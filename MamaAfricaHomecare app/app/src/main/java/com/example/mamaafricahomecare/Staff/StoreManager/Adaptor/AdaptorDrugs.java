package com.example.mamaafricahomecare.Staff.StoreManager.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mamaafricahomecare.R;
import com.example.mamaafricahomecare.Staff.StoreManager.Model.DrugsModel;
import com.example.mamaafricahomecare.Staff.StoreManager.Model.StockModel;

import java.util.List;

public class AdaptorDrugs extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DrugsModel> list;
     private Context context;

    public AdaptorDrugs(Context context, List<DrugsModel> list) {
        this.list = list;
        context=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_drugName,txv_stock;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_drugName=vw.findViewById(R.id.txv_drugName);
            txv_stock=vw.findViewById(R.id.txv_stock);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_drugs,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final DrugsModel data=list.get(position);

            view.txv_drugName.setText(data.getDrugName());
            view.txv_stock.setText(data.getStock()+" in stock");

        }
    }

    @Override
    public int getItemCount() {
        return list.size();

    }


}
