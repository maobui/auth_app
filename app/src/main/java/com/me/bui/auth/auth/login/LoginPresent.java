package com.me.bui.auth.auth.login;

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
 * Created by mao.bui on 6/28/2018.
 */
public class LoginPresent extends BasePresent<ILoginView> {

    private FirebaseAuth auth;

    public LoginPresent() {
        //Get Firebase auth instance
        this.auth = FirebaseAuth.getInstance();
    }

    public void checkSession() {
        if (auth.getCurrentUser() != null) {
            mV.startActivityAndFinish(MainActivity.class);
        }
    }

    public void login(String email, final String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(mV.getContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(mV.getContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        mV.showLoading();

        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(mV.getActivityClass(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        mV.hideLoading();
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                mV.setInputPasswordError(mV.getContext().getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(mV.getContext(), mV.getContext().getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            mV.startActivityAndFinish(MainActivity.class);
                        }
                    }
                });
    }
}
