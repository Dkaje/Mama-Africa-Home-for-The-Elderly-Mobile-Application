package com.example.mamaafricahomecare.Member;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;
import com.example.mamaafricahomecare.R;

public class Profile extends AppCompatActivity {
    private SessionHandler session;
    private UserModel user;
    private TextView txv_name,txv_username,txv_email,
            txv_date,txv_phoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        txv_date=findViewById(R.id.txv_date);
        txv_name=findViewById(R.id.txv_name);
        txv_username=findViewById(R.id.txv_username);
        txv_email=findViewById(R.id.txv_email);
        txv_phoneNo=findViewById(R.id.txv_phoneNo);

        session=new SessionHandler(this);
        user=session.getUserDetails();
        try {
            txv_email.setText(user.getEmail());
            txv_name.setText(user.getFirstname()+" "+user.getLastname());
            txv_username.setText(user.getUsername());
            txv_phoneNo.setText(user.getPhoneNo());
            txv_date.setText("Date "+user.getDateCreated());

            getSupportActionBar().setSubtitle(user.getUser_type());

        }catch (Exception e){

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}