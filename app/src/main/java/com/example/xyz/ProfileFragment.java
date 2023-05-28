package com.example.xyz;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

     FirebaseUser user;
     DatabaseReference reference;
     String userID;
     Button logout;
     Button status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        status=(Button) view.findViewById(R.id.statusButton);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Status.class);
                //intent.putExtra("mylist", items);
                startActivity(intent);
            }
        });

            logout=(Button) view.findViewById(R.id.signOut);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getContext(), SignIn.class));
                }
            });

            user=FirebaseAuth.getInstance().getCurrentUser();
            reference= FirebaseDatabase.getInstance().getReference("Users");
            userID=user.getUid();

            final TextView fullNameTextView=(TextView) view.findViewById(R.id.fullName);
            final TextView emailTextView=(TextView) view.findViewById(R.id.emailAddress);
            final TextView ageTextView=(TextView) view.findViewById(R.id.age);
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile=snapshot.getValue(User.class);

                    if(userProfile!=null){
                        String fullName=userProfile.fullName;
                        String email=userProfile.email;
                        String age=userProfile.age;

                        fullNameTextView.setText(fullName);
                        emailTextView.setText(email);
                        ageTextView.setText(age);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "Something wrong Happened!", Toast.LENGTH_LONG).show();
                }
            });
        return view;
    }
}