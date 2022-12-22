package com.example.mamaafricahomecare.Supplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.StoreManager.Suppliers;

public class SupplierDashboard extends AppCompatActivity {

    private Button btn_stock_in,btn_pending,
            btn_delivered,btn_approved;
    private String requestStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_dashboard);

        btn_stock_in=findViewById(R.id.btn_stock_in);
        btn_pending=findViewById(R.id.btn_pending);
        btn_delivered=findViewById(R.id.btn_delivered);
        btn_approved=findViewById(R.id.btn_approved);



        btn_pending.setOnClickListener(v->{
            requestStatus="Pending approval";
            Intent in=new Intent(this, DrugsSuppliesOrdered.class);
            in.putExtra("requestStatus",requestStatus);
            startActivity(in);
        });
        btn_approved.setOnClickListener(v->{
            requestStatus="Approved";
            Intent in=new Intent(this, DrugsSuppliesOrdered.class);
            in.putExtra("requestStatus",requestStatus);
            startActivity(in);
        });
        btn_delivered.setOnClickListener(v->{
            requestStatus="Delivered";
            Intent in=new Intent(this, DrugsSuppliesOrdered.class);
            in.putExtra("requestStatus",requestStatus);
            startActivity(in);
        });
        btn_stock_in.setOnClickListener(v->{
            requestStatus="Confirmed delivery";
            Intent in=new Intent(this, DrugsSuppliesOrdered.class);
            in.putExtra("requestStatus",requestStatus);
            startActivity(in);
        });
    }
}