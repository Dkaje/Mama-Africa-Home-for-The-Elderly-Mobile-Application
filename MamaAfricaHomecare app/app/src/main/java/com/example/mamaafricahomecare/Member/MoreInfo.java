package com.example.mamaafricahomecare.Member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.CareGiver.Doctors;

public class MoreInfo extends AppCompatActivity {

    private TextView txv_name, txv_gender, txv_dob, txv_elderID,
            txv_status;
    private Button btn_appointment, btn_donation;
    private ProgressBar progressBar;
    private String elderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txv_elderID = findViewById(R.id.txv_elderID);
        txv_name = findViewById(R.id.txv_name);
        txv_gender = findViewById(R.id.txv_gender);
        txv_dob = findViewById(R.id.txv_dob);
        txv_status = findViewById(R.id.txv_status);
        btn_donation = findViewById(R.id.btn_donations);
        btn_appointment = findViewById(R.id.btn_appointment);


        Intent in = getIntent();
        elderID = in.getStringExtra("elderID");
        txv_elderID.setText("ID: " + in.getStringExtra("elderID"));
        txv_name.setText(in.getStringExtra("name"));
        txv_gender.setText(in.getStringExtra("gender"));
        txv_dob.setText("DOB: " + in.getStringExtra("dob"));
        txv_status.setText(" " + in.getStringExtra("elderStatus"));


        btn_appointment.setOnClickListener(v -> {
            Intent n = new Intent(this, HospitalAppointments.class);
            n.putExtra("elderID", in.getStringExtra("elderID"));
            startActivity(n);
        });
        btn_donation.setOnClickListener(v -> {
            Intent n = new Intent(this, RequestElder.class);
            n.putExtra("elderID", in.getStringExtra("elderID"));
            startActivity(n);
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