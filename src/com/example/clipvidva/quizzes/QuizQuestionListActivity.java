package com.example.clipvidva.quizzes;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.clipvidva.R;

public class QuizQuestionListActivity extends Activity {
	private UserAnswersDataSource userAnswersDataSource;
	private QuizQuestionListAdapter quizQuestionListAdapter;
	private String subject_id;
	private String subject_name;
	public static final String SUBJECT_NAME = "subject_name";
	public static final String SUBJECT_ID = "subject_id";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_question_list);
		
		Intent intent = getIntent();
		subject_id = intent.getStringExtra(SUBJECT_ID);
		subject_name = intent.getStringExtra(SUBJECT_NAME);
		
		// Set Title Bar
		setTitle(subject_name);
		
		userAnswersDataSource = new UserAnswersDataSource(getApplicationContext());
		userAnswersDataSource.open();

		List<UserAnswer> userAnswers = userAnswersDataSource.getAllAnswersIn(subject_id);
		
		quizQuestionListAdapter = new QuizQuestionListAdapter();
		
	    GridView gridView = (GridView) findViewById(R.id.quiz_question_grid);
	    gridView.setAdapter(quizQuestionListAdapter);
	    gridView.setOnItemClickListener(new QuestionItemClickListener());
	    for(int i=0; i<userAnswers.size(); i++){
	    	quizQuestionListAdapter.addItem(userAnswers.get(i));
	    }
	    
	    quizQuestionListAdapter.notifyDataSetChanged();
	    gridView.setOnItemClickListener(new QuestionItemClickListener());
        
	}
	
	private void setTitle(String title){
        if (title.length() > 0) {
        	ActionBar actionBar = getActionBar();
        	actionBar.setTitle(title);
        }
	}
	
	private class QuestionItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int question_id,
				long arg3) {
			UserAnswer questionClicked = quizQuestionListAdapter.getItem(question_id);
			Intent intent = new Intent(getApplicationContext(), QuizItemDetailActivity.class);
			intent.putExtra(QuizItemDetailActivity.ARG_ITEM_ID, questionClicked.getQuestion_id()+"");
			intent.putExtra(QuizItemDetailActivity.ARG_SUBJECT_NAME, subject_name);
			intent.putExtra(QuizItemDetailActivity.ARG_SUBJECT_ID, subject_id+"");
			Log.v(this.getClass().getName(), "Put all extras "+questionClicked.getQuestion_id());
			startActivity(intent);
			Log.v(this.getClass().getName(), "Start intent");
		}
	
	}
}
