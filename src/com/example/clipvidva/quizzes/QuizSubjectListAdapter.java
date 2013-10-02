package com.example.clipvidva.quizzes;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.clipvidva.MyListAdapter;
import com.example.clipvidva.R;

public class QuizSubjectListAdapter extends MyListAdapter<SubjectWithProgress> {
	
	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflate = LayoutInflater.from(context);
            view = inflate.inflate(R.layout.quiz_subject_item_list, viewGroup, false);
        }
        
        Log.v(this.getClass().getName(), "Set Adapter2");
        SubjectWithProgress subject = container.get(i);
        
        TextView textView = (TextView) view.findViewById(R.id.quiz_subject_text);
        textView.setText(subject.getName());
        Typeface font_text = Typeface.createFromAsset(context.getAssets(), "fonts/RSU_Regular.ttf");
        textView.setTypeface(font_text);
        
    	ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.quiz_subject_score);
    	//progressBar.getProgressDrawable().setColorFilter(Color.RED, Mode.OVERLAY);
    	progressBar.setProgress(subject.getProgress());
    	progressBar.setMax(subject.getMaxProgress());
        return view;
    }

}
