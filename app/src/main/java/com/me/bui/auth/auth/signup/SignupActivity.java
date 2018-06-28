package com.me.bui.auth.auth.signup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.me.bui.auth.R;
import com.me.bui.auth.auth.reset.ResetPasswordActivity;
import com.me.bui.auth.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends BaseActivity implements ISignupView {

    @BindView(R.id.email)
    EditText inputEmail;

    @BindView(R.id.password)
    EditText inputPassword;

    @BindView(R.id.sign_in_button)
    Button btnSignIn;

    @BindView(R.id.sign_up_button)
    Button btnSignUp;

    @BindView(R.id.btn_reset_password)
    Button btnResetPassword;

    private SignupPresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setUnbinder(ButterKnife.bind(this));

        mPresent = new SignupPresent();
        mPresent.attachView(this);
    }

    @OnClick(R.id.sign_in_button)
    void onSignIn() {
        finish();
    }

    @OnClick(R.id.sign_up_button)
    void onSignUp() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        mPresent.signup(email, password);
    }

    @OnClick(R.id.btn_reset_password)
    void onResetPassword() {
        startActivity(ResetPasswordActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresent.dettachView();
        mPresent.destroy();
        mPresent = null;
    }

    @Override
    public Activity getActivityClass() {
        return SignupActivity.this;
    }

    @Override
    public void showWarningEmail() {
        showWarning(getString(R.string.enter_email));
    }

    @Override
    public void showWarningPassword() {
        showWarning(getString(R.string.enter_password));
    }

    @Override
    public void showWarningPasswordMin() {
        showWarning(getString(R.string.minimum_password));

    }

    @Override
    public void showErrorCreateFailed() {
        showError(getString(R.string.auth_failed_reason));
    }

    @Override
    public void showSuccessCreated() {
        showSuccess(getString(R.string.created_user_successful));
    }
}
