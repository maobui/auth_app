package com.me.bui.auth.main;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.me.bui.auth.R;
import com.me.bui.auth.auth.signup.SignupActivity;
import com.me.bui.auth.base.BasePresent;

/**
 * Created by mao.bui on 6/27/2018.
 */
public class MainPresent extends BasePresent<IMainView>{

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    public MainPresent() {
        //get firebase auth instance
        this.auth = FirebaseAuth.getInstance();
    }

    public void startLogin() {
        mV.startLogin();
    }

    public void checkSession() {
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startLogin();
                }
            }
        };
    }

    public void changeEmail() {
        mV.showLoading();
        if (getCurrentUser() != null && !mV.getNewEmail().equals("")) {
            getCurrentUser().updateEmail(mV.getNewEmail())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                mV.showSuccessEmailUpdated();
                                signOut();
                                mV.hideLoading();
                            } else {
                                mV.showErrorEmailUpdate();
                                mV.hideLoading();
                            }
                        }
                    });
        } else if (mV.getNewEmail().equals("")) {
            mV.setNewEmailError("Enter email");
            mV.hideLoading();
        }
    }

    public void changePassword() {
        mV.showLoading();
        if (getCurrentUser() != null && !mV.getNewPassword().equals("")) {
            if (mV.getNewPassword().length() < 6) {
                mV.setPasswordError("Password too short, enter minimum 6 characters");
                mV.hideLoading();
            } else {
                getCurrentUser().updatePassword(mV.getNewPassword())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    mV.showSuccessPasswordSent();
                                    signOut();
                                    mV.hideLoading();
                                } else {
                                    mV.showErrorPasswordSend();
                                    mV.hideLoading();
                                }
                            }
                        });
            }
        } else if (mV.getNewPassword().equals("")) {
            mV.setPasswordError("Enter password");
            mV.hideLoading();
        }
    }

    public void sendEmail() {
        mV.showLoading();
        if (!mV.getOldEmail().equals("")) {
            auth.sendPasswordResetEmail(mV.getOldEmail())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                mV.showSuccessPasswordSent();
                                mV.hideLoading();
                            } else {
                                mV.showErrorPasswordSend();
                                mV.hideLoading();
                            }
                        }
                    });
        } else {
            mV.setOldEmailError("Enter email");
            mV.hideLoading();
        }
    }
    public void removeUser() {
        mV.showLoading();
        if (getCurrentUser() != null) {
            getCurrentUser().delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                mV.showSuccessDeleteUser();
                                mV.startActivityAndFinish(SignupActivity.class);
                                mV.hideLoading();
                            } else {
                                mV.showErrorDeleteUserFail();
                                mV.hideLoading();
                            }
                        }
                    });
        }
    }

    public void signOut() {
        this.auth.signOut();
    }

    public FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public void registerAuthListener() {
        auth.addAuthStateListener(authListener);
    }

    public void unregisterAuthListener() {
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
