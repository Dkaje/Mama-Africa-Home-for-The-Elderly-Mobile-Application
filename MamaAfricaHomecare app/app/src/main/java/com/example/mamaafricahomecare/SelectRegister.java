package com.example.mamaafricahomecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mamaafricahomecare.Donors.DonorRegister;
import com.example.mamaafricahomecare.Supplier.RegisterSuppliers;

public class SelectRegister extends AppCompatActivity {

    private Button btn_donor,btn_fam,btn_supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_register);

        btn_donor=findViewById(R.id.btn_donor);
        btn_fam=findViewById(R.id.btn_fam);
        btn_supplier=findViewById(R.id.btn_supplier);

        btn_donor.setOnClickListener(v->{
            Intent in=new Intent(this, DonorRegister.class);
            startActivity(in);
        });
        btn_fam.setOnClickListener(v->{
            Intent in=new Intent(this, Register.class);
            startActivity(in);
        });
        btn_supplier.setOnClickListener(v->{
            Intent in=new Intent(this, RegisterSuppliers.class);
            startActivity(in);
        });


    }
}