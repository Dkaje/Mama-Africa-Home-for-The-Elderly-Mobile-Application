package com.example.mamaafricahomecare.Staff.Adaptors;

import static com.example.mamaafricahomecare.ConstFiles.Urls.APPROVE_ELDER;
import static com.example.mamaafricahomecare.ConstFiles.Urls.ASSIGN_CARE_GIVER;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.Supervisor.Model.ModelCareGiver;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptorCareGiver extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  List<ModelCareGiver> list,searchItems;
     private Context cnxt;
    private  Intent in;
    private String elderID,name,staffID,statusElder;

    ProgressDialog progressDialog;

    public AdaptorCareGiver(Context context, List<ModelCareGiver> list,
                            String elderVar,String elderStatus) {
        this.list = list;
        this.searchItems=list;
        cnxt=context;
        elderID=elderVar;
        statusElder=elderStatus;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_name,txv_phoneNo;
        private ImageView img_view;
//
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);

            txv_name=vw.findViewById(R.id.txv_name);
            txv_phoneNo=vw.findViewById(R.id.txv_phoneNo);
            img_view=vw.findViewById(R.id.img_view);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_care_giver,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final ModelCareGiver data=list.get(position);


            view.txv_name.setText(data.getName());
            view.txv_phoneNo.setText("Phone No: "+data.getPhoneNo());

            if(statusElder.equals("Admitted")){
                view.img_view.setVisibility(View.GONE);
            }
           view.img_view.setOnClickListener(v->{

               name=data.getName();
               staffID=data.getStaffID();
               alertDlg(v);

          });
        }
    }


    private void alertDlg( View v){

        AlertDialog.Builder alertbox = new AlertDialog.Builder(v.getRootView().getContext());
        alertbox.setMessage("Assign to "+name);
        alertbox.setTitle("Confirm");
        alertbox.setIcon(R.drawable.ic_alert);

        alertbox.setNeutralButton("OK",
                (arg0, arg1) -> assignStaff(v));
        alertbox.setNegativeButton("Close",null);
        alertbox.show();
    }

    public void progressShow(View v){
        progressDialog = new ProgressDialog( v.getRootView().getContext());
        progressDialog.setTitle("Wait");
        progressDialog.setIcon(R.drawable.ic_wait);
        progressDialog.setMessage(" Submitting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void assignStaff(View v){

        progressShow(v);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, ASSIGN_CARE_GIVER,
                response -> {

                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String status=jsonObject.getString("status");
                        String message=jsonObject.getString("message");
                        if(status.equals("1")){
                            ((Activity)v.getContext()).finish();
                            Toast.makeText(cnxt, message, Toast.LENGTH_SHORT).show();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(cnxt, message, Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("E "," "+e.toString());
                        progressDialog.dismiss();

                    }
                }, error -> {
            error.printStackTrace();
            Log.e("ERROR"," "+error.toString());
            Toast.makeText(cnxt, error.toString(), Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
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
                    ArrayList<ModelCareGiver> filteredList = new ArrayList<>();

                    for (ModelCareGiver androidVersion : list) {

                        if (androidVersion.getName().toLowerCase().contains(charString)
                                ||androidVersion.getName().toLowerCase().contains(charString)
                           ||androidVersion.getPhoneNo().toLowerCase().contains(charString)) {

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
                list = (ArrayList<ModelCareGiver>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
