package com.example.mamaafricahomecare.Member.Adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mamaafricahomecare.Member.Models.ElderModel;
import com.example.mamaafricahomecare.Member.MoreInfo;
import com.example.mamaafricahomecare.R;

import java.util.ArrayList;
import java.util.List;

public class ElderAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ElderModel> list,searchItems;
     private Context cnxt;

    public ElderAdaptor(Context context, List<ElderModel> list) {
        this.list = list;
        this.searchItems=list;
        cnxt=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_name,txv_gender,txv_dob,
                txv_dateAdded,txv_status;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_name=vw.findViewById(R.id.txv_name);
            txv_gender=vw.findViewById(R.id.txv_gender);
            txv_dob=vw.findViewById(R.id.txv_dob);
            txv_dateAdded=vw.findViewById(R.id.txv_dateAdded);
            txv_status=vw.findViewById(R.id.txv_status);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_elder,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final ElderModel data=list.get(position);

            view.txv_name.setText(data.getName());
            view.txv_dob.setText("DOB: "+data.getDob());
            view.txv_gender.setText(data.getGender());
            view.txv_dateAdded.setText("Date: "+data.getDateAdded());
            view.txv_status.setText("Status: "+data.getElderStatus());

            view.itemView.setOnClickListener(v->{
                if(data.getElderStatus().equals("Pending approval")){

                }else{
                    Intent in=new Intent(cnxt, MoreInfo.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    in.putExtra("elderID", data.getElderID());
                    in.putExtra("name",data.getName());
                    in.putExtra("gender",data.getGender());
                    in.putExtra("dob",data.getDob());
                    in.putExtra("dateAdded",data.getDateAdded());
                    in.putExtra("elderStatus",data.getElderStatus());
                    cnxt.startActivity(in);
                }

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

                    ArrayList<ElderModel> filteredList = new ArrayList<>();

                    for (ElderModel androidVersion : list) {

                        if (androidVersion.getName().toLowerCase().contains(charString)) {

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
                list = (ArrayList<ElderModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
