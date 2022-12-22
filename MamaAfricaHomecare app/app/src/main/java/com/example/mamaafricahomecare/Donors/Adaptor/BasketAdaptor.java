package com.example.mamaafricahomecare.Donors.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mamaafricahomecare.Donors.Model.BasketModel;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.Doctors.Model.CartModel;

import java.util.List;

public class BasketAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BasketModel> list;
     private Context context;

    public BasketAdaptor(Context context, List<BasketModel> list) {
        this.list = list;
        context=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_itemName;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_itemName=vw.findViewById(R.id.txv_itemName);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_basket,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final BasketModel data=list.get(position);

            view.txv_itemName.setText(data.getItemName()+"\n Quantity "+data.getQuantity());

        }
    }

    @Override
    public int getItemCount() {
        return list.size();

    }


}
