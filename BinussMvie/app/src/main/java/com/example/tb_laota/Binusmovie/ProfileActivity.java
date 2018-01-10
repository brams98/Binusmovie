package com.example.tb_laota.Binusmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    public String photo, name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(ProfileActivity.this, loginActivity.class));
                }
            }
        };

        mAuth = FirebaseAuth.getInstance();
        loadUser();
    }

    private void loadUser() {
        FirebaseUser user = mAuth.getCurrentUser();
        photo = user.getPhotoUrl().toString();
        name = user.getDisplayName();
        email = user.getEmail();
    }

    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }
}
