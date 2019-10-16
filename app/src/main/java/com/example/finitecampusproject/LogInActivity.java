package com.example.finitecampusproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.logInButton);
        Button button2 = (Button) findViewById(R.id.signUpButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEditText = (EditText)findViewById(R.id.editText);
                EditText passwordEditText = (EditText)findViewById(R.id.editText2);
                EditText confirmPasswordEditText = (EditText)findViewById(R.id.editText3);
                String name = nameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();
                boolean check = true;
                if(name.isEmpty()){
                    nameEditText.setError("Name cannot be empty");
                    check = false;
                }
                if(password.isEmpty()){
                    passwordEditText.setError("Password cannot be empty");
                    check = false;
                }
                if(confirmPassword.isEmpty()){
                    confirmPasswordEditText.setError("Confirm Password cannot be empty");
                    check = false;
                }
                if(!(confirmPassword.equals(password))){
                    confirmPasswordEditText.setError("Passwords do not match");
                    check = false;
                }
                if(check){
                    Intent intent = new Intent(LogInActivity.this, ClassListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

}
