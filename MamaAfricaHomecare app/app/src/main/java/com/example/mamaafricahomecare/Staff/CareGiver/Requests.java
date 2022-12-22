package com.example.mamaafricahomecare.Staff.CareGiver;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_APPROVE_REQUEST;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_GET_DOCTORS;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_GET_REQUESTS;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_REQUESTED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.CareGiver.Adaptor.AdaptorDoctors;
import com.example.mamaafricahomecare.Staff.CareGiver.Adaptor.AdaptorRequest;
import com.example.mamaafricahomecare.Staff.CareGiver.Models.ModelDoctors;
import com.example.mamaafricahomecare.Staff.CareGiver.Models.RequestModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Requests extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private String requestedStatus;
    private List<RequestModel>list;
    private AdaptorRequest adaptor;
    private TextView txv_status;
    private SessionHandler session;
    private UserModel user;
    private String URL_REQUEST="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        recyclerView=findViewById(R.id.recyclerView);
        progressBar=findViewById(R.id.progressBar);
        txv_status=findViewById(R.id.txv_status);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent in=getIntent();
        requestedStatus=in.getStringExtra("requestStatus");
        getSupportActionBar().setSubtitle(requestedStatus);

        Toast.makeText(this, requestedStatus, Toast.LENGTH_SHORT).show();

        session=new SessionHandler(this);
        user=session.getUserDetails();
        list=new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        getRequests();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }
    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {

                adaptor.getFilter().filter(newText);

                return true;
            }

        });
    }


    private void getRequests() {
        try{
            if(user.getUser_type().equals("Store manager")){
                URL_REQUEST=URL_REQUESTED;
            }else if(user.getUser_type().equals("Care giver")){
                URL_REQUEST=URL_GET_REQUESTS;
            }
        }catch (Exception e){

        }

            StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REQUEST,
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
                                    String requestID=jsn.getString("requestID");
                                    String name=jsn.getString("name");
                                    String gender=jsn.getString("gender");
                                    String requestDate=jsn.getString("requestDate");
                                    String requestStatus=jsn.getString("requestStatus");
                                    RequestModel model=new RequestModel(requestID,  name, gender,  requestDate,requestStatus);
                                    list.add(model);
                                }
                                adaptor=new AdaptorRequest(getApplicationContext(),list);
                                recyclerView.setAdapter(adaptor);
                                progressBar.setVisibility(View.GONE);
                                txv_status.setVisibility(View.GONE);
                            }else{
                                String message=jsonObject.getString("message");
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                txv_status.setText(message);
                                txv_status.setVisibility(View.VISIBLE);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("ERROR e 1 ",e.toString());
                        }

                    }, error -> {
                error.printStackTrace();
                Log.e("ERROR",error.toString());
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("userID",user.getUserID());
                    params.put("requestStatus",requestedStatus);
                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        @Override
        public void onRestart()
        {
            super.onRestart();
            finish();
            startActivity(getIntent());
        }
}