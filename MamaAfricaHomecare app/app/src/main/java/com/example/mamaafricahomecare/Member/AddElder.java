package com.example.mamaafricahomecare.Member;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_ADD_ELDER;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;
import com.example.mamaafricahomecare.R;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddElder extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button btn_add;
    private EditText edt_firstname,edt_lastname,
            edt_gender,edt_dob,edt_relation;
    private int mYear, mMonth, mDay;
    private SessionHandler session;
    private UserModel user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_elder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar=findViewById(R.id.progressBar);
        btn_add=findViewById(R.id.btn_add);
        edt_firstname=findViewById(R.id.edt_firstname);
        edt_lastname=findViewById(R.id.edt_lastname);
        edt_gender=findViewById(R.id.edt_gender);
        edt_relation=findViewById(R.id.edt_relation);
        edt_dob=findViewById(R.id.edt_age);

        progressBar.setVisibility(View.GONE);
        edt_gender.setFocusable(false);
        edt_relation.setFocusable(false);
        edt_dob.setFocusable(false);

        session=new SessionHandler(this);
        user=session.getUserDetails();
        edt_gender.setOnClickListener(v->select(v));
        edt_relation.setOnClickListener(v->selectAlert(v));
        edt_dob.setOnClickListener(v->getDate(v));
        btn_add.setOnClickListener(v->add());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void getDate( View v){
        final Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -65); // set maximum 18 years ago
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(),
                (view, year, monthOfYear, dayOfMonth)-> edt_dob.
                        setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year),
                mYear, mMonth, mDay);
//        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); // disable past dates
        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());

        datePickerDialog.show();
    }
    public void select(View v){
        final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Gender");
        final String[] array = {"Male","Female"};

        builder.setSingleChoiceItems( array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edt_gender.setText(array[i]);
                dialogInterface.dismiss();

                builder.setNegativeButton("Close",null);
            }
        });
        builder.show();
    }
    public void selectAlert(View v){
        final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Relationship");
        final String[] array = {"Father","Mother","Brother","Sister","Uncle","Aunt","Relative","Others"};

        builder.setSingleChoiceItems( array, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edt_relation.setText(array[i]);
                Toast.makeText(getApplicationContext(), array[i], Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
                builder.setNegativeButton("Close",null);
            }
        });
        builder.show();

    }
    private void add(){
        progressBar.setVisibility(View.VISIBLE);
        btn_add.setVisibility(View.GONE);

        final String firstname=edt_firstname.getText().toString().trim();
        final String lastname=edt_lastname.getText().toString().trim();
        final String gender=edt_gender.getText().toString().trim();
        final String relation=edt_relation.getText().toString().trim();
        final String dob=edt_dob.getText().toString().trim();

        if(TextUtils.isEmpty(firstname)||TextUtils.isEmpty(lastname)
                ||TextUtils.isEmpty(gender)||TextUtils.isEmpty(relation)||TextUtils.isEmpty(dob)){

            Toast.makeText(getApplicationContext(), "Please enter all the details", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            btn_add.setVisibility(View.VISIBLE);
            return;
        }
        StringRequest stringRequest=new StringRequest(Request.Method.POST,URL_ADD_ELDER,
                response ->{
                    Log.e("RESPONSE"," "+response);
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        String msg=jsonObject.getString("message");
                        if(status.equals("1")){
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                            Intent in=new Intent(getApplicationContext(),Elders.class);
                            startActivity(in);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            btn_add.setVisibility(View.VISIBLE);
                        }

                    }catch (Exception e){
                        Log.e("ERROR e",e.toString());
                        progressBar.setVisibility(View.GONE);
                        btn_add.setVisibility(View.VISIBLE);
                    }
                },error->{
            Log.e("ERROR error",error.toString());
            progressBar.setVisibility(View.GONE);
            btn_add.setVisibility(View.VISIBLE);
        } ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userID",user.getUserID());
                params.put("firstname",firstname);
                params.put("lastname",lastname);
                params.put("gender",gender);
                params.put("relation",relation);
                params.put("dob",dob);
                Log.e("PARAMS"," "+params);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }


}