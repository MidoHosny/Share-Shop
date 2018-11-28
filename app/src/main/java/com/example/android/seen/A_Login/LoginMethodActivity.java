package com.example.android.seen.A_Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.android.seen.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginMethodActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ImageView mFacebookButton, mgoogleButton, aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_method_activity);

        mFacebookButton = findViewById(R.id.FacebookButtonMethod);
        mgoogleButton = findViewById(R.id.googleButtonMethod);
        aa = findViewById(R.id.inta);
        mFacebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMethodActivity.this, FacebookLogin.class));
            }
        });

        mgoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMethodActivity.this, GoogleLogin.class));
            }
        });


        aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginMethodActivity.this,loginActivity.class));

            }
        });
    }


}
