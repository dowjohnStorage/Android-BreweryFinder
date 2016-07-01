package com.epicodus.androidindependentproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.usernameLogin) EditText mUsernameLogin;
    @Bind(R.id.passwordLogin) EditText mPasswordLogin;
    @Bind(R.id.loginButton) Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameLogin.getText().toString();
                String password = mPasswordLogin.getText().toString();
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });
    }
}
