package com.example.mamaafricahomecare.Donors.Adaptor;

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

import java.util.ArrayList;
import java.util.List;

public class AdaptorDonations extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DonationModel> list,searchItems;
     private Context cnxt;
    private  Intent in;

    public AdaptorDonations(Context context, List<DonationModel> list) {
        this.list = list;
        this.searchItems=list;
        cnxt=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_donationID,
                txv_date,txv_status;
        private ImageView img_view;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_donationID=vw.findViewById(R.id.txv_donationID);
            txv_date=vw.findViewById(R.id.txv_date);
            txv_status=vw.findViewById(R.id.txv_status);
            img_view=vw.findViewById(R.id.img_view);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_donations,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final DonationModel data=list.get(position);

            view.txv_donationID.setText("Donation No: "+data.getDonationID());
            view.txv_date.setText("Date: "+data.getDonationDate());
            view.txv_status.setText("Status: "+data.getDonationStatus());

            view.img_view.setOnClickListener(v->{
                in=new Intent(cnxt, ItemsDonated.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("donationID", data.getDonationID());
                in.putExtra("donationDate",data.getDonationDate());
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
                    ArrayList<DonationModel> filteredList = new ArrayList<>();

                    for (DonationModel androidVersion : list) {

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
                list = (ArrayList<DonationModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
