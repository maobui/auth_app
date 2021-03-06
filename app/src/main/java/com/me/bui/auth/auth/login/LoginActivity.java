package com.me.bui.auth.auth.login;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.me.bui.auth.R;
import com.me.bui.auth.auth.reset.ResetPasswordActivity;
import com.me.bui.auth.auth.signup.SignupActivity;
import com.me.bui.auth.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.email)
    EditText inputEmail;

    @BindView(R.id.password)
    EditText inputPassword;

    @BindView(R.id.btn_signup)
    Button btnSignup;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_reset_password)
    Button btnReset;


    LoginPresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the view now
        setContentView(R.layout.activity_login);

        setUnbinder(ButterKnife.bind(this));

        mPresent = new LoginPresent();
        mPresent.attachView(this);
        mPresent.checkSession();
    }


    @OnClick(R.id.btn_signup)
    void onSignUp() {
        startActivity(SignupActivity.class);
    }

    @OnClick(R.id.btn_login)
    void onLogin() {
        String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        mPresent.login(email, password);
    }

    @OnClick(R.id.btn_reset_password)
    void onResetPassword() {
        startActivity(ResetPasswordActivity.class);
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
        return LoginActivity.this;
    }

    @Override
    public void setInputPasswordError() {
        inputPassword.setError(getString(R.string.minimum_password));
    }

    @Override
    public void showWarningEmail() {
        showWarning(R.string.enter_email);
    }

    @Override
    public void showWarningPassword() {
        showWarning(R.string.enter_password);
    }

    @Override
    public void showAuthFailed() {
        showError(R.string.auth_failed);
    }
}
