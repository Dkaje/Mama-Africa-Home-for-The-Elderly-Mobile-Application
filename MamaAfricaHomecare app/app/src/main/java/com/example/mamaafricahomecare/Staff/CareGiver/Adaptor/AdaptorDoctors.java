package com.example.mamaafricahomecare.Staff.CareGiver.Adaptor;

import static com.example.mamaafricahomecare.ConstFiles.Urls.ASSIGN_BOOK_APPOINTMENT;
import static com.example.mamaafricahomecare.ConstFiles.Urls.ASSIGN_CARE_GIVER;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mamaafricahomecare.ConstFiles.SessionHandler;
import com.example.mamaafricahomecare.ConstFiles.UserModel;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.CareGiver.Models.ModelDoctors;
import com.example.mamaafricahomecare.Staff.Supervisor.Model.ModelCareGiver;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptorDoctors extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  List<ModelDoctors> list,searchItems;
     private Context cnxt;
    private  Intent in;
    private String elderID,name,staffID;
    private View prompt;
    private AlertDialog dialog;
    ProgressDialog progressDialog;
    private EditText edt_date,edt_details;
    private TextView txv_name;
    private Button btn_book;
    private int mYear, mMonth, mDay;
    private SessionHandler session;
    private UserModel user;
    public AdaptorDoctors(Context context, List<ModelDoctors> list,
                          String elderVar) {
        this.list = list;
        this.searchItems=list;
        cnxt=context;
        elderID=elderVar;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_name,txv_phoneNo,txv_role;
        private ImageView img_view;
//
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);

            txv_name=vw.findViewById(R.id.txv_name);
            txv_role=vw.findViewById(R.id.txv_role);
            txv_phoneNo=vw.findViewById(R.id.txv_phoneNo);
            img_view=vw.findViewById(R.id.img_view);
            session=new SessionHandler(cnxt);
            user=session.getUserDetails();

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_doctor,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final ModelDoctors data=list.get(position);


            view.txv_name.setText(data.getName());
            view.txv_role.setText(data.getRole());
            view.txv_phoneNo.setText("Phone No: "+data.getPhoneNo());


           view.img_view.setOnClickListener(v->{

               name=data.getName();
               staffID=data.getStaffID();
//               promptDoc();
               alertBook(v);

          });
        }
    }

    public void alertBook(View v){

        android.app.AlertDialog.Builder alertDialog = new
                AlertDialog.Builder(v.getContext());
        alertDialog.setNegativeButton("Close", null);
        LayoutInflater li = LayoutInflater.from(cnxt);
        prompt = li.inflate(R.layout.prompt_doc,null);
        edt_date =prompt. findViewById(R.id.edt_date);
        edt_details =prompt. findViewById(R.id.edt_details);
        btn_book =prompt. findViewById(R.id.btn_book);
        txv_name =prompt. findViewById(R.id.txv_name);

        edt_date.setFocusable(false);
        txv_name.setText(name);
        alertDialog.setView(prompt);
        dialog = alertDialog.create();
        dialog.show();
        edt_date.setOnClickListener(v1 -> getDates(v));

        btn_book.setOnClickListener(v2 -> bookNow(v));
    }
    public void getDates(View vw){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(vw.getContext(),
                (view, year, monthOfYear, dayOfMonth) ->
                        edt_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); // disable past dates
        datePickerDialog.show();
    }


    public void progressShow(View v){
        progressDialog = new ProgressDialog( v.getRootView().getContext());
        progressDialog.setTitle("Wait");
        progressDialog.setIcon(R.drawable.ic_wait);
        progressDialog.setMessage(" Booking...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void bookNow(View v){

        progressShow(v);
        btn_book.setVisibility(View.GONE);
        final String date=edt_date.getText().toString().trim();
        final String details=edt_details.getText().toString().trim();


        if(TextUtils.isEmpty(date)){
            Toast.makeText(cnxt, "Please choose a date", Toast.LENGTH_SHORT).show();
            progressDialog.hide();
            btn_book.setVisibility(View.VISIBLE);

            return;
        }
        if(TextUtils.isEmpty(details)){
            Toast.makeText(cnxt, "Please write appointment details", Toast.LENGTH_SHORT).show();
            progressDialog.hide();
            btn_book.setVisibility(View.VISIBLE);

            return;
        }

        StringRequest stringRequest=new StringRequest(Request.Method.POST, ASSIGN_BOOK_APPOINTMENT,
                response -> {

                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        String message=jsonObject.getString("message");
                        if(status.equals("1")){

                            ((Activity)v.getContext()).finish();
                        }
                        progressDialog.hide();
                        btn_book.setVisibility(View.VISIBLE);
                        Toast.makeText(cnxt, message, Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("E "," "+e.toString());
                        progressDialog.hide();
                        btn_book.setVisibility(View.VISIBLE);

                    }
                }, error -> {
            error.printStackTrace();
            Log.e("ERROR"," "+error.toString());
            Toast.makeText(cnxt, error.toString(), Toast.LENGTH_SHORT).show();
            progressDialog.hide();
            btn_book.setVisibility(View.VISIBLE);

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("date",date);
                params.put("details",details);
                params.put("careGiverID",user.getUserID());
                params.put("elderID",elderID);
                params.put("staffID",staffID);
                Log.e("PARAMS "," "+params);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(cnxt);
        requestQueue.add(stringRequest);
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    list = searchItems;
                } else {
                    ArrayList<ModelDoctors> filteredList = new ArrayList<>();

                    for (ModelDoctors androidVersion : list) {

                        if (androidVersion.getName().toLowerCase().contains(charString)
                                ||androidVersion.getPhoneNo().toLowerCase().contains(charString)
                           ||androidVersion.getRole().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    list = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (ArrayList<ModelDoctors>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
