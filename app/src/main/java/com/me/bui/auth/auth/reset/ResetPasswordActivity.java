package com.me.bui.auth.auth.reset;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.me.bui.auth.R;
import com.me.bui.auth.base.BaseActivity;

public class ResetPasswordActivity extends BaseActivity implements IResetPasswordView {

    private CoordinatorLayout coordinatorLayout;
    private EditText inputEmail;
    private Button btnReset, btnBack;
    private ProgressBar progressBar;

    ResetPasswordPresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        inputEmail = (EditText) findViewById(R.id.email);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        btnBack = (Button) findViewById(R.id.btn_back);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mPresent = new ResetPasswordPresent();
        mPresent.attachView(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();

                mPresent.reset(email);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresent.dettachView();
        mPresent.destroy();
        mPresent = null;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWarningEmail() {
        showWarning(getString(R.string.enter_email_id));
    }

    @Override
    public void showSuccessSent() {
        showSuccess(getString(R.string.sent_reset_password));
    }

    @Override
    public void showErrorSendFail() {
        showError(getString(R.string.fail_send_reset_password));
    }
}
