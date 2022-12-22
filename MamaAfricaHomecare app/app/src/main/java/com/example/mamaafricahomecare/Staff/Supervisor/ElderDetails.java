package com.example.mamaafricahomecare.Staff.Supervisor;

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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ElderDetails extends AppCompatActivity {
    private TextView txv_name,txv_gender,txv_dob,txv_elderID,
            txv_dateAdded,txv_status,txv_familyMember;
    private Button btn_approve;
    private ProgressBar progressBar;
    private String elderID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elder_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txv_elderID=findViewById(R.id.txv_elderID);
        txv_name=findViewById(R.id.txv_name);
        txv_familyMember=findViewById(R.id.txv_familyMember);
        txv_gender=findViewById(R.id.txv_gender);
        txv_dob=findViewById(R.id.txv_dob);
        txv_dateAdded=findViewById(R.id.txv_dateAdded);
        txv_status=findViewById(R.id.txv_status);
        btn_approve=findViewById(R.id.btn_approve);
        progressBar=findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        Intent in=getIntent();
        elderID=in.getStringExtra("elderID");
        txv_elderID.setText("ID: "+in.getStringExtra("elderID"));
        txv_familyMember.setText("FamilyMember "+in.getStringExtra("familyMember"));
        txv_name.setText(in.getStringExtra("name"));
        txv_gender.setText(in.getStringExtra("gender"));
        txv_dob.setText("DOB: "+in.getStringExtra("dob"));
        txv_dateAdded.setText("Date: "+in.getStringExtra("dateAdded"));
        txv_status.setText(" "+in.getStringExtra("elderStatus"));

        btn_approve.setOnClickListener(v->approve());
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

        StringRequest stringRequest=new StringRequest(Request.Method.POST, APPROVE_ELDER,
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
                params.put("elderID",elderID);
                Log.e("PARAMS "," "+params);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}