package com.example.xyz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    Button b1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_home, container, false);

        b1 = (Button)view.findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(getContext(), "redirecting", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(),video.class);
                    startActivity(intent);
            }
        });
        return view;
    }
}