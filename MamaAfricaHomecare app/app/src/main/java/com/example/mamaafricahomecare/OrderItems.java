package com.example.mamaafricahomecare;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_APPROVE_BOOKING;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_ORDER_BASKET;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_ORDER_ITEMS;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_UPDATE_ORDER;

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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;
import com.example.mamaafricahomecare.Staff.StoreManager.Adaptor.AdaptorCartOrders;
import com.example.mamaafricahomecare.Staff.StoreManager.Model.OrderCartModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderItems extends AppCompatActivity {

    private Button btn_submit;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private List<OrderCartModel> list;
    private AdaptorCartOrders adaptor;
    private String requestID,requestStatus,updateStatus;
    private SessionHandler session;
    private UserModel user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_items);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_submit=findViewById(R.id.btn_submit);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);

        btn_submit.setVisibility(View.GONE);

        Intent in=getIntent();
        getSupportActionBar().setSubtitle(in.getStringExtra("name"));
        requestID=in.getStringExtra("requestID");
        requestStatus=in.getStringExtra("requestStatus");

        session=new SessionHandler(this);
        user=session.getUserDetails();

        list=new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        try{
            if(user.getUser_type().equals("Supplier")){
                if(requestStatus.equals("Pending approval")){
                    btn_submit.setVisibility(View.VISIBLE);
                    btn_submit.setText("Approved order");
                    updateStatus="Approved";
                } if(requestStatus.equals("Approved")){
                    btn_submit.setVisibility(View.VISIBLE);
                    btn_submit.setText("Mark delivered");
                    updateStatus="Delivered";
                }

            }
            if(user.getUser_type().equals("Store manager")){

                if(requestStatus.equals("Delivered")){
                    btn_submit.setVisibility(View.VISIBLE);
                    btn_submit.setText("Confirm delivery");

                    updateStatus="Confirmed delivery";
                    Toast.makeText(this, requestStatus, Toast.LENGTH_SHORT).show();
                }

            }
        }catch (Exception e){

        }

        btn_submit.setOnClickListener(v-> updateOrder());


        cart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void cart(){
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_ORDER_ITEMS,
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
                                String id=jsn.getString("id");
                                String drugName=jsn.getString("drugName");
                                String quantity=jsn.getString("quantity");
                                OrderCartModel model=new OrderCartModel(id,  drugName,  quantity  );
                                list.add(model);
                            }
                            adaptor=new AdaptorCartOrders(getApplicationContext(),list);
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


    private void updateOrder(){
        btn_submit.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_UPDATE_ORDER,
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
                        btn_submit.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                    }
                }, error -> {
            error.printStackTrace();
            Log.e("ERROR"," "+error.toString());
            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            btn_submit.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("requestID",requestID);
                params.put("updateStatus",updateStatus);
                Log.e("PARAMS "," "+params);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}