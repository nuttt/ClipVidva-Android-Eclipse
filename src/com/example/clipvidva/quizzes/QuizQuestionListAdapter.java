package com.example.clipvidva.quizzes;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clipvidva.MyListAdapter;
import com.example.clipvidva.R;

public class QuizQuestionListAdapter extends MyListAdapter<UserAnswer> {

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflate = LayoutInflater.from(context);
            view = inflate.inflate(R.layout.quiz_question_item_list, viewGroup, false);
        }
        
        UserAnswer userAnswer = container.get(i);

        TextView textView = (TextView) view.findViewById(R.id.quiz_question_id);
        ImageView iconView = (ImageView) view.findViewById(R.id.iconView);
        textView.setText(Integer.toString(userAnswer.getQuestion_id()));
        Log.v(this.getClass().getName(), "Result: "+userAnswer.getResult());
        if(userAnswer.getResult().equals("Correct")){
        	iconView.setImageResource(R.drawable.grid_item_correct);
        	Log.v(this.getClass().getName(), "Change to correct");
        	iconView.postInvalidate();
        }

        return view;
    }
}
