package com.example.finitecampusproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ViewIndividualAssignment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_view_individual_assignment);
    }
}
