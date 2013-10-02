package com.example.clipvidva.quizzes;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.clipvidva.R;
import com.example.clipvidva.Subject;
import com.example.clipvidva.SubjectsDataSource;

public class QuizSubjectListActivity extends FragmentActivity {

    private List<Subject> subjects;
    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_NAME = "item_name";
	public static final String SUBJECT_NAME = "subject_name";
	public static final String SUBJECT_ID = "subject_id";
    private SubjectsDataSource subjectsDataSource;
    private UserAnswersDataSource userAnswersDataSource;
    private QuizSubjectListAdapter quizSubjectListAdapter;
    private ListView listView;
    private String category_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_subject_list);
        Bundle extras = getIntent().getExtras();
        category_id = extras.getString(ARG_ITEM_ID);
        String name = extras.getString(ARG_ITEM_NAME);
        Log.v(this.getClass().getName(), "ITEM_NAME "+name);
        
        // Set title bar
        setActionBarTitle(name);
        Log.v(this.getClass().getName(), "progressbar1");
        listView = (ListView)findViewById(R.id.quiz_subject_list_view);
        
        // Get subjects
        Log.v(this.getClass().getName(), "get subjects");
        subjectsDataSource = new SubjectsDataSource(getApplicationContext());
        subjectsDataSource.open();
        subjects = subjectsDataSource.getAllSubjectsIn(category_id);
        
        // Get score
        Log.v(this.getClass().getName(), "userAnswersDataSource");
        userAnswersDataSource = new UserAnswersDataSource(getApplicationContext());
        userAnswersDataSource.open();
          
    }
    
    public void onResume(){
    	super.onResume();
		quizSubjectListAdapter = new QuizSubjectListAdapter();
	    for(int i=0; i<subjects.size(); i++){
	    	Subject subject = subjects.get(i);
	    	int numberOfQuestions = userAnswersDataSource.getNumberOfQuestions(subject.getId());
	    	int numberOfCorrectAnswers = userAnswersDataSource.getNumberOfCorrectAnswers(subject.getId());
	    	SubjectWithProgress subjectWithProgress = SubjectWithProgress.parseSubjectWithProgress(
	    											  subject, numberOfCorrectAnswers, numberOfQuestions);
	    	quizSubjectListAdapter.addItem(subjectWithProgress);
	    }
	    listView.setAdapter(quizSubjectListAdapter);
	    listView.setOnItemClickListener(new SubjectItemClickListener());
	    Log.v(this.getClass().getName(), "Notify");
	    quizSubjectListAdapter.notifyDataSetChanged();  
    }
    
/*
    @Override
    
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        String sId = Integer.toString(subjects.get(position).getId());
        String sName = subjects.get(position).getName();
        mCallbacks.onItemSelected(sId, sName);
        Log.v(this.getClass().getName(), "Clicked subject named "+ sName);        
    }*/

    public void setActionBarTitle(String title) {
    	getActionBar().setTitle(title);
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
	        	Log.v(this.getClass().getName(), "Close button clicked");
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
    private class SubjectItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			
			String sId = Integer.toString(subjects.get(position).getId());
	        String sName = subjects.get(position).getName();
	        
	        Intent intent = new Intent(getApplicationContext(), QuizQuestionListActivity.class);
	        intent.putExtra(QuizQuestionListActivity.SUBJECT_ID, sId);
	        intent.putExtra(QuizQuestionListActivity.SUBJECT_NAME, sName);
	        startActivity(intent);
		}
    	
    }
    
}
