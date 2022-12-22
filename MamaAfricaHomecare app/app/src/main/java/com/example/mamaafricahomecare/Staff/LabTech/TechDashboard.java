package com.example.mamaafricahomecare.Staff.LabTech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.Doctors.Bookings;

public class TechDashboard extends AppCompatActivity {
    private Button btn_pending,btn_done;
    private  String testStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_dashboard);

        btn_pending=findViewById(R.id.btn_pending);
        btn_done=findViewById(R.id.btn_done);

        btn_pending.setOnClickListener(v->{
            testStatus="Pending";
            Intent in=new Intent(getApplicationContext(), Test.class);
            in.putExtra("testStatus",testStatus);
            startActivity(in);
        });

        btn_done.setOnClickListener(v->{
            testStatus="Test done";
            Intent in=new Intent(getApplicationContext(), Test.class);
            in.putExtra("testStatus",testStatus);
            startActivity(in);
        });
    }
}