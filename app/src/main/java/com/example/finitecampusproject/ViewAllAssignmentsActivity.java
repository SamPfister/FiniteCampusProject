package com.example.finitecampusproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ViewAllAssignmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_assignments);
        ListView assignmentsList = (ListView) findViewById(R.id.AssignmentsListView);
    }
}
