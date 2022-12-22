package com.example.mamaafricahomecare;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_APPROVE_BOOKING;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_APPROVE_REQUEST;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_BASKET;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_ISSUE_REQUEST;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_ITEMS_REQUESTED;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_REQUESTED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.Adaptor.ItemDetailAdaptor;
import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;
import com.example.mamaafricahomecare.Model.ItemDetailModel;
import com.example.mamaafricahomecare.Staff.Adaptors.ItemRequestAdaptor;
import com.example.mamaafricahomecare.Staff.Model.ItemCartModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestDetails extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private TextView txv_requestDI,txv_name,txv_gender,
            txv_requestDate,txv_requestStatus;
    private List<ItemDetailModel>list;
    private ItemDetailAdaptor adaptor;
    private String requestID;
    private SessionHandler session;
    private UserModel user;
    private Button btn_approve;
    private String URL_REQUEST="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);
        txv_requestDI=findViewById(R.id.txv_requestID);
        txv_name=findViewById(R.id.txv_name);
        txv_gender=findViewById(R.id.txv_gender);
        txv_requestDate=findViewById(R.id.txv_requestDate);
        txv_requestStatus=findViewById(R.id.txv_status);
        btn_approve=findViewById(R.id.btn_approve);

        session=new SessionHandler(this);
        user=session.getUserDetails();
        btn_approve.setVisibility(View.GONE);


        Intent in=getIntent();
        requestID=in.getStringExtra("requestID");
        txv_requestDI.setText("Request ID: "+in.getStringExtra("requestID"));
        txv_name.setText(in.getStringExtra("name"));
        txv_gender.setText(in.getStringExtra("gender"));
        txv_requestDate.setText("Requested date: "+in.getStringExtra("requestDate"));
        txv_requestStatus.setText(in.getStringExtra("requestStatus"));


        try{
            if(user.getUser_type().equals("Store manager")){
                if(in.getStringExtra("requestStatus").equals("Pending approval")){
                    btn_approve.setVisibility(View.VISIBLE);
                    btn_approve.setText("Approve");
                }
                if(in.getStringExtra("requestStatus").equals("Approved")){
                    btn_approve.setVisibility(View.VISIBLE);
                    btn_approve.setText("Issued items");
                }
            }
        }catch (Exception e){

        }

        list=new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        btn_approve.setOnClickListener(v->{
            if(in.getStringExtra("requestStatus").equals("Pending approval")){

                approve();
            }
            if(in.getStringExtra("requestStatus").equals("Approved")){

                issueItems();
            }
        });

        getItemsRequested();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void getItemsRequested() {

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_ITEMS_REQUESTED,
                response -> {
                    Log.e("RESPONSE BASKET ",response);
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        if(status.equals("1")){
                            list.clear();
                            JSONArray jsonArray= jsonObject.getJSONArray("details");
                            for(int i =0; i<jsonArray.length();i++){
                                JSONObject jsn=jsonArray.getJSONObject(i);
                                String itemName=jsn.getString("itemName");
                                String quantity=jsn.getString("quantity");
                                String itemDetail=jsn.getString("itemDetail");
                                ItemDetailModel model=new ItemDetailModel(itemName,  quantity,  itemDetail );
                                list.add(model);
                            }
                            adaptor=new ItemDetailAdaptor(getApplicationContext(),list);
                            recyclerView.setAdapter(adaptor);
                        }else{
                            String message=jsonObject.getString("message");
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("ERROR e BASKET ",e.toString());
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
                params.put("requestID", requestID);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void approve(){

            btn_approve.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);


            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_APPROVE_REQUEST,
                    response -> {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            if(status.equals("1")){
                                finish();
                            }else {
                                String message=jsonObject.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                                btn_approve.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
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
                    params.put("requestID",requestID);
                    Log.e("PARAMS "," "+params);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    private void issueItems(){

            btn_approve.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);


            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_ISSUE_REQUEST,
                    response -> {

                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            if(status.equals("1")){
                                finish();
                            }else{
                                String message=jsonObject.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                                btn_approve.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
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
                    params.put("requestID",requestID);
                    Log.e("PARAMS "," "+params);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

}