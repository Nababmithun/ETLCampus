package com.bipul.nrsingdidristict.adapterForAPI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bipul.nrsingdidristict.activity.ProfileActivity;
import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.modelForEmployeeGET.Datum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private Context context;
    private List<Datum> employeeList = new ArrayList<>();

    public EmployeeAdapter(Context context, List<Datum> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employee_item_layout,
                        parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       Datum employee =  employeeList.get(position);
        String image = "http://apis.digiins.gov.bd/district_app/public/employee/"+employee.getPicture();

        Picasso.get().load("http://apis.digiins.gov.bd/district_app/public/employee/"+employee.getPicture()).placeholder(R.drawable.default_icon)
                .into(holder.profileIV);
       holder.nameTV.setText(employee.getName());
       holder.phoneNumberTV.setText(employee.getMobileNo());
       holder.mailTV.setText(employee.getEmail());
       holder.designationTV.setText(employee.getDesignation());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context,ProfileActivity.class);
               intent.putExtra("employee", employee);
               context.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profileIV;
        TextView nameTV,phoneNumberTV,designationTV,mailTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileIV = itemView.findViewById(R.id.employeeImageIV);
            nameTV = itemView.findViewById(R.id.nameTV);
            phoneNumberTV = itemView.findViewById(R.id.phoneNumberTV);
            designationTV = itemView.findViewById(R.id.designationTV);
            mailTV = itemView.findViewById(R.id.emailTV);
        }
    }
}
