package com.example.finitecampusproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;

public class ClassActivity extends AppCompatActivity {
    private Button addStudentButton;
    private Button viewAllAssignmentsButton;
    private EditText addStudentEditText;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;
    private Button home;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_class);
        home = (Button) findViewById(R.id.homeButton);
        addStudentButton = (Button) findViewById(R.id.addStudentButton);
        addStudentEditText = (EditText) findViewById(R.id.studentEditText);
        viewAllAssignmentsButton = (Button) findViewById(R.id.viewAllAssignmentsButton);
        listView = (ListView) findViewById(R.id.listViewStudents);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(ClassActivity.this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        addingStudents();
        homeButtonDatabase();
        viewAllAssignmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClassActivity.this, ViewAllAssignmentsActivity.class);
                startActivity(intent);
            }
        });
    }
    private void addingStudents(){
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = addStudentEditText.getText().toString().trim();
                if(result.trim().length() > 0){
                    arrayList.add(result);
                    arrayAdapter.notifyDataSetChanged();
                    addStudentEditText.setText("");
                }else{
                    Toast.makeText(ClassActivity.this, "Please enter in a valid student name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void homeButtonDatabase(){
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!arrayList.isEmpty()){
                    String currentAuthUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    reference = FirebaseDatabase.getInstance().getReference("Users").child(currentAuthUid).child("Classes").child("ClassesList");
                    HashMap<String,Object> classNameMap = new HashMap<>();
                    classNameMap.put("Class",arrayList);
                    reference.setValue(classNameMap);
                }

            }
        });
    }
}
