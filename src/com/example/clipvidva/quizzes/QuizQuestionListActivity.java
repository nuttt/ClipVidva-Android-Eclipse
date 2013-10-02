package com.example.clipvidva.quizzes;

import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.clipvidva.R;

public class QuizQuestionListActivity extends Activity {
	private UserAnswersDataSource userAnswersDataSource;
	private QuizQuestionListAdapter quizQuestionListAdapter;
	private List<UserAnswer> userAnswers;
	private String subject_id;
	private String subject_name;
	private GridView gridView;
	public static final String SUBJECT_NAME = "subject_name";
	public static final String SUBJECT_ID = "subject_id";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz_question_list);
		
		Intent intent = getIntent();
		subject_id = intent.getStringExtra(SUBJECT_ID);
		subject_name = intent.getStringExtra(SUBJECT_NAME);
		
		// Set Title Bar
		setTitle(subject_name);
		
		userAnswersDataSource = new UserAnswersDataSource(getApplicationContext());
		userAnswersDataSource.open();
	    gridView = (GridView) findViewById(R.id.quiz_question_grid);
        
	}
	
	protected void onResume(){
		super.onResume();
		quizQuestionListAdapter = new QuizQuestionListAdapter();
		userAnswers = userAnswersDataSource.getAllAnswersIn(subject_id);
	    for(int i=0; i<userAnswers.size(); i++){
	    	quizQuestionListAdapter.addItem(userAnswers.get(i));
	    }
	    gridView.setAdapter(quizQuestionListAdapter);	
	    gridView.setOnItemClickListener(new QuestionItemClickListener());    
	    quizQuestionListAdapter.notifyDataSetChanged();
	}
	
	private void setTitle(String title){
    	ActionBar actionBar = getActionBar();
    	actionBar.setTitle(title);
    	//actionBar.setDisplayHomeAsUpEnabled(true);
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
			startActivity(intent);
		}
	
	}
	

	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate close for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.close_bar, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.close_activity:
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
