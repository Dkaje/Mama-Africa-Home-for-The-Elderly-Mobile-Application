package com.example.mamaafricahomecare.Staff.StoreManager.Adaptor;

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

import com.example.mamaafricahomecare.Donors.ItemsDonated;
import com.example.mamaafricahomecare.Donors.Model.DonationModel;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.StoreManager.ItemsList;
import com.example.mamaafricahomecare.Staff.StoreManager.Model.DonationListModel;

import java.util.ArrayList;
import java.util.List;

public class AdaptorDonationList extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DonationListModel> list,searchItems;
     private Context cnxt;
    private  Intent in;

    public AdaptorDonationList(Context context, List<DonationListModel> list) {
        this.list = list;
        this.searchItems=list;
        cnxt=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_donationID,
                txv_date,txv_status,txv_name;
        private ImageView img_view;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_donationID=vw.findViewById(R.id.txv_donationID);
            txv_name=vw.findViewById(R.id.txv_name);
            txv_date=vw.findViewById(R.id.txv_date);
            txv_status=vw.findViewById(R.id.txv_status);
            img_view=vw.findViewById(R.id.img_view);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_donation_lists,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final DonationListModel data=list.get(position);

            view.txv_donationID.setText("Donation No: "+data.getDonationID());
            view.txv_name.setText(data.getName());
            view.txv_date.setText("Date: "+data.getDanationDate());
            view.txv_status.setText("Status: "+data.getDonationStatus());

            view.img_view.setOnClickListener(v->{
                in=new Intent(cnxt, ItemsList.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("donationID", data.getDonationID());
                in.putExtra("donationDate",data.getDanationDate());
                in.putExtra("name",data.getName());
                in.putExtra("donationStatus",data.getDonationStatus());
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
                    ArrayList<DonationListModel> filteredList = new ArrayList<>();

                    for (DonationListModel androidVersion : list) {

                        if (androidVersion.getDonationID().toLowerCase().contains(charString)) {

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
                list = (ArrayList<DonationListModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
