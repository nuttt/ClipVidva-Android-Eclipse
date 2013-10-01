package com.example.clipvidva.quizzes;


import com.example.clipvidva.MyListAdapter;
import com.example.clipvidva.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuizQuestionListAdapter extends MyListAdapter<Question> {

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflate = LayoutInflater.from(context);
            view = inflate.inflate(R.layout.quiz_question_item_list, viewGroup, false);
        }
        
        Question question = container.get(i);

        TextView textView = (TextView) view.findViewById(R.id.quiz_question_id);
        if(textView == null){
        	Log.v(this.getClass().getName(), "TextView Null");
        }
        textView.setText(Integer.toString(question.getId()));

        return view;
    }
}
