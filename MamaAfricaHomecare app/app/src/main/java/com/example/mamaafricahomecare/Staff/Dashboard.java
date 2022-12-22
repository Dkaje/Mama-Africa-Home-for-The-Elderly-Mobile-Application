package com.example.mamaafricahomecare.Staff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.CareGiver.Appointments;

public class Dashboard extends AppCompatActivity {

    private Button btn_approved,btn_pending,
            btn_lab,btn_history;
    private  String appointmentStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_approved=findViewById(R.id.btn_approved);
        btn_pending=findViewById(R.id.btn_pending);
        btn_lab=findViewById(R.id.btn_lab);
        btn_history=findViewById(R.id.btn_history);

        btn_pending.setOnClickListener(v->{
            appointmentStatus="Pending approval";
            Intent in=new Intent(getApplicationContext(), Appointments.class);
            in.putExtra("appointmentStatus",appointmentStatus);
            startActivity(in);
        });
        btn_approved.setOnClickListener(v->{
            appointmentStatus="Approved";
            Intent in=new Intent(getApplicationContext(), Appointments.class);
            in.putExtra("appointmentStatus",appointmentStatus);
            startActivity(in);
        });
        btn_lab.setOnClickListener(v->{
            appointmentStatus="Sent to lab";
            Intent in=new Intent(getApplicationContext(), Appointments.class);
            in.putExtra("appointmentStatus",appointmentStatus);
            startActivity(in);
        });
        btn_history.setOnClickListener(v->{
            appointmentStatus="Attended";
            Intent in=new Intent(getApplicationContext(), Appointments.class);
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