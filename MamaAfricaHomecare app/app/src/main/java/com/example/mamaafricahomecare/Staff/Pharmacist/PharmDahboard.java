package com.example.mamaafricahomecare.Staff.Pharmacist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.mamaafricahomecare.R;

public class PharmDahboard extends AppCompatActivity {

    private Button btn_collected,btn_pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharm_dahboard);

        btn_collected=findViewById(R.id.btn_collected);
        btn_pending=findViewById(R.id.btn_pending);


        btn_collected.setOnClickListener(v->{
            Intent in=new Intent(this,CollectedDrugs.class);
            startActivity(in);
        });

        btn_pending.setOnClickListener(v->{
            Intent in=new Intent(this,PendingDrugs.class);
            startActivity(in);
        });
    }
}