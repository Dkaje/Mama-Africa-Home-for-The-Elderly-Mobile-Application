package com.example.mamaafricahomecare.Staff.Doctors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.CareGiver.Appointments;

public class BookingDashboard extends AppCompatActivity {


    private Button btn_approved,btn_pending,
            btn_lab,btn_history,btn_results;
    private  String appointmentStatus,testStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_dashboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_approved=findViewById(R.id.btn_approved);
        btn_pending=findViewById(R.id.btn_pending);
        btn_lab=findViewById(R.id.btn_lab);
        btn_results=findViewById(R.id.btn_results);
        btn_history=findViewById(R.id.btn_history);
        
        btn_pending.setOnClickListener(v->{
            appointmentStatus="Pending approval";
            Intent in=new Intent(getApplicationContext(), Bookings.class);
            in.putExtra("appointmentStatus",appointmentStatus);
            startActivity(in);
        });
        btn_approved.setOnClickListener(v->{
            appointmentStatus="Approved";
            Intent in=new Intent(getApplicationContext(), Bookings.class);
            in.putExtra("appointmentStatus",appointmentStatus);
            startActivity(in);
        });
        btn_lab.setOnClickListener(v->{
            testStatus="Pending";
            Intent in=new Intent(getApplicationContext(), LabTest.class);
            in.putExtra("testStatus",testStatus);
            startActivity(in);
        });
        btn_results.setOnClickListener(v->{
            testStatus="Test done";
            Intent in=new Intent(getApplicationContext(), LabTest.class);
            in.putExtra("testStatus",testStatus);
            startActivity(in);
        });
        btn_history.setOnClickListener(v->{
            appointmentStatus="Attended";
            Intent in=new Intent(getApplicationContext(), Bookings.class);
            in.putExtra("appointmentStatus",appointmentStatus);
            startActivity(in);
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}