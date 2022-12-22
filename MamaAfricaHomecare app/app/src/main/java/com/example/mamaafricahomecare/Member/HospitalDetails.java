package com.example.mamaafricahomecare.Member;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_GET_APPOINTMENT_DOCTOR;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_GET_APPOINTMENT_DRUGS;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_GET_TEST_DONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.print.PrintHelper;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.Adaptor.IssueDrugAdaptor;
import com.example.mamaafricahomecare.Model.IssuedDrugsModel;
import com.example.mamaafricahomecare.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalDetails extends AppCompatActivity {
    private TextView txv_name,txv_gender,txv_dob,txv_appointmentID,
            txv_status,txv_careGiver,txv_details,txv_receipt;
    private ProgressBar progressBar;
    private String appointmentID;
    private LinearLayout layout_drugs, layout_test;
    private TextView txv_drug_status,txv_test,txv_doctorName;
    private RecyclerView recyclerView;
    private List<IssuedDrugsModel> list;
    private IssueDrugAdaptor adaptor;
    private Button btn_print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txv_appointmentID=findViewById(R.id.txv_appointmentID);
        txv_name=findViewById(R.id.txv_name);
        txv_gender=findViewById(R.id.txv_gender);
        txv_dob=findViewById(R.id.txv_dob);
        txv_careGiver=findViewById(R.id.txv_careGiverName);
        txv_status=findViewById(R.id.txv_status);
        txv_details=findViewById(R.id.txv_details);
        progressBar=findViewById(R.id.progressBar);
        layout_test=findViewById(R.id.layout_test);
        layout_drugs=findViewById(R.id.layout_drugs);
        recyclerView=findViewById(R.id.recyclerView);
        txv_receipt=findViewById(R.id.txv_receipt);
        btn_print=findViewById(R.id.btn_print);

        txv_doctorName=findViewById(R.id.txv_doctorName);
        txv_test=findViewById(R.id.txv_test);
        txv_drug_status=findViewById(R.id.txv_drugs_status);

        layout_test.setVisibility(View.GONE);
        layout_drugs.setVisibility(View.GONE);
        txv_receipt.setVisibility(View.GONE);

        Intent in=getIntent();
        appointmentID=in.getStringExtra("appointmentID");
        txv_appointmentID.setText("ID: "+in.getStringExtra("appointmentID"));
        txv_name.setText(in.getStringExtra("name"));
        txv_gender.setText(in.getStringExtra("gender"));
        txv_dob.setText("DOB: "+in.getStringExtra("dob"));
        txv_status.setText(in.getStringExtra("appointmentStatus"));
        txv_details.setText(in.getStringExtra("details"));
        txv_careGiver.setText(in.getStringExtra("careGiver"));

        list=new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        btn_print.setOnClickListener(v->print());
        getDoctor();
        getTest();
        getDrugs();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDoctor(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_GET_APPOINTMENT_DOCTOR,
                response -> {
                    Log.e("RESPONSE",response);
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        if(status.equals("1")){
                            txv_doctorName.setText(jsonObject.getString("docDetails"));
                        }else{
                            txv_doctorName.setText(jsonObject.getString("message"));
                        }
                        progressBar.setVisibility(View.GONE);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("ERROR e 1 ",e.toString());
                    }

                }, error -> {
            error.printStackTrace();
            Log.e("ERROR",error.toString());
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("appointmentID",appointmentID);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getTest(){

        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_GET_TEST_DONE,
                response -> {
                    Log.e("RESPONSE",response);
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        if(status.equals("1")){
                            txv_test.setText(jsonObject.getString("testDetails"));
                            layout_test.setVisibility(View.VISIBLE);
                        }
                        progressBar.setVisibility(View.GONE);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("ERROR e 1 ",e.toString());
                    }

                }, error -> {
            error.printStackTrace();
            Log.e("ERROR",error.toString());
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("appointmentID",appointmentID);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void getDrugs(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_GET_APPOINTMENT_DRUGS,
                response -> {
                    Log.e("RESPONSE",response);
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        if(status.equals("1")){

                            JSONArray jsonArray= jsonObject.getJSONArray("details");
                            for(int i =0; i<jsonArray.length();i++){
                                JSONObject jsn=jsonArray.getJSONObject(i);
                                String drugName=jsn.getString("drugName");
                                String quantity=jsn.getString("quantity");
                                IssuedDrugsModel model=new IssuedDrugsModel(drugName,quantity);
                                list.add(model);
                            }
                            txv_drug_status.setText(jsonObject.getString("drugStatus"));
                            adaptor=new IssueDrugAdaptor(getApplicationContext(),list);
                            recyclerView.setAdapter(adaptor);
                            progressBar.setVisibility(View.GONE);
                            layout_drugs.setVisibility(View.VISIBLE);
                        }else{
                            String message=jsonObject.getString("message");
                            progressBar.setVisibility(View.GONE);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("ERROR e 1 ",e.toString());
                    }

                }, error -> {
            error.printStackTrace();
            Log.e("ERROR",error.toString());
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("appointmentID",appointmentID);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void print(){

        try{

            btn_print.setVisibility(View.GONE);

            txv_receipt.setVisibility(View.VISIBLE);

            View view = getWindow().getDecorView().findViewById(android.R.id.content);
            view.setDrawingCacheEnabled(true);
            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),View. MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.buildDrawingCache(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            PrintHelper photoPrinter = new PrintHelper(this); // Asume that 'this' is your activity
            photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
            photoPrinter.printBitmap("print receipt", bitmap);

            txv_receipt.setVisibility(View.VISIBLE);
            btn_print.setVisibility(View.VISIBLE);

        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}