package com.example.mamaafricahomecare.Staff.Doctors;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_LOGIN;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_LOGIN_STAFF;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_SEND_TO_LAB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.MainActivity;
import com.example.mamaafricahomecare.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SentToLab extends AppCompatActivity {

    private Button btn_sendToLab;
    private ProgressBar progressBar;
    private EditText edt_details;
    private TextView txv_name;
    private String appointmentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_to_lab);

        txv_name=findViewById(R.id.txv_name);
        progressBar=findViewById(R.id.progressBar);
        btn_sendToLab=findViewById(R.id.btn_sendToLab);
        edt_details=findViewById(R.id.edt_details);

        progressBar.setVisibility(View.GONE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recommend test");

        Intent in=getIntent();
        appointmentID=in.getStringExtra("appointmentID");
        txv_name.setText(in.getStringExtra("name"));

        btn_sendToLab.setOnClickListener(v->SendToLab());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void SendToLab() {
        
            progressBar.setVisibility(View.VISIBLE);
            btn_sendToLab.setVisibility(View.GONE);


            final String details = edt_details.getText().toString().trim();



            if(TextUtils.isEmpty(details)){
                Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
                btn_sendToLab.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                return;

            }

            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_SEND_TO_LAB,
                    response -> {
                        Log.e("Response","is" + response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            String msg=jsonObject.getString("message");
                            if(status.equals("1")){
                                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                            }
                            btn_sendToLab.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                            btn_sendToLab.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }, error -> {
                error.printStackTrace();
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                btn_sendToLab.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("appointmentID",appointmentID);
                    params.put("details",details);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

        }
}