package com.example.finitecampusproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AddClassActivity extends AppCompatActivity {
    private EditText classNameEditText;
    DatabaseReference reference;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        classNameEditText = (EditText) findViewById(R.id.editText);
        Button button2 = (Button) findViewById(R.id.cancelButton);
        arrayList = new ArrayList<String>();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String className = classNameEditText.getText().toString().trim();
                arrayList.add(className);
                classNameEditText.setText("");
            }
        });
        submitButtonClick();
    }
    private void submitButtonClick(){
        Button button = (Button) findViewById(R.id.submitButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentAuthUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                reference = FirebaseDatabase.getInstance().getReference("Users").child(currentAuthUid).child("Classes");
                HashMap<String,Object> classNameMap = new HashMap<>();
                    classNameMap.put("ClassesList",arrayList);
                    reference.setValue(classNameMap);
                Intent intent = new Intent(AddClassActivity.this, ClassListActivity.class);
                startActivity(intent);
            }
        });
    }

}
