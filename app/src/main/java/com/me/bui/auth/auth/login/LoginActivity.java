package com.me.bui.auth.auth.login;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.me.bui.auth.R;
import com.me.bui.auth.auth.reset.ResetPasswordActivity;
import com.me.bui.auth.auth.signup.SignupActivity;
import com.me.bui.auth.base.BaseActivity;

public class LoginActivity extends BaseActivity implements ILoginView {

    private CoordinatorLayout coordinatorLayout;
    private EditText inputEmail, inputPassword;

    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;

    LoginPresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresent = new LoginPresent();
        mPresent.attachView(this);

        mPresent.checkSession();

        // set the view now
        setContentView(R.layout.activity_login);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SignupActivity.class);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ResetPasswordActivity.class);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                mPresent.login(email, password);
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
    public Activity getActivityClass() {
        return LoginActivity.this;
    }

    @Override
    public void setInputPasswordError() {
        inputPassword.setError(getString(R.string.minimum_password));
    }

    @Override
    public void showLoading() {
//        super.showLoading();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
//        super.hideLoading();
        progressBar.setVisibility(View.GONE);
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
    public void showAuthFailed() {
        showError(getString(R.string.auth_failed));
    }
}
