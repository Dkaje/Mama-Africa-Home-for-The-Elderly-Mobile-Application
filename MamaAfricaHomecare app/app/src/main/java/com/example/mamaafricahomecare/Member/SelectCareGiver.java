package com.example.mamaafricahomecare.Member;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;
import com.example.mamaafricahomecare.Member.Adaptors.AdaptorAssignedCareGiver;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.Adaptors.AdaptorCareGiver;
import com.example.mamaafricahomecare.Staff.Supervisor.Model.ModelCareGiver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectCareGiver extends AppCompatActivity {

    private TextView txv_status;
    private RecyclerView recyclerView;
    private List<ModelCareGiver> list;
    private AdaptorAssignedCareGiver adaptorCareGiver;
    private ProgressBar progressBar;
    private SessionHandler session;
    private UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_care_giver);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txv_status=findViewById(R.id.txv_status);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);


        session=new SessionHandler(this);
        user=session.getUserDetails();

        list=new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));


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

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_GET_CARE_GIVER,
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
                            adaptorCareGiver=new AdaptorAssignedCareGiver(getApplicationContext(),list);
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
        });
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