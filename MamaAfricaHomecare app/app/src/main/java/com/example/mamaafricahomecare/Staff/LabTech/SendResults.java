package com.example.mamaafricahomecare.Staff.LabTech;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_SEND_RESULTS;
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
import com.example.mamaafricahomecare.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendResults extends AppCompatActivity {

    private Button btn_submit;
    private ProgressBar progressBar;
    private EditText edt_results;
    private TextView txv_test;
    private String appointmentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_results);

        txv_test=findViewById(R.id.txv_test);
        progressBar=findViewById(R.id.progressBar);
        btn_submit=findViewById(R.id.btn_submit);
        edt_results=findViewById(R.id.edt_result);

        progressBar.setVisibility(View.GONE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Test result");

        Intent in=getIntent();
        appointmentID=in.getStringExtra("appointmentID");
        txv_test.setText(in.getStringExtra("test"));

        if(in.getStringExtra("testStatus").equals("Test done")){
            edt_results.setVisibility(View.GONE);
            btn_submit.setVisibility(View.GONE);

            txv_test.setText(in.getStringExtra("test")+"\nResults: "+in.getStringExtra("testResult"));
        }

        btn_submit.setOnClickListener(v->SendResults());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void SendResults() {

        progressBar.setVisibility(View.VISIBLE);
        btn_submit.setVisibility(View.GONE);


        final String results = edt_results.getText().toString().trim();



        if(TextUtils.isEmpty(results)){
            Toast.makeText(getApplicationContext(), "Write test results", Toast.LENGTH_SHORT).show();
            btn_submit.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;

        }

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_SEND_RESULTS,
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
                        btn_submit.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                        btn_submit.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }, error -> {
            error.printStackTrace();
            error.printStackTrace();
            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            btn_submit.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("appointmentID",appointmentID);
                params.put("results",results);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}