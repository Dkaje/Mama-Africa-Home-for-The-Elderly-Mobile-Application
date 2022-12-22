package com.example.mamaafricahomecare.Staff.Pharmacist;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_CART;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_ISSUE_DRUGS;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_SEND_TO_LAB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.Adaptors.CartAdaptor;
import com.example.mamaafricahomecare.Staff.Doctors.Model.CartModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Issue extends AppCompatActivity {

    private TextView txv_name,txv_gender,txv_dob,txv_appointmentID,
            txv_status,txv_familyMember,txv_details;
    private String appointmentID,name;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button btn_issue;


    private List<CartModel> list;
    private CartAdaptor cartAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txv_appointmentID=findViewById(R.id.txv_appointmentID);
        txv_name=findViewById(R.id.txv_name);
        txv_familyMember=findViewById(R.id.txv_familyMember);
        txv_gender=findViewById(R.id.txv_gender);
        txv_dob=findViewById(R.id.txv_dob);
        txv_status=findViewById(R.id.txv_status);
        txv_details=findViewById(R.id.txv_details);
        recyclerView=findViewById(R.id.recyclerView);
        btn_issue=findViewById(R.id.btn_issue);

        progressBar=findViewById(R.id.progressBar);
        btn_issue.setVisibility(View.GONE);

        list=new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));


        Intent in=getIntent();
        appointmentID=in.getStringExtra("appointmentID");
        name=in.getStringExtra("name");
        txv_appointmentID.setText("ID: "+in.getStringExtra("appointmentID"));
        txv_familyMember.setText("Family member "+in.getStringExtra("familyMember"));
        txv_name.setText(in.getStringExtra("name"));
        txv_gender.setText(in.getStringExtra("gender"));
        txv_dob.setText("DOB: "+in.getStringExtra("dob"));
        txv_status.setText(in.getStringExtra("dStatus"));
        txv_details.setText(in.getStringExtra("details"));

        btn_issue.setOnClickListener(v-> issueDrugs());

        try {
            if(in.getStringExtra("dStatus").equals("Pending")){
                btn_issue.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){

        }


        issuedDrugs();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void issuedDrugs(){

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_CART,
                response -> {
                    Log.e("RESPONSE",response);
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        if(status.equals("1")){
                            list.clear();
                            JSONArray jsonArray= jsonObject.getJSONArray("details");
                            for(int i =0; i<jsonArray.length();i++){
                                JSONObject jsn=jsonArray.getJSONObject(i);
                                String medID=jsn.getString("medID");
                                String drugName=jsn.getString("drugName");
                                String quantity=jsn.getString("quantity");

                                CartModel cartModel=new CartModel(medID,  drugName,  quantity  );
                                list.add(cartModel);
                            }
                            cartAdaptor=new CartAdaptor(getApplicationContext(),list);
                            recyclerView.setAdapter(cartAdaptor);
                            progressBar.setVisibility(View.GONE);


                        }else{
                            String message=jsonObject.getString("message");
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("ERROR e",e.toString());
                        progressBar.setVisibility(View.GONE);

                    }

                }, error -> {
            error.printStackTrace();
            Log.e("ERROR",error.toString());
            progressBar.setVisibility(View.GONE);

        }){
            @Override
            protected Map<String,String> getParams()throws  AbstractMethodError{
                Map<String,String> params=new HashMap<>();
                params.put("appointmentID", appointmentID);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void issueDrugs() {

        progressBar.setVisibility(View.VISIBLE);
        btn_issue.setVisibility(View.GONE);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_ISSUE_DRUGS,
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
                        btn_issue.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                        btn_issue.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }, error -> {
            error.printStackTrace();
            error.printStackTrace();
            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            btn_issue.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("appointmentID",appointmentID);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}