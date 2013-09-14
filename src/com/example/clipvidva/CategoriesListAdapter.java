package com.example.clipvidva;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by nuttt on 13/9/13.
 */
public class CategoriesListAdapter extends BaseAdapter {

    private ArrayList<Category> categories;

    public CategoriesListAdapter() {
        categories = new ArrayList<Category>();
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(Category c) {
        categories.add(c);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflate = LayoutInflater.from(context);
            view = inflate.inflate(R.layout.category_item, viewGroup, false);
        }


        Category category = categories.get(i);

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
