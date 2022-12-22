package com.example.mamaafricahomecare.Donors;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_ADD;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_ADD_ITEM;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_CART;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_DONATE;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_DONATED_BASKETS;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_DRUGS;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_ITEMS;

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
import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;
import com.example.mamaafricahomecare.Donors.Adaptor.BasketAdaptor;
import com.example.mamaafricahomecare.Donors.Model.BasketModel;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.Adaptors.CartAdaptor;
import com.example.mamaafricahomecare.Staff.Doctors.Model.CartModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MakeDonation extends AppCompatActivity {

    private Button btn_add,btn_submit;
    private EditText edt_itemName,edt_quantity;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ArrayList<String> arrayItemName;

    private List<BasketModel> list;
    private BasketAdaptor cartAdaptor;
    private SessionHandler session;
    private UserModel user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_donation);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn_add=findViewById(R.id.btn_add);
        btn_submit=findViewById(R.id.btn_submit);
        edt_itemName=findViewById(R.id.edt_itemName);
        edt_quantity=findViewById(R.id.edt_quantity);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);

        edt_itemName.setFocusable(false);

        session=new SessionHandler(this);
        user=session.getUserDetails();

        arrayItemName=new ArrayList<>();
        list=new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        edt_itemName.setOnClickListener(v->alertItem(v));

        btn_add.setOnClickListener(v->addItem());
        btn_submit.setOnClickListener(v->submit());

        getItem();
        cart();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void alertItem(View v){
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Items");
        final String[] array = arrayItemName.toArray(new String[arrayItemName.size()]);
        builder.setNegativeButton("Close",null);
        builder.setSingleChoiceItems( array, -1, (dialogInterface, i) -> {
            edt_itemName.setText(array[i]);
            dialogInterface.dismiss();

        });
        builder.show();
    }

    public void getItem(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_ITEMS,
                response -> {
                    try {
                        Log.e("Response"," "+response);
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        if(status.equals("1")){
                            JSONArray jsonArray=jsonObject.getJSONArray("details");
                            for (int i =0; i <jsonArray.length();i++){
                                JSONObject jsn=jsonArray.getJSONObject(i);
                                String itemName=jsn.getString("itemName");
                                arrayItemName.add(itemName);

                            }
                            progressBar.setVisibility(View.GONE);

                            edt_itemName.setVisibility(View.VISIBLE);
                            btn_add.setVisibility(View.VISIBLE);
                            btn_submit.setVisibility(View.VISIBLE);
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

    private void addItem(){
        progressBar.setVisibility(View.VISIBLE);
        btn_submit.setVisibility(View.GONE);
        btn_add.setVisibility(View.GONE);

        final String itemName=edt_itemName.getText().toString().trim();
        final String quantity=edt_quantity.getText().toString().trim();

        if(TextUtils.isEmpty(itemName)||TextUtils.isEmpty(quantity)){
            Toast.makeText(getApplicationContext(), "Please enter all the details", Toast.LENGTH_LONG).show();
            btn_submit.setVisibility(View.VISIBLE);
            btn_add.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }


        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_ADD_ITEM,
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
                params.put("userID",user.getUserID());
                params.put("itemName",itemName);
                params.put("quantity",quantity);
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
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_DONATED_BASKETS,
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
                                String itemID=jsn.getString("itemID");
                                String itemName=jsn.getString("itemName");
                                String quantity=jsn.getString("quantity");

                                BasketModel model=new BasketModel(itemID,  itemName,  quantity  );
                                list.add(model);
                            }
                            cartAdaptor=new BasketAdaptor(getApplicationContext(),list);
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
                params.put("userID", user.getUserID());
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
   
    private void submit(){
        progressBar.setVisibility(View.VISIBLE);
        btn_submit.setVisibility(View.GONE);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_DONATE,
                response -> {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        Boolean status=jsonObject.getBoolean("status");
                        String msg=jsonObject.getString("message");

                        if(status.equals(true)){
                            Intent intent=new Intent(getApplicationContext(), Donations.class);
                            startActivity(intent);
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
                params.put("userID",user.getUserID());
                Log.e("values",""+params);
                return params;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}