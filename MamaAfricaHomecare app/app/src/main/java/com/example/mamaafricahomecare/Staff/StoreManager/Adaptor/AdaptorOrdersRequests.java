package com.example.mamaafricahomecare.Staff.StoreManager.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mamaafricahomecare.OrderItems;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.Doctors.AppointmentHistory;
import com.example.mamaafricahomecare.Staff.Model.AppointmentsModel;
import com.example.mamaafricahomecare.Staff.StoreManager.Model.OrderCartModel;
import com.example.mamaafricahomecare.Staff.StoreManager.Model.OrdersRequestsModel;

import java.util.ArrayList;
import java.util.List;

public class AdaptorOrdersRequests extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<OrdersRequestsModel> list,searchItems;
     private  Context cnxt;;

    public AdaptorOrdersRequests(Context context, List<OrdersRequestsModel> list) {
        this.list = list;
        this.searchItems= list;
        cnxt=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_name,txv_requestDate,txv_requestID,txv_requestStatus;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_requestID=vw.findViewById(R.id.txv_requestID);
            txv_name=vw.findViewById(R.id.txv_name);
            txv_requestDate=vw.findViewById(R.id.txv_requestDate);
            txv_requestStatus=vw.findViewById(R.id.txv_status);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_orders,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final OrdersRequestsModel data=list.get(position);

            view.txv_name.setText(data.getName());
            view.txv_requestID.setText("Request ID: "+data.getRequestID());
            view.txv_requestDate.setText(data.getRequestDate());
            view.txv_requestStatus.setText(data.getRequestStatus());

            view.itemView.setOnClickListener(v->{
                Intent in=new Intent(cnxt, OrderItems.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("requestID",data.getRequestID());
                in.putExtra("name",data.getName());
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
                    ArrayList<OrdersRequestsModel> filteredList = new ArrayList<>();

                    for (OrdersRequestsModel androidVersion : list) {

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
                list = (ArrayList<OrdersRequestsModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
