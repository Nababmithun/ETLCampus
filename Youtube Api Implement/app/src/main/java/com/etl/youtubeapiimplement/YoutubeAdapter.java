package com.etl.youtubeapiimplement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.etl.youtubeapiimplement.model.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeViewHolder> {
    private Context context;
    private List<Datum> videoList;

    public YoutubeAdapter(Context context, List<Datum> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public YoutubeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vedio_item_layout,
                        parent, false);
        return new YoutubeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final YoutubeViewHolder holder, final int position) {
        String link = "https://www.youtube.com/embed/";
        final Datum datum = videoList.get(position);
      //  holder.webView.loadUrl("https://www.youtube.com/embed/"+datum.getLink());
        Picasso.get().load(datum.getCover())
                .placeholder(R.drawable.ic_photo_library_gray_24dp)
                .into(holder.thumbleIV);

        holder.titleTv.setText(datum.getTitle());
        holder.playBtnIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goIntent = new Intent(context, ShowVideoActivity.class);
                goIntent.putExtra("link", datum.getLink());
                goIntent.putExtra("title",datum.getTitle());

                context.startActivity(goIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }


    public class YoutubeViewHolder extends RecyclerView.ViewHolder {

       ImageView thumbleIV,playBtnIV;

       TextView titleTv;
        @SuppressLint("SetJavaScriptEnabled")
        public YoutubeViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbleIV = itemView.findViewById(R.id.thumbleIV);
            playBtnIV = itemView.findViewById(R.id.playBtnIV);
            titleTv = itemView.findViewById(R.id.titleItemTV);
        }


    }
}
