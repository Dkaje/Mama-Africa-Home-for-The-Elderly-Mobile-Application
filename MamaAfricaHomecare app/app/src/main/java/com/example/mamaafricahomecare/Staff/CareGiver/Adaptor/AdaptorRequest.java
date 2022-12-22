package com.example.mamaafricahomecare.Staff.CareGiver.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.RequestDetails;
import com.example.mamaafricahomecare.Staff.CareGiver.Models.AssignedModel;
import com.example.mamaafricahomecare.Staff.CareGiver.Models.RequestModel;
import com.example.mamaafricahomecare.Staff.CareGiver.MoreDetails;

import java.util.ArrayList;
import java.util.List;

public class AdaptorRequest extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RequestModel> list,searchItems;
     private Context cnxt;
    private  Intent in;

    public AdaptorRequest(Context context, List<RequestModel> list) {
        this.list = list;
        this.searchItems=list;
        cnxt=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_name,txv_gender,txv_requestID,
                txv_status,txv_requestDate;
        private ImageView img_view;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_requestID=vw.findViewById(R.id.txv_requestID);
            txv_name=vw.findViewById(R.id.txv_name);
            txv_gender=vw.findViewById(R.id.txv_gender);
            txv_status=vw.findViewById(R.id.txv_status);
            txv_requestDate=vw.findViewById(R.id.txv_requestDate);
            img_view=vw.findViewById(R.id.img_view);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_requests,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final RequestModel data=list.get(position);

            view.txv_requestID.setText("ID: "+data.getRequestID());
            view.txv_name.setText(data.getName());
            view.txv_gender.setText(data.getGender());
            view.txv_requestDate.setText("Request date: "+data.getRequestDate());
            view.txv_status.setText("Status: "+data.getRequestStatus());

            view.img_view.setOnClickListener(v->{

               in=new Intent(cnxt, RequestDetails.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("requestID", data.getRequestID());
                in.putExtra("name",data.getName());
                in.putExtra("gender",data.getGender());
                in.putExtra("requestDate",data.getRequestDate());
                in.putExtra("requestStatus",data.getRequestStatus());
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
                    ArrayList<RequestModel> filteredList = new ArrayList<>();

                    for (RequestModel androidVersion : list) {

                        if (androidVersion.getName().toLowerCase().contains(charString)
                           ||androidVersion.getRequestID().toLowerCase().contains(charString)) {
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
                list = (ArrayList<RequestModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
