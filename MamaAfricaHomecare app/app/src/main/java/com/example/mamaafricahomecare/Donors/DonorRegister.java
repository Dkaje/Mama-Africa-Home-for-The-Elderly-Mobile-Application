package com.example.mamaafricahomecare.Donors;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_REGISTER;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_REGISTER_DONOR;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.Login;
import com.example.mamaafricahomecare.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DonorRegister extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button btn_register;
    private TextView txv_login;
    private EditText edt_firstname,edt_lastname,edt_username,
            edt_phoneNo,edt_email,edt_password,edt_confrim_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar=findViewById(R.id.progressBar);
        btn_register=findViewById(R.id.btn_register);
        edt_firstname=findViewById(R.id.edt_firstname);
        edt_lastname=findViewById(R.id.edt_lastname);
        edt_username=findViewById(R.id.edt_username);
        edt_email=findViewById(R.id.edt_email);
        edt_phoneNo=findViewById(R.id.edt_phoneNo);
        edt_password=findViewById(R.id.edt_password);
        edt_confrim_password=findViewById(R.id.edt_c_password);
        txv_login=findViewById(R.id.txv_login);

        progressBar.setVisibility(View.GONE);
        txv_login.setOnClickListener(v->{
            Intent in=new Intent(getApplicationContext(), Login.class);
            startActivity(in);
            finish();
        });
        btn_register.setOnClickListener(v->register());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void register() {
        progressBar.setVisibility(View.VISIBLE);
        btn_register.setVisibility(View.GONE);

        final String firstname = edt_firstname.getText().toString().trim();
        final String lastname = edt_lastname.getText().toString().trim();
        final String username = edt_username.getText().toString().trim();
        final String phoneNo = edt_phoneNo.getText().toString().trim();
        final String email = edt_email.getText().toString().trim();
        final String password = edt_password.getText().toString().trim();
        final String password_c = edt_confrim_password.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if(TextUtils.isEmpty(firstname)){
            Toast.makeText(getApplicationContext(), "first name required", Toast.LENGTH_SHORT).show();
            btn_register.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;

        }
        if(TextUtils.isEmpty(lastname)){
            Toast.makeText(getApplicationContext(), "last name required", Toast.LENGTH_SHORT).show();
            btn_register.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;

        }

        if(TextUtils.isEmpty(username)){
            Toast.makeText(getApplicationContext(), "Username required", Toast.LENGTH_SHORT).show();
            btn_register.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;

        }
        if(TextUtils.isEmpty(phoneNo)){
            Toast.makeText(getApplicationContext(), "Phone number required", Toast.LENGTH_SHORT).show();
            btn_register.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;

        }
        if(phoneNo.length()>10 ||phoneNo.length()<10){
            Toast.makeText(getApplicationContext(), "Phone number should contain 10 digits", Toast.LENGTH_SHORT).show();
            btn_register.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            return;
        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Email address required", Toast.LENGTH_SHORT).show();
            btn_register.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;

        }
        if (!email.matches(emailPattern)){
            Toast.makeText(getApplicationContext(), "In valid email", Toast.LENGTH_SHORT).show();
            btn_register.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT).show();
            btn_register.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(!password.equals(password_c)) {
            Toast.makeText(getApplicationContext(), "Password not the same", Toast.LENGTH_SHORT).show();
            btn_register.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGISTER_DONOR,
                response -> {
                    Log.e("Response","is" + response);
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        String msg=jsonObject.getString("message");
                        if(status.equals("1")){
                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                            btn_register.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }else{
                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

                            btn_register.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                        btn_register.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }, error -> {
            error.printStackTrace();
            error.printStackTrace();
            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            btn_register.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstname",firstname);
                params.put("lastname",lastname);
                params.put("username",username);
                params.put("phoneNo",phoneNo);
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}