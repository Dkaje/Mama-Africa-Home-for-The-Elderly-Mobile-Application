package com.example.mamaafricahomecare.Staff.Doctors;

import static com.example.mamaafricahomecare.ConstFiles.Urls.APPROVE_ELDER;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_APPROVE_BOOKING;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.CareGiver.Doctors;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BookingDetails extends AppCompatActivity {
    private TextView txv_name,txv_gender,txv_dob,txv_appointmentID,
            txv_status,txv_familyMember,txv_appointmentDate,txv_details;
    private Button btn_report,btn_lab,btn_approve;
    private RelativeLayout layout_buttons;
    private String appointmentID,name;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txv_appointmentID=findViewById(R.id.txv_appointmentID);
        txv_name=findViewById(R.id.txv_name);
        txv_familyMember=findViewById(R.id.txv_familyMember);
        txv_gender=findViewById(R.id.txv_gender);
        txv_dob=findViewById(R.id.txv_dob);
        txv_status=findViewById(R.id.txv_status);
        txv_appointmentDate=findViewById(R.id.txv_appointmentDate);
        txv_details=findViewById(R.id.txv_details);
        btn_lab=findViewById(R.id.btn_lab);
        btn_report=findViewById(R.id.btn_report);
        btn_approve=findViewById(R.id.btn_approve);
        layout_buttons=findViewById(R.id.layout_buttons);
        progressBar=findViewById(R.id.progressBar);

        btn_approve.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        layout_buttons.setVisibility(View.GONE);


        Intent in=getIntent();
        appointmentID=in.getStringExtra("appointmentID");
        name=in.getStringExtra("name");
        txv_appointmentID.setText("ID: "+in.getStringExtra("appointmentID"));
        txv_familyMember.setText("Family member "+in.getStringExtra("familyMember"));
        txv_name.setText(in.getStringExtra("name"));
        txv_gender.setText(in.getStringExtra("gender"));
        txv_dob.setText("DOB: "+in.getStringExtra("dob"));
        txv_appointmentDate.setText("Appointment date: "+in.getStringExtra("appointmentDate"));
        txv_status.setText(in.getStringExtra("appointmentStatus"));
        txv_details.setText(in.getStringExtra("details"));

        if(in.getStringExtra("appointmentStatus").equals("Pending approval")){
            btn_approve.setVisibility(View.VISIBLE);
        }
        if(in.getStringExtra("appointmentStatus").equals("Approved")){
            layout_buttons.setVisibility(View.VISIBLE);
        }

        btn_approve.setOnClickListener(v->approve());

        btn_lab.setOnClickListener(v->{
            Intent n=new Intent(this, SentToLab.class);
            n.putExtra("appointmentID", appointmentID);
            n.putExtra("name", in.getStringExtra("name"));
            startActivity(n);
            finish();
        });
        btn_report.setOnClickListener(v->{
            Intent n=new Intent(this, SendReport.class);
            n.putExtra("appointmentID", appointmentID);
            n.putExtra("name", in.getStringExtra("name"));
            startActivity(n);
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

    private void approve(){
        btn_approve.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_APPROVE_BOOKING,
                response -> {

                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        if(status.equals("1")){
                            finish();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("E "," "+e.toString());
                        btn_approve.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                    }
                }, error -> {
            error.printStackTrace();
            Log.e("ERROR"," "+error.toString());
            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            btn_approve.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("appointmentID",appointmentID);
                Log.e("PARAMS "," "+params);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}