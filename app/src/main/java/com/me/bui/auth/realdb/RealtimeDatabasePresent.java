package com.me.bui.auth.realdb;

import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.me.bui.auth.R;
import com.me.bui.auth.base.BasePresent;
import com.me.bui.auth.realdb.model.User;

/**
 * Created by mao.bui on 6/28/2018.
 */
public class RealtimeDatabasePresent extends BasePresent<IRealtimeDatabaseView> {
    private final static String TAG = RealtimeDatabasePresent.class.getSimpleName();

    private final static String USERS = "users";
    private final static String APP_TITLE = "app_title";
    private final static String APP_TITLE_VALUE = "Realtime Database";
    public static final class UserEntry {
        public static final  String NAME = "name";
        public static final  String EMAIL = "email";
    }

    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;

    private String userId;

    public RealtimeDatabasePresent() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mDatabaseReference = mFirebaseDatabase.getReference(USERS);

        // store app title to 'app_title' node
        mFirebaseDatabase.getReference(APP_TITLE).setValue(APP_TITLE_VALUE);
    }

    public void registerDatabaseListener() {
        // app_title change listener
        mFirebaseDatabase.getReference(APP_TITLE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.e(TAG, "App title updated");

                String appTitle = dataSnapshot.getValue(String.class);

                // update toolbar title
                mV.updateActionBarTitle(appTitle);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                mV.showError(R.string.fail_read_app_title);
            }
        });
    }

    public void createUser(String name, String email) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mDatabaseReference.push().getKey();
        }

        User user = new User(name, email);

        mDatabaseReference.child(userId).setValue(user);

        addUserChangeListener();
    }

    private void addUserChangeListener() {
        // User data change listener
        mDatabaseReference.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null ) {
                    mV.showWarning(R.string.warn_user_data);
                    return;
                }
                mV.updateUI(user);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                mV.showError(R.string.fail_read_user);
            }
        });
    }

    public void updateUser(String name, String email) {
        // updating the user via child nodes
        if (!TextUtils.isEmpty(name))
            mDatabaseReference.child(userId).child(UserEntry.NAME).setValue(name);

        if (!TextUtils.isEmpty(email))
            mDatabaseReference.child(userId).child(UserEntry.EMAIL).setValue(email);
    }

    public String getUserId() {
        return userId;
    }
}
