package com.bipul.nrsingdidristict.adapterForAPI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.modelForComplainFechAllGET.Datum;

import java.util.ArrayList;
import java.util.List;

public class ComplainFetchAllAdapter extends RecyclerView.Adapter<ComplainFetchAllAdapter.ViewHolder> {

    private Context context;
    List<Datum> list = new ArrayList<>();

    public ComplainFetchAllAdapter(Context context, List<Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complain_fetch_all_item,
                        parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum data = list.get(position);

        holder.nameOneTV.setText(data.getComplainantName());
        holder.nameTwoTV.setText(data.getDefendantName());
        holder.addressOneTV.setText(data.getComplainantAddress());
        holder.addressTwoTV.setText(data.getDefendantAddress());
        holder.mobileNumberTV.setText(data.getMobileNo());
        holder.complainDetailsTV.setText(data.getComplain());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameOneTV,nameTwoTV,addressOneTV,addressTwoTV,complainDetailsTV,mobileNumberTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameOneTV = itemView.findViewById(R.id.nameOneTV);
            nameTwoTV = itemView.findViewById(R.id.nameTwoTV);
            addressOneTV = itemView.findViewById(R.id.addressOneTV);
            addressTwoTV = itemView.findViewById(R.id.addressTwoTV);
            complainDetailsTV = itemView.findViewById(R.id.complainDetailsTV);
            mobileNumberTV = itemView.findViewById(R.id.mobileNumberTV);
        }
    }
}
