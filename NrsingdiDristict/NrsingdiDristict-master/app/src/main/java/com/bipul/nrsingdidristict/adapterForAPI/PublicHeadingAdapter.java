package com.bipul.nrsingdidristict.adapterForAPI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.activity.PublicHearingDetailsActivity;
import com.bipul.nrsingdidristict.activity.PublicHearingEditorActivity;
import com.bipul.nrsingdidristict.activity.SendMessageActivity;
import com.bipul.nrsingdidristict.common.Common;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelForPublicHearingDeletePOST.SetPublicHearingDeleteResponse;
import com.bipul.nrsingdidristict.modelPublicHeadingGET.Datum;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublicHeadingAdapter extends RecyclerView.Adapter<PublicHeadingAdapter.ViewHolder> {

    private Context context;
    private List<Datum> publicHearingList = new ArrayList<>();

    private ApiInterface apiService;

    public PublicHeadingAdapter(Context context, List<Datum> publicHearingList) {
        this.context = context;
        this.publicHearingList = publicHearingList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.public_hearing_item_layout,
                        parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Datum info = publicHearingList.get(position);
        holder.nameTV.setText(info.getName());
        holder.mobileTV.setText(info.getMobileNo());
        holder.addressTV.setText(info.getAddress());
        holder.titleTV.setText(info.getSubject());
        holder.descriptionTV.setText(info.getDescription());

        holder.sendMessageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SendMessageActivity.class);

                intent.putExtra("info", info);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PublicHearingDetailsActivity.class);
                intent.putExtra("data", info);
                context.startActivity(intent);
            }
        });

        holder.deleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, PublicHearingEditorActivity.class);
//                intent.putExtra("allData", info);
//                context.startActivity(intent);

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setMessage("Delete this item?");
                dialog.setPositiveButton("Yes" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData(info.getId());
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    private void deleteData( final int id) {
        apiService = RetrofitClient.getRetrofit().create(ApiInterface.class);
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();
        //readMode();
        //delete operation
        apiService = RetrofitClient.getRetrofit().create(ApiInterface.class);

        apiService.setPublicHearingDeleteResponse(Common.APP_KEY, id, "1").enqueue(new Callback<SetPublicHearingDeleteResponse>() {
            @Override
            public void onResponse(Call<SetPublicHearingDeleteResponse> call, Response<SetPublicHearingDeleteResponse> response) {

                if (response.code() == 200) {
                    Toast.makeText(context, "" + response.body().getMessage()+"", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "problem", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SetPublicHearingDeleteResponse> call, Throwable t) {

                Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

        progressDialog.dismiss();
    }

    @Override
    public int getItemCount() {
        return publicHearingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTV,mobileTV,addressTV,titleTV,descriptionTV;
        ImageView sendMessageIV,deleteIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.nameTV);
            mobileTV = itemView.findViewById(R.id.mobileTV);
            addressTV = itemView.findViewById(R.id.addresTV);
            titleTV = itemView.findViewById(R.id.titleTV);
            descriptionTV = itemView.findViewById(R.id.descriptionTV);
            sendMessageIV = itemView.findViewById(R.id.sendMessageIV);
            deleteIV = itemView.findViewById(R.id.deleteIV);
        }
    }
}
