package com.example.mamaafricahomecare.Staff.Supervisor;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_CARE_GIVER_ASSIGNED;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_GET_CARE_GIVER;

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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.Adaptors.AdaptorCareGiver;
import com.example.mamaafricahomecare.Staff.Supervisor.Model.ModelCareGiver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CareGiverAssigned extends AppCompatActivity {

    private TextView txv_name,txv_gender,txv_dob,txv_elderID,
            txv_dateAdded,txv_status,txv_familyMember;
    private Button btn_approve;
    String elderID,elderStatus;
    private RecyclerView recyclerView;
    private List<ModelCareGiver> list;
    private AdaptorCareGiver adaptorCareGiver;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_giver_assigned);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setSubtitle("Details");

        txv_elderID=findViewById(R.id.txv_elderID);
        txv_name=findViewById(R.id.txv_name);
        txv_familyMember=findViewById(R.id.txv_familyMember);
        txv_gender=findViewById(R.id.txv_gender);
        txv_dob=findViewById(R.id.txv_dob);
        txv_dateAdded=findViewById(R.id.txv_dateAdded);
        txv_status=findViewById(R.id.txv_status);
        btn_approve=findViewById(R.id.btn_approve);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);



        list=new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        Intent in=getIntent();
        elderID=in.getStringExtra("elderID");
        elderStatus=in.getStringExtra("elderStatus");
        txv_elderID.setText("ID: "+in.getStringExtra("elderID"));
        txv_familyMember.setText("FamilyMember "+in.getStringExtra("familyMember"));
        txv_name.setText(in.getStringExtra("name"));
        txv_gender.setText(in.getStringExtra("gender"));
        txv_dob.setText("DOB: "+in.getStringExtra("dob"));
        txv_dateAdded.setText("Date: "+in.getStringExtra("dateAdded"));
        txv_status.setText(" "+in.getStringExtra("elderStatus"));


        getCareGivers();

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

                adaptorCareGiver.getFilter().filter(newText);

                return true;
            }

        });
    }


    private void getCareGivers(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_CARE_GIVER_ASSIGNED,
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
                                String staffID=jsn.getString("staffID");
                                String name=jsn.getString("name");
                                String phoneNo=jsn.getString("phoneNo");
                                ModelCareGiver modelCareGiver=new ModelCareGiver(staffID,  name,  phoneNo);
                                list.add(modelCareGiver);
                            }
                            adaptorCareGiver=new AdaptorCareGiver(getApplicationContext(),list,elderID,elderStatus);
                            recyclerView.setAdapter(adaptorCareGiver);
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
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("elderID",elderID);
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