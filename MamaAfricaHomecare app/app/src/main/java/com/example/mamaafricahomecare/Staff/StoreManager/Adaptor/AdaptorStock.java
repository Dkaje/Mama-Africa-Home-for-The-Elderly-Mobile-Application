package com.example.mamaafricahomecare.Staff.StoreManager.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mamaafricahomecare.Donors.Model.ItemModel;
import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.StoreManager.Model.StockModel;

import java.util.List;

public class AdaptorStock extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<StockModel> list;
     private Context context;

    public AdaptorStock(Context context, List<StockModel> list) {
        this.list = list;
        context=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_itemName,txv_stock;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_itemName=vw.findViewById(R.id.txv_itemName);
            txv_stock=vw.findViewById(R.id.txv_stock);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_stock,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final StockModel data=list.get(position);

            view.txv_itemName.setText(data.getItemName());
            view.txv_stock.setText(data.getStock()+" in stock");

        }
    }

    @Override
    public int getItemCount() {
        return list.size();

    }


}
