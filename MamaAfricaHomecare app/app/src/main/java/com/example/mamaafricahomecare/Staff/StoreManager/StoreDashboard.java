package com.example.mamaafricahomecare.Staff.StoreManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.mamaafricahomecare.R;

public class StoreDashboard extends AppCompatActivity {

    private Button btn_new_donations,btn_aproved_donations,
            btn_delivered_donations,btn_confirmed_donations,
            btn_items;

    private String donationStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_dashboard);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_aproved_donations=findViewById(R.id.btn_approved_donations);
        btn_new_donations=findViewById(R.id.btn_new_donations);
        btn_delivered_donations=findViewById(R.id.btn_delivered_donations);
        btn_confirmed_donations=findViewById(R.id.btn_confirmed_donations);
        btn_items=findViewById(R.id.btn_items);

        btn_items.setOnClickListener(v->{
            Intent in=new Intent(this,ItemStock.class);
            startActivity(in);
                });

        btn_new_donations.setOnClickListener(v->{
            donationStatus="Pending approval";
            Intent in=new Intent(this,NewDonations.class);
            in.putExtra("donationStatus",donationStatus);
            startActivity(in);

        });btn_aproved_donations.setOnClickListener(v->{
            donationStatus="Approved";
            Intent in=new Intent(this,NewDonations.class);
            in.putExtra("donationStatus",donationStatus);
            startActivity(in);

        });btn_delivered_donations.setOnClickListener(v->{
            donationStatus="Delivered";
            Intent in=new Intent(this,NewDonations.class);
            in.putExtra("donationStatus",donationStatus);
            startActivity(in);

        });btn_confirmed_donations.setOnClickListener(v->{
            donationStatus="Confirmed delivery";
            Intent in=new Intent(this,NewDonations.class);
            in.putExtra("donationStatus",donationStatus);
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