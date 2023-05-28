package com.example.xyz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SetTime extends AppCompatActivity {

    int tHour, tMin;
    ListView listView;
    Button b2;
    TextView ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        List<String> items = (ArrayList<String>) getIntent().getSerializableExtra("mylist");

        listView = findViewById(R.id.items_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1,items.toArray(new String[items.size()]));
        listView.setAdapter(adapter);


        b2=findViewById(R.id.confirm_button);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(SetTime.this, "Time Confirmed", Toast.LENGTH_SHORT).show();


                Intent intent=new Intent(SetTime.this,splash.class);
                startActivity(intent);
            }
        });
        tHour=0;
        tMin=30;
        String time="Default Time :" +tMin+"Min";
//        tHour+"hr :"+tMin+"Min";
        ed= (TextView) findViewById(R.id.timer);
        //Toast.makeText(this, "Time:"+time, Toast.LENGTH_SHORT).show();
        ed.setText(time);

    }
}
