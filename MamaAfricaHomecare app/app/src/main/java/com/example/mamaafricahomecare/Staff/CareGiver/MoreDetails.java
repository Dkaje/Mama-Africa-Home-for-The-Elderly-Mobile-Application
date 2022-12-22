package com.example.mamaafricahomecare.Staff.CareGiver;

import static com.example.mamaafricahomecare.ConstFiles.Urls.APPROVE_ELDER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MoreDetails extends AppCompatActivity {
    private TextView txv_name,txv_gender,txv_dob,txv_elderID,
            txv_status,txv_familyMember;
    private Button btn_appointment,btn_donation;
    private ProgressBar progressBar;
    private String elderID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txv_elderID=findViewById(R.id.txv_elderID);
        txv_name=findViewById(R.id.txv_name);
        txv_familyMember=findViewById(R.id.txv_familyMember);
        txv_gender=findViewById(R.id.txv_gender);
        txv_dob=findViewById(R.id.txv_dob);
        txv_status=findViewById(R.id.txv_status);
        btn_donation=findViewById(R.id.btn_donations);
        btn_appointment=findViewById(R.id.btn_appointment);
        progressBar=findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        Intent in=getIntent();
        elderID=in.getStringExtra("elderID");
        txv_elderID.setText("ID: "+in.getStringExtra("elderID"));
        txv_familyMember.setText("Family member "+in.getStringExtra("familyMember"));
        txv_name.setText(in.getStringExtra("name"));
        txv_gender.setText(in.getStringExtra("gender"));
        txv_dob.setText("DOB: "+in.getStringExtra("dob"));
        txv_status.setText(" "+in.getStringExtra("elderStatus"));
        btn_appointment.setOnClickListener(v->{
            Intent n=new Intent(this,Doctors.class);
            n.putExtra("elderID", in.getStringExtra("elderID"));
            n.putExtra("familyMember", in.getStringExtra("familyMember"));
            n.putExtra("name", in.getStringExtra("name"));
            n.putExtra("gender", in.getStringExtra("gender"));
            n.putExtra("dob", in.getStringExtra("dob"));
            n.putExtra("elderStatus", in.getStringExtra("elderStatus"));
            startActivity(n);
            Toast.makeText(this,  in.getStringExtra("dob"), Toast.LENGTH_SHORT).show();
        });
        btn_donation.setOnClickListener(v->{
         Intent i=new Intent(this,RequestForm.class);
            i.putExtra("elderID", in.getStringExtra("elderID"));
         startActivity(i);
         finish();
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