package com.example.clipvidva;

import com.example.clipvidva.quizzes.QuizCategoryListActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by nuttt on 12/9/13.
 */
public class Splash extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);

        Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/fontawesome-webfont.ttf");
        TextView icon_video = (TextView)findViewById(R.id.splash_watch_clip_icon);
        icon_video.setTypeface(font);
        TextView icon_pencil = (TextView)findViewById(R.id.splash_exam_icon);
        icon_pencil.setTypeface(font);

        Typeface font_text = Typeface.createFromAsset(this.getAssets(), "fonts/RSU_Regular.ttf");
        TextView text_video = (TextView)findViewById(R.id.splash_watch_clip_text);
        text_video.setTypeface(font_text);
        TextView text_exam = (TextView)findViewById(R.id.splash_exam_text);
        text_exam.setTypeface(font_text);


    }

    public boolean goToCategory(View view){
        Intent intent = new Intent(this, CategoryListActivity.class);
        startActivity(intent);
        return true;
    }

    public boolean goToQuiz(View view){
    	Log.v(this.getClass().getName(), "Click the quiz button");
        Intent intent = new Intent(this, QuizCategoryListActivity.class);
        startActivity(intent);
        Log.v(this.getClass().getName(), "Start quiz activity");
        return true;
    }
}