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

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
    @Bind(R.id.createUserButton) Button mCreateUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        ButterKnife.bind(this);

        mCreateUserButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == mCreateUserButton) {
            String usernameCreate = mEmailEditText.getText().toString();
            String passwordCreate = mPasswordEditText.getText().toString();
            String passwordVerify = mConfirmPasswordEditText.getText().toString();
            Intent intent = new Intent(CreateProfileActivity.this, MainActivity.class);
            intent.putExtra("usernameCreate", usernameCreate);
            intent.putExtra("passwordCreate", passwordCreate);
            intent.putExtra("passwordVerify", passwordVerify);
            startActivity(intent);
        }
    }
}
