package com.me.bui.auth.realdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.me.bui.auth.R;
import com.me.bui.auth.base.BaseActivity;
import com.me.bui.auth.main.MainActivity;
import com.me.bui.auth.realdb.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealtimeDatabaseActivity extends BaseActivity implements IRealtimeDatabaseView{

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.txt_user)
    TextView txtDetails;

    @BindView(R.id.name)
    EditText inputName;

    @BindView(R.id.email)
    EditText inputEmail;

    @BindView(R.id.btn_save)
    Button btnSave;

    private RealtimeDatabasePresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_database);

        setUnbinder(ButterKnife.bind(this));

        mPresent = new RealtimeDatabasePresent();
        mPresent.attachView(this);
        mPresent.registerDatabaseListener();

        setUp();
    }

    @Override
    public void setUp() {
        // Displaying toolbar icon
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        toggleButton();
    }

    @OnClick(R.id.btn_save)
    void onSaveButtonClick() {
        String name = inputName.getText().toString();
        String email = inputEmail.getText().toString();

        // Check for already existed userId
        if (TextUtils.isEmpty(mPresent.getUserId())) {
            mPresent.createUser(name, email);
        } else {
            mPresent.updateUser(name, email);
        }
    }

    // Changing button text
    private void toggleButton() {
        if (TextUtils.isEmpty(mPresent.getUserId())) {
            btnSave.setText(getString(R.string.action_save));
        } else {
            btnSave.setText(getString(R.string.action_update));
        }
    }

    @Override
    public void updateActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void updateUI(User user) {
        showSuccess(R.string.msg_user_changed);

        // Display newly updated name and email
        txtDetails.setText(user.name + ", " + user.email);

        // clear edit text
        inputEmail.setText("");
        inputName.setText("");

        toggleButton();
    }

    @Override
    protected void onDestroy() {
        mPresent.dettachView();
        mPresent.destroy();
        mPresent = null;
        super.onDestroy();
    }
}
