package com.me.bui.auth.auth.signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.me.bui.auth.R;
import com.me.bui.auth.base.BasePresent;
import com.me.bui.auth.main.MainActivity;

/**
 * Created by mao.bui on 6/27/2018.
 */
public class SignupPresent extends BasePresent<ISignupView>{

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    public SignupPresent() {
        //get firebase auth instance
        this.auth = FirebaseAuth.getInstance();
    }

    public void signup(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            mV.showWarningEmail();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mV.showWarningPassword();
            return;
        }

        if (password.length() < 6) {
            mV.showWarningPasswordMin();
            return;
        }

        mV.showLoading();
        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(mV.getActivityClass(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mV.hideLoading();
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            mV.showErrorCreateFailed();
                        } else {
                            mV.showSuccessCreated();
                            mV.startActivityAndFinish(MainActivity.class);
                        }
                    }
                });
    }
}
