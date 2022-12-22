package com.example.mamaafricahomecare.Staff.LabTech.Adaptor;

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
import com.example.mamaafricahomecare.Staff.Doctors.Model.LabTestModel;
import com.example.mamaafricahomecare.Staff.Doctors.TestResults;
import com.example.mamaafricahomecare.Staff.LabTech.SendResults;

import java.util.ArrayList;
import java.util.List;

public class AdaptorTest extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<LabTestModel> list,searchItems;
     private Context cnxt;
    private  Intent in;

    public AdaptorTest(Context context, List<LabTestModel> list) {
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
                .inflate(R.layout.layout_lab_test,parent,false);
        viewHolder=new OriginalViewHolder(view);
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof  OriginalViewHolder){
            final OriginalViewHolder view=(OriginalViewHolder) holder;
            final LabTestModel data=list.get(position);

            view.txv_appointmentID.setText("ID: "+data.getAppointmentID());
            view.txv_name.setText(data.getName());
            view.txv_familyMember.setText("Family member: "+data.getFamilyMember());
            view.txv_dob.setText("DOB: "+data.getDob());
            view.txv_gender.setText(data.getGender());
            view.txv_date.setText("Appointment date: "+data.getAppointmentDate()+"\n"+data.getRemarks());
            view.txv_status.setText("Status: "+data.getTestStatus());

                view.img_view.setOnClickListener(v->{
               in=new Intent(cnxt, SendResults.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("appointmentID", data.getAppointmentID());
                in.putExtra("test",data.getRemarks());
                in.putExtra("testStatus",data.getTestStatus());
                in.putExtra("testResult",data.getTestResults());
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
                    ArrayList<LabTestModel> filteredList = new ArrayList<>();
                    for (LabTestModel androidVersion : list) {
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
                list = (ArrayList<LabTestModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
