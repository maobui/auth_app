package com.me.bui.auth.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.me.bui.auth.R;
import com.me.bui.auth.auth.login.LoginActivity;
import com.me.bui.auth.base.BaseActivity;

public class MainActivity extends BaseActivity implements IMainView {

    private Button btnChangeEmail, btnChangePassword, btnSendResetEmail, btnRemoveUser,
            changeEmail, changePassword, sendEmail, remove, signOut;

    private EditText oldEmail, newEmail, password, newPassword;
    private ProgressBar progressBar;

    private MainPresent mPresent;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresent = new MainPresent();
        mPresent.attachView(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        mPresent.checkSession();

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        btnChangeEmail = (Button) findViewById(R.id.change_email_button);
        btnChangePassword = (Button) findViewById(R.id.change_password_button);
        btnSendResetEmail = (Button) findViewById(R.id.sending_pass_reset_button);
        btnRemoveUser = (Button) findViewById(R.id.remove_user_button);
        changeEmail = (Button) findViewById(R.id.changeEmail);
        changePassword = (Button) findViewById(R.id.changePass);
        sendEmail = (Button) findViewById(R.id.send);
        remove = (Button) findViewById(R.id.remove);
        signOut = (Button) findViewById(R.id.sign_out);

        oldEmail = (EditText) findViewById(R.id.old_email);
        newEmail = (EditText) findViewById(R.id.new_email);
        password = (EditText) findViewById(R.id.password);
        newPassword = (EditText) findViewById(R.id.newPassword);

        oldEmail.setVisibility(View.GONE);
        newEmail.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        newPassword.setVisibility(View.GONE);
        changeEmail.setVisibility(View.GONE);
        changePassword.setVisibility(View.GONE);
        sendEmail.setVisibility(View.GONE);
        remove.setVisibility(View.GONE);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

        btnChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldEmail.setVisibility(View.GONE);
                newEmail.setVisibility(View.VISIBLE);
                password.setVisibility(View.GONE);
                newPassword.setVisibility(View.GONE);
                changeEmail.setVisibility(View.VISIBLE);
                changePassword.setVisibility(View.GONE);
                sendEmail.setVisibility(View.GONE);
                remove.setVisibility(View.GONE);
            }
        });

        changeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.changeEmail();
            }
        });

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldEmail.setVisibility(View.GONE);
                newEmail.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                newPassword.setVisibility(View.VISIBLE);
                changeEmail.setVisibility(View.GONE);
                changePassword.setVisibility(View.VISIBLE);
                sendEmail.setVisibility(View.GONE);
                remove.setVisibility(View.GONE);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.changePassword();
            }
        });

        btnSendResetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldEmail.setVisibility(View.VISIBLE);
                newEmail.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                newPassword.setVisibility(View.GONE);
                changeEmail.setVisibility(View.GONE);
                changePassword.setVisibility(View.GONE);
                sendEmail.setVisibility(View.VISIBLE);
                remove.setVisibility(View.GONE);
            }
        });

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.sendEmail();
            }
        });

        btnRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.removeUser();
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

    //sign out method
    public void signOut() {
        mPresent.signOut();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideLoading();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresent.registerAuthListener();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresent.unregisterAuthListener();
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
//        super.showLoading();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
//        super.hideLoading();
        progressBar.setVisibility(View.GONE);
    }

    private void showSnackbar(String error, int color) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, error, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView tvLabel = sbView.findViewById(android.support.design.R.id.snackbar_text);
        tvLabel.setTextColor(color);
        snackbar.show();
    }

    @Override
    public void showError(String error) {
        this.showSnackbar(error, Color.RED);
    }

    @Override
    public void showWarning(String warning) {
        this.showSnackbar(warning, Color.YELLOW);
    }

    @Override
    public void showSuccess(String success) {
        this.showSnackbar(success, Color.GREEN);
    }

    @Override
    public void showSuccessEmailUpdated() {
        showSuccess(getString(R.string.email_updated));
    }

    @Override
    public void showErrorEmailUpdate() {
        showError(getString(R.string.email_update_failed));
    }

    @Override
    public void showSuccessPasswordUpdated() {
        showSuccess(getString(R.string.password_updated));
    }

    @Override
    public void showErrorPasswordUpdate() {
        showError(getString(R.string.password_update_failed));
    }

    @Override
    public void showSuccessPasswordSent() {
        showSuccess(getString(R.string.reset_pass_sent));
    }

    @Override
    public void showErrorPasswordSend() {
        showError(getString(R.string.reset_pass_failed));
    }

    @Override
    public void showSuccessDeleteUser() {
        showSuccess(getString(R.string.delete_user_successful));
    }

    @Override
    public void showErrorDeleteUserFail() {
        showError(getString(R.string.delete_user_failed));
    }

    @Override
    public void startLogin() {
        startActivityAndFinish(LoginActivity.class);
    }

    @Override
    public String getOldEmail() {
        return oldEmail.getText().toString().trim();
    }

    @Override
    public void setOldEmailError(CharSequence error) {
        oldEmail.setError(error);
    }

    @Override
    public String getNewPassword() {
        return newPassword.getText().toString().trim();
    }

    @Override
    public void setPasswordError(CharSequence error) {
        newPassword.setError(error);
    }

    @Override
    public String getNewEmail() {
        return newEmail.getText().toString().trim();
    }

    @Override
    public void setNewEmailError(CharSequence error) {
        newEmail.setError(error);
    }


}
