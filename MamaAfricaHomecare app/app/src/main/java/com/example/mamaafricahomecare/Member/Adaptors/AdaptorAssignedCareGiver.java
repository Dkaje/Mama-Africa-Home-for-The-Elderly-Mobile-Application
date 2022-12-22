package com.example.mamaafricahomecare.Member.Adaptors;

import static com.example.mamaafricahomecare.ConstFiles.Urls.ASSIGN_CARE_GIVER;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.mamaafricahomecare.Member.Feedback;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.Supervisor.Model.ModelCareGiver;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdaptorAssignedCareGiver extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  List<ModelCareGiver> list,searchItems;
     private Context cnxt;
    private  Intent in;

    ProgressDialog progressDialog;

    public AdaptorAssignedCareGiver(Context context, List<ModelCareGiver> list) {
        this.list = list;
        this.searchItems=list;
        cnxt=context;
       ;
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
            view.img_view.setVisibility(View.GONE);

            view.itemView.setOnClickListener(v->{
                Intent in=new Intent(cnxt, Feedback.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("staffID",data.getStaffID());
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
