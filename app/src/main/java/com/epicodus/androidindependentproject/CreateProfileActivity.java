package com.epicodus.androidindependentproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.usernameEditText) EditText mUsernameEditText;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.createProfileSubmitButton) Button mCreateProfileSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        ButterKnife.bind(this);

        mCreateProfileSubmitButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v == mCreateProfileSubmitButton) {
            String usernameCreate = mUsernameEditText.getText().toString();
            String passwordCreate = mPasswordEditText.getText().toString();
            Intent intent = new Intent(CreateProfileActivity.this, MainActivity.class);
            intent.putExtra("usernameCreate", usernameCreate);
            intent.putExtra("passwordCreate", passwordCreate);
            startActivity(intent);
        }
    }
}
