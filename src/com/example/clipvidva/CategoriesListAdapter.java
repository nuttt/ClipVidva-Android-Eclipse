package com.example.clipvidva;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoriesListAdapter extends MyListAdapter<Category> {

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflate = LayoutInflater.from(context);
            view = inflate.inflate(R.layout.category_item, viewGroup, false);
        }


        Category category = container.get(i);

        ImageView imageView = (ImageView) view.findViewById(R.id.category_item_image);
        int imageID = context.getResources().getIdentifier(category.getImg(), "drawable", context.getPackageName());
        imageView.setImageResource(imageID);

        TextView textView = (TextView) view.findViewById(R.id.category_item_text);
        textView.setText(category.getName());

        Typeface font_text = Typeface.createFromAsset(context.getAssets(), "fonts/RSU_Regular.ttf");
        textView.setTypeface(font_text);

        return view;
    }
}
