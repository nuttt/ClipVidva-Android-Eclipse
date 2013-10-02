package com.example.clipvidva;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class VideoListAdapter extends MyListAdapter<VideoWithStatus> {

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflate = LayoutInflater.from(context);
            view = inflate.inflate(R.layout.video_item, viewGroup, false);
        }

        VideoWithStatus video = container.get(i);

        TextView textView = (TextView) view.findViewById(R.id.video_item_text);
        textView.setText(video.getName());
        
        if(video.isStatus()){
        	ImageView imageView = (ImageView) view.findViewById(R.id.icon_video);
        	imageView.setImageResource(R.drawable.grid_button_blue);
        }

        Typeface font_text = Typeface.createFromAsset(context.getAssets(), "fonts/RSU_Regular.ttf");
        textView.setTypeface(font_text);

        return view;
    }
}
