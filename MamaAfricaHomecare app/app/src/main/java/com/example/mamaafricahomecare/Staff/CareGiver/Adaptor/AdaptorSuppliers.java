package com.example.mamaafricahomecare.Staff.CareGiver.Adaptor;

import static com.example.mamaafricahomecare.ConstFiles.Urls.ASSIGN_BOOK_APPOINTMENT;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.mamaafricahomecare.Staff.StoreManager.Model.SuppliersModel;
import com.example.mamaafricahomecare.Staff.StoreManager.RequestDrugs;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptorSuppliers extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  List<SuppliersModel> list,searchItems;
     private Context cnxt;

    public AdaptorSuppliers(Context context, List<SuppliersModel> list) {
        this.list = list;
        this.searchItems=list;
        cnxt=context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_name,txv_phoneNo;
//
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);

            txv_name=vw.findViewById(R.id.txv_name);
            txv_phoneNo=vw.findViewById(R.id.txv_phoneNo);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_suppliers,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final SuppliersModel data=list.get(position);


            view.txv_name.setText(data.getName());
            view.txv_phoneNo.setText("Phone No: "+data.getPhoneNo());

            view.itemView.setOnClickListener(v->{
                Intent in=new Intent(cnxt, RequestDrugs.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("supplierID",data.getSupplierID());
                in.putExtra("name",data.getName());
                cnxt.startActivity(in);
            });

        }
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
                    ArrayList<SuppliersModel> filteredList = new ArrayList<>();

                    for (SuppliersModel androidVersion : list) {

                        if (androidVersion.getName().toLowerCase().contains(charString)
                                ||androidVersion.getPhoneNo().toLowerCase().contains(charString)
                        ) {

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
                list = (ArrayList<SuppliersModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
