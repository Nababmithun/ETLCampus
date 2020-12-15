package com.bipul.nrsingdidristict.adapterForAPI;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bipul.nrsingdidristict.R;
import com.bipul.nrsingdidristict.activity.ASubjectSaveActivity;
import com.bipul.nrsingdidristict.activity.PublicHeadingSaveActivity;
import com.bipul.nrsingdidristict.common.Common;
import com.bipul.nrsingdidristict.interfaces.ApiInterface;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectDeletePOST.AppointmentSubjectDeleteResponse;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectFetchAllGET.Datum;
import com.bipul.nrsingdidristict.modelForAppointmentSubjectUpdatePOST.AppointmentSubjectUpdateResponse;
import com.bipul.nrsingdidristict.webApi.RetrofitClient;
import com.developer.kalert.KAlertDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentFetchAllAdapter extends RecyclerView.Adapter<AppointmentFetchAllAdapter.ViewHolder> {

    private Context context;
    private List<Datum> list = new ArrayList<>();
    int id;

    private RecyclerViewClickListener mListener;

    private ApiInterface apiService;

    public AppointmentFetchAllAdapter(Context context, List<Datum> list, RecyclerViewClickListener listener) {
        this.context = context;
        this.list = list;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public AppointmentFetchAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_appointment_item_show_row,
                        parent, false);
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentFetchAllAdapter.ViewHolder holder, int position) {
        Datum suj = list.get(position);

        id = suj.getId();
        holder.titleTV.setText(suj.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTV;
        private ImageView moreIV, deleteIV, updateIV;

        private RecyclerViewClickListener mListener;
        private RelativeLayout mRowContainer;

        public ViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);

            titleTV = itemView.findViewById(R.id.nameTitleTV);

            mRowContainer = itemView.findViewById(R.id.row_container);
            mListener = listener;
            mRowContainer.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;

                default:
                    break;
            }
        }
    }


    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }

}
