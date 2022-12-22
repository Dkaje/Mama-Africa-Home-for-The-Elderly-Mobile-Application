package com.example.mamaafricahomecare.Staff.Pharmacist.Adptors;

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
import com.example.mamaafricahomecare.Staff.Doctors.AppointmentHistory;
import com.example.mamaafricahomecare.Staff.Doctors.BookingDetails;
import com.example.mamaafricahomecare.Staff.Doctors.Model.BookingModel;
import com.example.mamaafricahomecare.Staff.Pharmacist.Issue;
import com.example.mamaafricahomecare.Staff.Pharmacist.Model.DrugIssueModel;

import java.util.ArrayList;
import java.util.List;

public class AdaptorDrugIssue extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DrugIssueModel> list,searchItems;
     private Context cnxt;
    private  Intent in;

    public AdaptorDrugIssue(Context context, List<DrugIssueModel> list) {
        this.list = list;
        this.searchItems=list;
        cnxt=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        private TextView txv_name,txv_gender,txv_dob,txv_appointmentID,
                txv_status,txv_familyMember,txv_details;
        private ImageView img_view;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_appointmentID=vw.findViewById(R.id.txv_appointmentID);
            txv_name=vw.findViewById(R.id.txv_name);
            txv_familyMember=vw.findViewById(R.id.txv_familyMember);
            txv_gender=vw.findViewById(R.id.txv_gender);
            txv_dob=vw.findViewById(R.id.txv_dob);
            txv_status=vw.findViewById(R.id.txv_status);
            txv_details=vw.findViewById(R.id.txv_details);
            img_view=vw.findViewById(R.id.img_view);

        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_issue_drugs,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final DrugIssueModel data=list.get(position);

            view.txv_appointmentID.setText("ID: "+data.getAppointmentID());
            view.txv_name.setText(data.getName());
            view.txv_familyMember.setText("Family member: "+data.getFamilyMember());
            view.txv_dob.setText("DOB: "+data.getDob());
            view.txv_gender.setText(data.getGender());
            view.txv_details.setText(data.getAppointmentDetails());
            view.txv_status.setText(data.getdStatus());

            view.img_view.setOnClickListener(v->{

                in=new Intent(cnxt, Issue.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("appointmentID", data.getAppointmentID());
                in.putExtra("familyMember",data.getFamilyMember());
                in.putExtra("name",data.getName());
                in.putExtra("gender",data.getGender());
                in.putExtra("dob",data.getDob());
                in.putExtra("dStatus",data.getdStatus());
                in.putExtra("details",data.getAppointmentDetails());
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
                    ArrayList<DrugIssueModel> filteredList = new ArrayList<>();
                    for (DrugIssueModel androidVersion : list) {
                        if (androidVersion.getFamilyMember().toLowerCase().contains(charString)
                                ||androidVersion.getName().toLowerCase().contains(charString)
                           ||androidVersion.getAppointmentID().toLowerCase().contains(charString)) {
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
                list = (ArrayList<DrugIssueModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
