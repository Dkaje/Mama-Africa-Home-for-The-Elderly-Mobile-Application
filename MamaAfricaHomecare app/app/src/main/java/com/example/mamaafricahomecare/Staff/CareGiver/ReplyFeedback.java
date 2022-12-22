package com.example.mamaafricahomecare.Staff.CareGiver;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_FEEDBACK;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_GET_FEEDBACK;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_REPLY_FEEDBACK;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_SEND_FEEDBACK;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;
import com.example.mamaafricahomecare.Member.Adaptors.AdapterFeedback;
import com.example.mamaafricahomecare.Member.Feedback;
import com.example.mamaafricahomecare.Member.Models.FeedbackModel;
import com.example.mamaafricahomecare.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplyFeedback extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView txv_status;
    private String memberID;
    private SessionHandler session;
    private UserModel user;
    private AdapterFeedback adaptor;
    private List<FeedbackModel> list;
    private ImageView img_send;
    private EditText edt_feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_feedback);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txv_status=findViewById(R.id.txv_status);
        progressBar=findViewById(R.id.progressBar);
        recyclerView=findViewById(R.id.recyclerView);
        img_send=findViewById(R.id.img_send);
        edt_feedback=findViewById(R.id.edt_feedback);

        session=new SessionHandler(this);
        user=session.getUserDetails();

        Intent in=getIntent();
        memberID=in.getStringExtra("memberID");
        getSupportActionBar().setSubtitle(in.getStringExtra("name"));

        list=new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(layoutManager);

        img_send.setOnClickListener(v->{
            send();
        });

        getFeedback();
    }
    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void getFeedback(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_FEEDBACK,
                response -> {
                    try {
                        Log.e("RESPONSE",response);
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        String msg=jsonObject.getString("message");
                        if(status.equals("1")){

                            list.clear();
                            JSONArray jsonArray=jsonObject.getJSONArray("details");
                            for(int i=0;i <jsonArray.length();i++){
                                JSONObject jsn=jsonArray.getJSONObject(i);
                                String comment=jsn.getString("comment");
                                String reply=jsn.getString("reply");
                                FeedbackModel feedbackModel=new FeedbackModel(comment,reply);
                                list.add(feedbackModel);
                            }
                            adaptor=new AdapterFeedback(getApplicationContext(),list);
                            recyclerView.setAdapter(adaptor);
                            progressBar.setVisibility(View.GONE);
                            txv_status.setVisibility(View.GONE);
                        }else{
                            progressBar.setVisibility(View.GONE);
                            txv_status.setText(msg);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        Log.e("ERROR 1",e.toString());
                    }
                }, error -> {
            error.printStackTrace();
            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            Log.e("ERROR 2",error.toString());
        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("userID",user.getUserID());
                params.put("memberID",memberID);
                Log.e("PARAMS","" +params);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

    public void send(){
        progressBar.setVisibility(View.VISIBLE);
        img_send.setVisibility(View.GONE);
        final String feedback=edt_feedback.getText().toString().trim();

        if(TextUtils.isEmpty(feedback)){
            Toast.makeText(getApplicationContext(), "Please write a comment", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            img_send.setVisibility(View.VISIBLE);
            return;
        }
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REPLY_FEEDBACK,
                response -> {
                    try {
                        Log.e("RESPONSE",response);
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        String msg=jsonObject.getString("message");
                        if(status.equals("1")){
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                            edt_feedback.setText("");
                            progressBar.setVisibility(View.GONE);
                            img_send.setVisibility(View.VISIBLE);
                            getFeedback();
                        }else{
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);
                            img_send.setVisibility(View.VISIBLE);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        img_send.setVisibility(View.VISIBLE);
                    }
                }, error -> {
            error.printStackTrace();
            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            img_send.setVisibility(View.VISIBLE);
        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("reply",feedback);
                params.put("userID",user.getUserID());
                params.put("memberID",memberID);
                Log.e("PARAMS",""+params);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }

}