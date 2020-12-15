package com.bipul.nrsingdidristict.adapter;

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
import com.bipul.nrsingdidristict.model.Stuff;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StuffRecyclerView extends RecyclerView.Adapter<StuffRecyclerView.ViewHolder> {

    private Context context;
    private List<Stuff> stuffList = new ArrayList<>();

    public StuffRecyclerView(Context context, List<Stuff> stuffList) {
        this.context = context;
        this.stuffList = stuffList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.staff_design1,
                        parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       Stuff stuff =  stuffList.get(position);
       holder.stuffImageIV.setImageResource(stuff.getStuffImage());
       holder.nameTV.setText(stuff.getName());
       holder.phoneNumberTV.setText(stuff.getMobileNumber());
       holder.mailTV.setText(stuff.getMail());
       holder.designationTV.setText(stuff.getDesignation());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               context.startActivity(new Intent(context, ProfileActivity.class));
           }
       });

    }

    @Override
    public int getItemCount() {
        return stuffList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView stuffImageIV;
        TextView nameTV,phoneNumberTV,designationTV,mailTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stuffImageIV = itemView.findViewById(R.id.stuffImageIV);
            nameTV = itemView.findViewById(R.id.nameTV);
            phoneNumberTV = itemView.findViewById(R.id.phoneNumberTV);
            designationTV = itemView.findViewById(R.id.designationTV);
            mailTV = itemView.findViewById(R.id.mailTV);
        }
    }
}
