package com.example.mamaafricahomecare;

import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_LOGIN;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_LOGIN_DONOR;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_LOGIN_STAFF;
import static com.example.mamaafricahomecare.ConstFiles.Urls.URL_LOGIN_SUPPLIER;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private ProgressBar progressBar;
    private Button btn_login;
    private TextView txv_register,txv_forgot_password;
    private EditText edt_username,edt_password,edt_select;

    private SessionHandler session;
    private UserModel user;
    private String SELECT_USER_LOGIN="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressBar=findViewById(R.id.progressBar);
        btn_login=findViewById(R.id.btn_login);
        edt_username=findViewById(R.id.edt_username);
        edt_password=findViewById(R.id.edt_password);
        txv_register=findViewById(R.id.txv_register);
        edt_select=findViewById(R.id.edt_select);
        txv_forgot_password=findViewById(R.id.txv_forgot_password);

        progressBar.setVisibility(View.GONE);
        edt_select.setFocusable(false);

        session=new SessionHandler(this);
        user=session.getUserDetails();
        txv_register.setOnClickListener(v->{
            Intent in=new Intent(getApplicationContext(),Register.class);
            startActivity(in);
            finish();
        });
        txv_forgot_password.setOnClickListener(v->{
            Intent in=new Intent(getApplicationContext(),RestPassword.class);
            startActivity(in);
            finish();
        });
        btn_login.setOnClickListener(v->login());
        edt_select.setOnClickListener(v -> selectAlert(v));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void selectAlert(View v){
        final android.app.AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Login as");
        final String[] array = {"Family member","Donor","Supervisor","Care giver","Doctor",
                "Lab technician","Pharmacist","Store manager","Supplier"};

        builder.setSingleChoiceItems( array, -1, (dialogInterface, i) -> {
            edt_select.setText(array[i]);
            Toast.makeText(getApplicationContext(), array[i], Toast.LENGTH_SHORT).show();
            dialogInterface.dismiss();
            builder.setNegativeButton("Close",null);
        });
        builder.show();

    }
    private void login() {
        progressBar.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);


        final String select = edt_select.getText().toString().trim();
        final String username = edt_username.getText().toString().trim();
        final String password = edt_password.getText().toString().trim();



        if(TextUtils.isEmpty(select)){
            Toast.makeText(getApplicationContext(), "Please select an option", Toast.LENGTH_SHORT).show();
            btn_login.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;

        }


        if(TextUtils.isEmpty(username)){
            Toast.makeText(getApplicationContext(), "Username required", Toast.LENGTH_SHORT).show();
            btn_login.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;

        }


        if(TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT).show();
            btn_login.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(select.equals("Family member")){
            SELECT_USER_LOGIN= URL_LOGIN;
        }else if(select.equals("Donor")){
            SELECT_USER_LOGIN= URL_LOGIN_DONOR;
        }else if(select.equals("Supplier")){
            SELECT_USER_LOGIN= URL_LOGIN_SUPPLIER;
        }else{
            SELECT_USER_LOGIN= URL_LOGIN_STAFF;
        }

        StringRequest stringRequest=new StringRequest(Request.Method.POST, SELECT_USER_LOGIN,
                response -> {
                    Log.e("Response","is" + response);
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        String msg=jsonObject.getString("message");
                        if(status.equals("1")){
                            JSONArray jsonArray=jsonObject.getJSONArray("details");

                            for(int i=0;i <jsonArray.length();i++){
                                JSONObject jsn=jsonArray.getJSONObject(i);
                                String userID=jsn.getString("userID");
                                String firstname=jsn.getString("firstname");
                                String lastname=jsn.getString("lastname");
                                String userName=jsn.getString("username");
                                String phoneNo=jsn.getString("phoneNo");
                                String email=jsn.getString("email");
                                String dateCreated=jsn.getString("dateCreated");
                                String user_type=jsn.getString("user");
                                session.loginUser(userID,firstname,lastname,userName,phoneNo,email,dateCreated,user_type);
                                Toast.makeText(getApplicationContext(), user_type, Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

                        }
                        btn_login.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                        btn_login.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }
                }, error -> {
            error.printStackTrace();
            error.printStackTrace();
            Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            btn_login.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}
