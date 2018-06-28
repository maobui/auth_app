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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IMainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.change_email_button)
    Button btnChangeEmail;

    @BindView(R.id.change_password_button)
    Button btnChangePassword;

    @BindView(R.id.sending_pass_reset_button)
    Button btnSendResetEmail;

    @BindView(R.id.remove_user_button)
    Button btnRemoveUser;

    @BindView(R.id.changeEmail)
    Button changeEmail;

    @BindView(R.id.changePass)
    Button changePassword;

    @BindView(R.id.send)
    Button sendEmail;

    @BindView(R.id.remove)
    Button remove;

    @BindView(R.id.sign_out)
    Button signOut;

    @BindView(R.id.old_email)
    EditText oldEmail;

    @BindView(R.id.new_email)
    EditText newEmail;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.newPassword)
    EditText newPassword;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private MainPresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUnbinder(ButterKnife.bind(this));

        mPresent = new MainPresent();
        mPresent.attachView(this);
        mPresent.checkSession();

        setUp();
    }

    @Override
    public void setUp() {
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        oldEmail.setVisibility(View.GONE);
        newEmail.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        newPassword.setVisibility(View.GONE);
        changeEmail.setVisibility(View.GONE);
        changePassword.setVisibility(View.GONE);
        sendEmail.setVisibility(View.GONE);
        remove.setVisibility(View.GONE);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.change_email_button)
    void onChangeEmailButtonClick() {
        oldEmail.setVisibility(View.GONE);
        newEmail.setVisibility(View.VISIBLE);
        password.setVisibility(View.GONE);
        newPassword.setVisibility(View.GONE);
        changeEmail.setVisibility(View.VISIBLE);
        changePassword.setVisibility(View.GONE);
        sendEmail.setVisibility(View.GONE);
        remove.setVisibility(View.GONE);
    }

    @OnClick(R.id.changeEmail)
    void onChangeEmail() {
        mPresent.changeEmail();
    }


    @OnClick(R.id.change_password_button)
    void onChangePasswordButtonClick() {
        oldEmail.setVisibility(View.GONE);
        newEmail.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        newPassword.setVisibility(View.VISIBLE);
        changeEmail.setVisibility(View.GONE);
        changePassword.setVisibility(View.VISIBLE);
        sendEmail.setVisibility(View.GONE);
        remove.setVisibility(View.GONE);
    }

    @OnClick(R.id.changePass)
    void onChangePassword() {
        mPresent.changePassword();
    }

    @OnClick(R.id.sending_pass_reset_button)
    void onResetPasswordButtonClick() {
        oldEmail.setVisibility(View.VISIBLE);
        newEmail.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        newPassword.setVisibility(View.GONE);
        changeEmail.setVisibility(View.GONE);
        changePassword.setVisibility(View.GONE);
        sendEmail.setVisibility(View.VISIBLE);
        remove.setVisibility(View.GONE);
    }

    @OnClick(R.id.send)
    void onResetPassword() {
        mPresent.sendEmail();
    }

    @OnClick(R.id.remove_user_button)
    void onRemoveUserButtonClick() {
        mPresent.removeUser();
    }

    @OnClick(R.id.sign_out)
    void onSignOutButtonClick() {
        signOut();
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
    public void setOldEmailError() {
        oldEmail.setError(getString(R.string.enter_email));
    }

    @Override
    public String getNewPassword() {
        return newPassword.getText().toString().trim();
    }

    @Override
    public void setPasswordError() {
        newPassword.setError(getString(R.string.minimum_password));
    }

    @Override
    public void setPasswordErrorNull() {
        newPassword.setError(getString(R.string.enter_password));
    }

    @Override
    public String getNewEmail() {
        return newEmail.getText().toString().trim();
    }

    @Override
    public void setNewEmailError() {
        newEmail.setError(getString(R.string.enter_email));
    }


}
