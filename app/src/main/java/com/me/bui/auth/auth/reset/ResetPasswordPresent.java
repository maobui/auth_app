package com.me.bui.auth.auth.reset;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.me.bui.auth.R;
import com.me.bui.auth.base.BasePresent;

/**
 * Created by mao.bui on 6/28/2018.
 */
public class ResetPasswordPresent extends BasePresent<IResetPasswordView> {

    private FirebaseAuth auth;

    public ResetPasswordPresent() {
        //Get Firebase auth instance
        this.auth = FirebaseAuth.getInstance();
    }

    public void reset(String email) {
        if (TextUtils.isEmpty(email)) {
            mV.showWarningEmail();
            return;
        }

        mV.showLoading();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mV.showSuccessSent();
                        } else {
                            mV.showErrorSendFail();
                        }
                        mV.hideLoading();
                    }
                });
    }
}
