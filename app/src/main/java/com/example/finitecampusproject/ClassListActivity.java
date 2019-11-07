package com.example.finitecampusproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClassListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_class_list);
        Button button = (Button) findViewById(R.id.addClassButton);
        Button classOneButton = (Button) findViewById(R.id.classOne);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassListActivity.this, AddClassActivity.class);
                startActivity(intent);
            }
        });
        classOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent z =  new Intent(ClassListActivity.this, ClassActivity.class);
                startActivity(z);
            }
        });
    }
}
