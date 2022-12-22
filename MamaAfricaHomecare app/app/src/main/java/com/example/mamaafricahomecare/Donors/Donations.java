package com.example.mamaafricahomecare.Donors;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_BOOKINGS;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_DONATIONS;

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
import com.example.mamaafricahomecare.Donors.Adaptor.AdaptorDonations;
import com.example.mamaafricahomecare.Donors.Model.DonationModel;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.Doctors.Adaptor.AdaptorBookings;
import com.example.mamaafricahomecare.Staff.Doctors.Model.BookingModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Donations extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<DonationModel> list;
    private AdaptorDonations adaptor;
    private TextView txv_status;
    private SessionHandler session;
    private UserModel user;
    private String appointmentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);
        txv_status=findViewById(R.id.txv_status);

        session=new SessionHandler(this);
        user=session.getUserDetails();

        getSupportActionBar().setSubtitle("Donations");
        list=new ArrayList<>();
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        donations();
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
    private void donations(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_DONATIONS,
                response -> {
                    Log.e("RESPONSE",response);
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        if(status.equals("1")){

                            JSONArray jsonArray= jsonObject.getJSONArray("details");
                            for(int i =0; i<jsonArray.length();i++){
                                JSONObject jsn=jsonArray.getJSONObject(i);
                                String donationID=jsn.getString("donationID");
                                String donationStatus=jsn.getString("donationStatus");
                                String donationDate=jsn.getString("donationDate");
                                String donationRemarks=jsn.getString("donationRemarks");

                                DonationModel model=new DonationModel( donationID,  donationStatus,
                                         donationDate,  donationRemarks);
                                list.add(model);
                            }
                            adaptor=new AdaptorDonations(getApplicationContext(),list);
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
                        Log.e("ERROR e",e.toString());
                    }

                }, error -> {
            error.printStackTrace();
            Log.e("ERROR",error.toString());
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userID",user.getUserID());
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