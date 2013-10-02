package com.example.clipvidva.quizzes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.clipvidva.MyListAdapter;
import com.example.clipvidva.R;
import com.example.clipvidva.Subject;

public class QuizSubjectListAdapter extends MyListAdapter<Subject> {

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflate = LayoutInflater.from(context);
            view = inflate.inflate(R.layout.quiz_subject_item_list, viewGroup, false);
        }
        
        Log.v(this.getClass().getName(), "Set Adapter2");
        Subject subject = container.get(i);
        Log.v(this.getClass().getName(), "Set Adapter3");
        TextView textView = (TextView) view.findViewById(R.id.quiz_subject_text);
        textView.setText(subject.getName());

        return view;
    }

}
