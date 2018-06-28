package com.me.bui.auth.auth.reset;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
