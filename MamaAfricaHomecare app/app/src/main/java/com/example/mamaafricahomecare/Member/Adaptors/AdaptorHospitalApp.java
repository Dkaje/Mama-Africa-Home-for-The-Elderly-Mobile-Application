package com.example.mamaafricahomecare.Member.Adaptors;

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

import com.example.mamaafricahomecare.Member.HospitalDetails;
import com.example.mamaafricahomecare.Staff.CareGiver.AppointmentDetails;
import com.example.mamaafricahomecare.Member.Models.HospitalAppointmentModel;
import com.example.mamaafricahomecare.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptorHospitalApp extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HospitalAppointmentModel> list,searchItems;
     private Context cnxt;
    private  Intent in;

    public AdaptorHospitalApp(Context context, List<HospitalAppointmentModel> list) {
        this.list = list;
        this.searchItems=list;
        cnxt=context;
    }
    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        private TextView txv_name,txv_gender,txv_dob,txv_appointmentID,
                txv_status,txv_familyMember,txv_date;
        private ImageView img_view;
        public OriginalViewHolder(@NonNull View vw) {
            super(vw);
            txv_appointmentID=vw.findViewById(R.id.txv_appointmentID);
            txv_name=vw.findViewById(R.id.txv_name);
            txv_familyMember=vw.findViewById(R.id.txv_familyMember);
            txv_gender=vw.findViewById(R.id.txv_gender);
            txv_dob=vw.findViewById(R.id.txv_dob);
            txv_status=vw.findViewById(R.id.txv_status);
            txv_date=vw.findViewById(R.id.txv_date);
            img_view=vw.findViewById(R.id.img_view);

        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        RecyclerView.ViewHolder viewHolder;
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_bookings,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final HospitalAppointmentModel data=list.get(position);

            view.txv_appointmentID.setText("ID: "+data.getAppointmentID());
            view.txv_name.setText(data.getName());
            view.txv_dob.setText("DOB: "+data.getDob());
            view.txv_gender.setText(data.getGender());
            view.txv_date.setText("Appointment date: "+data.getAppointmentDate());
            view.txv_status.setText("Status: "+data.getAppointmentStatus());

            view.txv_familyMember.setVisibility(View.GONE);

            view.img_view.setOnClickListener(v->{

                in=new Intent(cnxt, HospitalDetails.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("appointmentID", data.getAppointmentID());
                in.putExtra("gender",data.getGender());
                in.putExtra("name",data.getName());
                in.putExtra("careGiver",data.getCareGiver());
                in.putExtra("dob",data.getDob());
                in.putExtra("appointmentStatus",data.getAppointmentStatus());
                in.putExtra("appointmentDate",data.getAppointmentDate());
                in.putExtra("details",data.getAppointmentDetails());
                in.putExtra("careGiver",data.getCareGiver());
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
                    ArrayList<HospitalAppointmentModel> filteredList = new ArrayList<>();
                    for (HospitalAppointmentModel androidVersion : list) {
                        if (androidVersion.getAppointmentID().toLowerCase().contains(charString)) {
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
                list = (ArrayList<HospitalAppointmentModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
