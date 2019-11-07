package com.example.finitecampusproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    private Button loginButton;
    private Button signUpButton;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //source for all animations https://stackoverflow.com/questions/3389501/activity-transition-in-android
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.logInButton);
        signUpButton = (Button) findViewById(R.id.SignUpButton);
        email = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        mAuth = FirebaseAuth.getInstance();
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cool = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(cool);
            }
        });

        userLogin();

    }

    private void userLogin() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID = email.getText().toString().trim();
                String passwordInfo = password.getText().toString().trim();

                if(emailID.isEmpty()){
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(emailID).matches()){
                    email.setError("Please enter a valid email id");
                    email.requestFocus();
                    return;
                }
                if(passwordInfo.isEmpty()){
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }
                mAuth.signInWithEmailAndPassword(emailID, passwordInfo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent b = new Intent(LogInActivity.this,ClassListActivity.class);
                            b.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(b);
                        }else{
                            Toast.makeText(LogInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}
