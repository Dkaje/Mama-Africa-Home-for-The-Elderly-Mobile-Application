package com.example.mamaafricahomecare.Staff.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.Doctors.Model.CartModel;

import java.util.List;

public class CartAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CartModel> list;
     private Context context;

    public CartAdaptor(Context context, List<CartModel> list) {
        this.list = list;
        context=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_drugNmae;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_drugNmae=vw.findViewById(R.id.txv_drugName);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_cart,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final CartModel data=list.get(position);

            view.txv_drugNmae.setText(data.getDrugName()+" Quantity "+data.getQuantity());

        }
    }

    @Override
    public int getItemCount() {
        return list.size();

    }


}
