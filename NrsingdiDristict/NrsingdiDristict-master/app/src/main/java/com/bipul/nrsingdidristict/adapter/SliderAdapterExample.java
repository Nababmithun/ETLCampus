package com.bipul.nrsingdidristict.adapter;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bipul.nrsingdidristict.R;
import com.smarteist.autoimageslider.SliderViewAdapter;


public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;

    public SliderAdapterExample(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {

        switch (position) {
            case 0:
                viewHolder.imageViewBackground.setImageResource(R.drawable.slider1);
                viewHolder.titleTV.setText("হারানো ফোনের ডাটা মুছে ফেলার উপায়");
                break;
            case 1:
                viewHolder.imageViewBackground.setImageResource(R.drawable.slider2);
                viewHolder.titleTV.setText("হারানো ফোনের ডাটা মুছে ফেলার উপায়");
                break;
            case 2:
                viewHolder.imageViewBackground.setImageResource(R.drawable.slider3);
                viewHolder.titleTV.setText("হারানো ফোনের ডাটা মুছে ফেলার উপায়");
                break;
            default:
                viewHolder.imageViewBackground.setImageResource(R.drawable.slider4);
                viewHolder.titleTV.setText("হারানো ফোনের ডাটা মুছে ফেলার উপায়");
                break;
        }

    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 4;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        private ImageView imageViewBackground;
        private TextView titleTV;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageViewBackground = itemView.findViewById(R.id.sliderIV);
            titleTV = itemView.findViewById(R.id.titleTV);
        }
    }
}