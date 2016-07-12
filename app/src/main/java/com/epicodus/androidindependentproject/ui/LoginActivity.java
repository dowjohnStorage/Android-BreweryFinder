package com.epicodus.androidindependentproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.androidindependentproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.emailLogin) EditText mEmailLogin;
    @Bind(R.id.passwordLogin) EditText mPasswordLogin;
    @Bind(R.id.loginButton) Button mLoginButton;
    @Bind(R.id.createAccountButton) Button mCreateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginButton.setOnClickListener(this);
        mCreateAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mLoginButton) {
            String email = mEmailLogin.getText().toString();
            String password = mPasswordLogin.getText().toString();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("password", password);
            startActivity(intent);
            finish();
        }
        if (view == mCreateAccountButton) {
            Intent intent = new Intent(LoginActivity.this, CreateProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

