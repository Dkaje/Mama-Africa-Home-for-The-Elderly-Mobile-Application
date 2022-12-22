package com.example.mamaafricahomecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mamaafricahomecare.Staff.StoreManager.Drugs;
import com.example.mamaafricahomecare.Staff.StoreManager.OrderRequest;

public class DrugsDashbboard extends AppCompatActivity {

    private Button btn_drugs,btn_stock_in,btn_pending,
            btn_delivered,btn_approved;
    private String requestStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_dashbboard);

        btn_drugs=findViewById(R.id.btn_drugs);
        btn_stock_in=findViewById(R.id.btn_stock_in);
        btn_pending=findViewById(R.id.btn_pending);
        btn_delivered=findViewById(R.id.btn_delivered);
        btn_approved=findViewById(R.id.btn_approved);


        btn_drugs.setOnClickListener(v->{
            Intent in=new Intent(this, Drugs.class);
            startActivity(in);
        });
        btn_pending.setOnClickListener(v->{
            requestStatus="Pending approval";
            Intent in=new Intent(this, OrderRequest.class);
            in.putExtra("requestStatus",requestStatus);
            startActivity(in);
        });
        btn_approved.setOnClickListener(v->{
            requestStatus="Approved";
            Intent in=new Intent(this, OrderRequest.class);
            in.putExtra("requestStatus",requestStatus);
            startActivity(in);
        });
        btn_delivered.setOnClickListener(v->{
            requestStatus="Delivered";
            Intent in=new Intent(this, OrderRequest.class);
            in.putExtra("requestStatus",requestStatus);
            startActivity(in);
        });
        btn_stock_in.setOnClickListener(v->{
            requestStatus="Confirmed delivery";
            Intent in=new Intent(this, OrderRequest.class);
            in.putExtra("requestStatus",requestStatus);
            startActivity(in);
        });
    }
}