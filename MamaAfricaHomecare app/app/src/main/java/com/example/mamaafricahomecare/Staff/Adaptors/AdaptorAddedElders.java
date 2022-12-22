package com.example.mamaafricahomecare.Staff.Adaptors;

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
import com.example.mamaafricahomecare.Staff.Supervisor.Assign;
import com.example.mamaafricahomecare.Staff.Supervisor.CareGiverAssigned;
import com.example.mamaafricahomecare.Staff.Supervisor.ElderDetails;
import com.example.mamaafricahomecare.Staff.Supervisor.Model.ModelElders;

import java.util.ArrayList;
import java.util.List;

public class AdaptorAddedElders extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ModelElders> list,searchItems;
     private Context cnxt;
    private  Intent in;

    public AdaptorAddedElders(Context context, List<ModelElders> list) {
        this.list = list;
        this.searchItems=list;
        cnxt=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        private TextView txv_name,txv_gender,txv_dob,txv_elderID,
                txv_dateAdded,txv_status,txv_familyMember;
        private ImageView img_view;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_elderID=vw.findViewById(R.id.txv_elderID);
            txv_name=vw.findViewById(R.id.txv_name);
            txv_familyMember=vw.findViewById(R.id.txv_familyMember);
            txv_gender=vw.findViewById(R.id.txv_gender);
            txv_dob=vw.findViewById(R.id.txv_dob);
            txv_dateAdded=vw.findViewById(R.id.txv_dateAdded);
            txv_status=vw.findViewById(R.id.txv_status);
            img_view=vw.findViewById(R.id.img_view);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_added_elders,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final ModelElders data=list.get(position);

            view.txv_elderID.setText("ID: "+data.getElderID());
            view.txv_name.setText(data.getName());
            view.txv_familyMember.setText("Family member: "+data.getFamilyMember());
            view.txv_dob.setText("DOB: "+data.getDob());
            view.txv_gender.setText(data.getGender());
            view.txv_dateAdded.setText("Date: "+data.getDateAdded());
            view.txv_status.setText("Status: "+data.getElderStatus());



            view.img_view.setOnClickListener(v->{

                if(data.getElderStatus().equals("Pending approval")){
                     in=new Intent(cnxt, ElderDetails.class);

                }
                if(data.getElderStatus().equals("Approved")){
                    in=new Intent(cnxt, Assign.class);

                }
                if(data.getElderStatus().equals("Admitted")){
                    in=new Intent(cnxt, CareGiverAssigned.class);

                }
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("elderID", data.getElderID());
                in.putExtra("familyMember",data.getFamilyMember());
                in.putExtra("name",data.getName());
                in.putExtra("gender",data.getGender());
                in.putExtra("dob",data.getDob());
                in.putExtra("dateAdded",data.getDateAdded());
                in.putExtra("elderStatus",data.getElderStatus());
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
                    ArrayList<ModelElders> filteredList = new ArrayList<>();

                    for (ModelElders androidVersion : list) {

                        if (androidVersion.getFamilyMember().toLowerCase().contains(charString)
                                ||androidVersion.getName().toLowerCase().contains(charString)
                           ||androidVersion.getElderID().toLowerCase().contains(charString)) {

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
                list = (ArrayList<ModelElders>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
