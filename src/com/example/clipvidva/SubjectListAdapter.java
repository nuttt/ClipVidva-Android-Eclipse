package com.example.clipvidva;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SubjectListAdapter extends MyListAdapter<Subject> {

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflate = LayoutInflater.from(context);
            view = inflate.inflate(R.layout.subject_item, viewGroup, false);
        }


        Subject subject = container.get(i);

        TextView textView = (TextView) view.findViewById(R.id.subject_item_text);
        textView.setText(subject.getName());


        TextView iconView = (TextView) view.findViewById(R.id.icon_arrow);
		Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-webfont.ttf");
		iconView.setTypeface(font);
		
        Typeface font_text = Typeface.createFromAsset(context.getAssets(), "fonts/RSU_Regular.ttf");
        textView.setTypeface(font_text);

        return view;
    }
}
