package com.example.mamaafricahomecare.Staff.Doctors;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_ADD;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_CART;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_DRUGS;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_SEND_REPORT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class SendReport extends AppCompatActivity {

    private Button btn_add,btn_submit;
    private EditText edt_report,edt_drugName,edt_quantity;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<String> arrayDrugs;
    private String appointmentID;

    private List<CartModel> list;
    private CartAdaptor cartAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_report);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_add=findViewById(R.id.btn_add);
        btn_submit=findViewById(R.id.btn_submit);
        edt_report=findViewById(R.id.edt_report);
        edt_quantity=findViewById(R.id.edt_quantity);
        edt_drugName=findViewById(R.id.edt_drugName);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);


        edt_drugName.setFocusable(false);

        edt_drugName.setVisibility(View.GONE);
        btn_add.setVisibility(View.GONE);
        btn_submit.setVisibility(View.GONE);
        edt_report.setVisibility(View.GONE);

        Intent in=getIntent();
        appointmentID=in.getStringExtra("appointmentID");

//        Toast.makeText(this, appointmentID, Toast.LENGTH_SHORT).show();
        arrayDrugs=new ArrayList<>();
        list=new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        edt_drugName.setOnClickListener(v->alertDrugs(v));
        btn_add.setOnClickListener(v->addDug());
        btn_submit.setOnClickListener(v->sendReportNow());

        getDrugs();
        cart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void alertDrugs(View v){
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Drugs");
        final String[] array = arrayDrugs.toArray(new String[arrayDrugs.size()]);
        builder.setNegativeButton("Close",null);
        builder.setSingleChoiceItems( array, -1, (dialogInterface, i) -> {
            edt_drugName.setText(array[i]);
            dialogInterface.dismiss();

        });
        builder.show();
    }

    public void getDrugs(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_DRUGS,
                response -> {
                    try {
                        Log.e("Response"," "+response);
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        if(status.equals("1")){
                            JSONArray jsonArray=jsonObject.getJSONArray("details");
                            for (int i =0; i <jsonArray.length();i++){
                                JSONObject jsn=jsonArray.getJSONObject(i);
                                String drugName=jsn.getString("drugName");
                                arrayDrugs.add(drugName);

                            }
                            progressBar.setVisibility(View.GONE);

                            edt_drugName.setVisibility(View.VISIBLE);
                            btn_add.setVisibility(View.VISIBLE);
                            btn_submit.setVisibility(View.VISIBLE);
                            edt_report.setVisibility(View.VISIBLE);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                    }

                }, error -> {
            error.printStackTrace();
            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void addDug(){
        progressBar.setVisibility(View.VISIBLE);
        btn_submit.setVisibility(View.GONE);
        btn_add.setVisibility(View.GONE);

        final String drugName=edt_drugName.getText().toString().trim();
        final String quantity=edt_quantity.getText().toString().trim();

        if(TextUtils.isEmpty(drugName)||TextUtils.isEmpty(quantity)){
            Toast.makeText(getApplicationContext(), "Please enter all the details", Toast.LENGTH_LONG).show();
            btn_submit.setVisibility(View.VISIBLE);
            btn_add.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_ADD,
                response -> {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        String msg=jsonObject.getString("message");

                        if(status.equals("1")){

                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                            cart();
                        }else{
                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);

                        }
                        btn_submit.setVisibility(View.VISIBLE);
                        btn_add.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);


                    }catch (Exception e){
                        e.printStackTrace();
                        Log.e("Error e",e.toString());
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        btn_submit.setVisibility(View.VISIBLE);
                        btn_add.setVisibility(View.VISIBLE);
                    }

                }, error -> {
            error.printStackTrace();
            Log.e("Error error",error.toString());
            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            btn_submit.setVisibility(View.VISIBLE);
            btn_add.setVisibility(View.VISIBLE);
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("appointmentID",appointmentID);
                params.put("drugName",drugName);
                params.put("quantity",quantity);
                Log.e("values",""+params);
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }

    private void sendReportNow(){
        progressBar.setVisibility(View.VISIBLE);
        btn_submit.setVisibility(View.GONE);

        final String report=edt_report.getText().toString().trim();

        if(TextUtils.isEmpty(report)){
            Toast.makeText(getApplicationContext(), "Write a report", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            btn_submit.setVisibility(View.VISIBLE);
            return;
        }


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_SEND_REPORT,
                response -> {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        Boolean status=jsonObject.getBoolean("status");
                        String msg=jsonObject.getString("message");

                        if(status.equals(true)){
                                 finish();
                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                        }else if(status.equals(false)){
                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            btn_submit.setVisibility(View.VISIBLE);

                        }

                    }catch (Exception e){
                        e.printStackTrace();
                        Log.e("Error e",e.toString());
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        btn_submit.setVisibility(View.VISIBLE);
                    }

                }, error -> {
            error.printStackTrace();
            Log.e("Error error",error.toString());
            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            btn_submit.setVisibility(View.VISIBLE);
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("appointmentID",appointmentID);
                params.put("report",report);
                Log.e("values",""+params);
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
    private void cart(){

        progressBar.setVisibility(View.VISIBLE);
        btn_submit.setVisibility(View.GONE);
        btn_add.setVisibility(View.GONE);
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
                            btn_submit.setVisibility(View.VISIBLE);
                            btn_add.setVisibility(View.VISIBLE);

                        }else{
                            String message=jsonObject.getString("message");
                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            btn_submit.setVisibility(View.VISIBLE);
                            btn_add.setVisibility(View.VISIBLE);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("ERROR e",e.toString());
                        progressBar.setVisibility(View.GONE);
                        btn_submit.setVisibility(View.VISIBLE);
                        btn_add.setVisibility(View.VISIBLE);
                    }

                }, error -> {
            error.printStackTrace();
            Log.e("ERROR",error.toString());
            progressBar.setVisibility(View.GONE);
            btn_submit.setVisibility(View.VISIBLE);
            btn_add.setVisibility(View.VISIBLE);
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
}