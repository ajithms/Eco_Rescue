package com.example.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main6Activity extends AppCompatActivity {

    DatabaseReference databaseComplaints;
    ListView listViewComplaints ;

    List<Complaint> complaintList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        databaseComplaints = FirebaseDatabase.getInstance().getReference("Complaint");

        listViewComplaints = (ListView) findViewById(R.id.list_complaints);
        complaintList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseComplaints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                complaintList.clear();

                for (DataSnapshot complaintSnapshot : dataSnapshot.getChildren()) {
                    Complaint complaint = complaintSnapshot.getValue(Complaint.class);

                    complaintList.add(complaint);
                }
                ComplaintList adapter = new ComplaintList(Main6Activity.this, complaintList);
                listViewComplaints.setAdapter(adapter);
                listViewComplaints.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(getApplicationContext(), Main7Activity.class);
                        Shared.obj = complaintList.get(position);
                      //  Toast.makeText(getApplicationContext(), Shared.obj.encode, Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
