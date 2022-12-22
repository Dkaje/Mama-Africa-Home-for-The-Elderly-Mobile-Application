package com.example.mamaafricahomecare.Donors.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mamaafricahomecare.Donors.Model.BasketModel;
import com.example.mamaafricahomecare.Donors.Model.ItemModel;
import com.example.mamaafricahomecare.R;

import java.util.List;

public class AdaptorDonationItems extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ItemModel> list;
     private Context context;

    public AdaptorDonationItems(Context context, List<ItemModel> list) {
        this.list = list;
        context=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_itemName,txv_quantity;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_itemName=vw.findViewById(R.id.txv_itemName);
            txv_quantity=vw.findViewById(R.id.txv_quantity);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_items,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final ItemModel data=list.get(position);

            view.txv_itemName.setText(data.getItemName());
            view.txv_quantity.setText(data.getQuantity()+" Quantity");

        }
    }

    @Override
    public int getItemCount() {
        return list.size();

    }


}
