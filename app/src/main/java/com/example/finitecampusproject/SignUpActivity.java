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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DataTruncation;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private Button signUp;
    private EditText emailEditText;
    private EditText passwordEditText;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailEditText = (EditText) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        registerUser();

    }
    private void registerUser() {
        signUp = (Button) findViewById(R.id.submitButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                if(email.isEmpty()){
                    emailEditText.setError("Email is required");
                    emailEditText.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    passwordEditText.setError("Password is required");
                    passwordEditText.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEditText.setError("Please enter a valid email address");
                    emailEditText.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String currentAuthUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(currentAuthUid);
                            HashMap<String,Object> userInfo = new HashMap<>();
                            userInfo.put("Classes","");
                            databaseReference.setValue(userInfo);
                            Toast.makeText(SignUpActivity.this, "User registration has been successful", Toast.LENGTH_SHORT).show();
                            Intent c = new Intent(SignUpActivity.this, LogInActivity.class);
                            c.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(c);
                        }else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(SignUpActivity.this, "This email is already registered", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

            }
        });

    }

}
