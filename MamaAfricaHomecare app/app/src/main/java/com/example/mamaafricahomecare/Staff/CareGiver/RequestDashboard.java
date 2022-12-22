package com.example.mamaafricahomecare.Staff.CareGiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.mamaafricahomecare.R;

public class RequestDashboard extends AppCompatActivity {
    private Button btn_approved,btn_pending,
            btn_history;
    private  String requestStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_dashboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_approved=findViewById(R.id.btn_approved);
        btn_pending=findViewById(R.id.btn_pending);
        btn_history=findViewById(R.id.btn_history);

        btn_pending.setOnClickListener(v->{
            requestStatus="Pending approval";
            Intent in=new Intent(getApplicationContext(), Requests.class);
            in.putExtra("requestStatus",requestStatus);
            startActivity(in);
        });
        btn_approved.setOnClickListener(v->{
            requestStatus="Approved";
            Intent in=new Intent(getApplicationContext(), Requests.class);
            in.putExtra("requestStatus",requestStatus);
            startActivity(in);
        });

        btn_history.setOnClickListener(v->{
            requestStatus="Issued";
            Intent in=new Intent(getApplicationContext(), Requests.class);
            in.putExtra("requestStatus",requestStatus);
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