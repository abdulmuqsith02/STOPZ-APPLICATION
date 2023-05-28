package com.example.xyz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class Status extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        ed1=(EditText) findViewById(R.id.editTextTextPersonName);
        ed2=(EditText) findViewById(R.id.editTextTextPersonName2);
        ed3=(EditText)findViewById(R.id.editTextTextPersonName3);
        ed4=findViewById(R.id.editTextTextPersonName4);
        ed5=findViewById(R.id.editTextTextPersonName5);
        b1=findViewById(R.id.submit);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
                String x1=ed1.getText().toString().trim();
                String x2=ed2.getText().toString().trim();
                String x3=ed3.getText().toString().trim();
                String x4=ed4.getText().toString().trim();
                String x5=ed5.getText().toString().trim();



                Toast.makeText(Status.this, "Submitted", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void userLogin() {


        String x1=ed1.getText().toString().trim();
        if(x1.isEmpty()){
            ed1.setError("Answer is required!");
            ed1.requestFocus();
            return;
        }
        if(!(x1.matches("yes")||x1.matches("no"))){
            ed1.setError("Please provide yes or no!");
            ed1.requestFocus();
            return;
        }
        String x2=ed2.getText().toString().trim();
        if(x2.isEmpty()){
            ed2.setError("Answer is required!");
            ed2.requestFocus();
            return;
        }
        if(!(x2.matches("yes")||x2.matches("no"))){
            ed2.setError("Please provide yes or no!");
            ed2.requestFocus();
            return;
        }
        String x3=ed3.getText().toString().trim();
        if(x3.isEmpty()){
            ed3.setError("Answer is required!");
            ed3.requestFocus();
            return;
        }
        if(!(x3.matches("yes")||x3.matches("no"))){
            ed3.setError("Please provide yes or no!");
            ed3.requestFocus();
            return;
        }
        String x4=ed4.getText().toString().trim();
        if(x4.isEmpty()){
            ed4.setError("Answer is required!");
            ed4.requestFocus();
            return;
        }
        if(!(x4.matches("yes")||x4.matches("no"))){
            ed4.setError("Please provide yes or no!");
            ed4.requestFocus();
            return;
        }
        String x5=ed5.getText().toString().trim();
        if(x5.isEmpty()){
            ed5.setError("Answer is required!");
            ed5.requestFocus();
            return;
        }
        if(!(x5.matches("yes")||x5.matches("no"))){
            ed5.setError("Please provide yes or no!");
            ed5.requestFocus();
            return;
        }
    }

}