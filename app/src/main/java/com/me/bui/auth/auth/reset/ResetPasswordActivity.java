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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends BaseActivity implements IResetPasswordView {

    @BindView(R.id.email)
    EditText inputEmail;

    @BindView(R.id.btn_reset_password)
    Button btnReset;

    @BindView(R.id.btn_back)
    Button btnBack;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private ResetPasswordPresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        setUnbinder(ButterKnife.bind(this));

        mPresent = new ResetPasswordPresent();
        mPresent.attachView(this);
    }

    @OnClick(R.id.btn_reset_password)
    void onResetPassword() {
        String email = inputEmail.getText().toString().trim();

        mPresent.reset(email);
    }

    @OnClick(R.id.btn_back)
    void onBack() {
        finish();
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
