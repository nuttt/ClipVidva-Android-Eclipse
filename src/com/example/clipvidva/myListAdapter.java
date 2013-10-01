package com.example.clipvidva;

import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyListAdapter<T> extends BaseAdapter {

    protected ArrayList<T> container;

    public MyListAdapter() {
        container = new ArrayList<T>();
    }

    @Override
    public int getCount() {
        return container.size();
    }

    @Override
    public Object getItem(int i) {
        return container.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(T c) {
        container.add(c);
    }

    @Override
    public abstract View getView(int i, View view, ViewGroup viewGroup);
}
