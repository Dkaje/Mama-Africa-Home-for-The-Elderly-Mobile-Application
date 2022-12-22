package com.example.mamaafricahomecare.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mamaafricahomecare.Model.IssuedDrugsModel;
import com.example.mamaafricahomecare.R;

import java.util.List;

public class IssueDrugAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<IssuedDrugsModel> list;
     private Context context;

    public IssueDrugAdaptor(Context context, List<IssuedDrugsModel> list) {
        this.list = list;
        context=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_drugName,txv_quantity;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_drugName=vw.findViewById(R.id.txv_drugName);
            txv_quantity=vw.findViewById(R.id.txv_quantity);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_issued_drugs,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final IssuedDrugsModel data=list.get(position);

            view.txv_drugName.setText(data.getDrugName());
            view.txv_quantity.setText("Quantity: "+data.getQuantity());

        }
    }

    @Override
    public int getItemCount() {
        return list.size();

    }


}
