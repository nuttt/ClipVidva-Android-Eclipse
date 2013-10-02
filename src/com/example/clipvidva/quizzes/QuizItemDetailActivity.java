package com.example.clipvidva.quizzes;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.clipvidva.R;
import com.example.clipvidva.paint.PaintActivity;

public class QuizItemDetailActivity extends FragmentActivity {

    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_SUBJECT_NAME = "subject_name";
    public static final String ARG_SUBJECT_ID = "subject_id";
    private Question question;
    private UserAnswer userAnswer;
    private int choice;
    private String question_id;
    private String subject_id;
    private String subject_name;
    private QuestionsDataSource questionsDataSource;
    private UserAnswersDataSource userAnswersDataSource;
    private Typeface font_text;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_fragment_item_detail);
        Bundle extras = getIntent().getExtras();
        question_id = extras.getString(ARG_ITEM_ID);
        subject_id = extras.getString(ARG_SUBJECT_ID);
        subject_name = extras.getString(ARG_SUBJECT_NAME);
        font_text = Typeface.createFromAsset(getAssets(), "fonts/RSU_Regular.ttf");
        Log.v(this.getClass().getName(), "SUBJECT_NAME "+subject_name);
        Log.v(this.getClass().getName(), "ITEM_ID "+question_id);
        Log.v(this.getClass().getName(), "SUBJECT_ID "+subject_id);
        
        questionsDataSource = new QuestionsDataSource(getApplicationContext());
        questionsDataSource.open();
        question = questionsDataSource.getOneQuestion(subject_id, question_id);
        
        userAnswersDataSource = new UserAnswersDataSource(getApplicationContext());
        userAnswersDataSource.open();
        userAnswer = userAnswersDataSource.getUserAnswer(subject_id, question_id);
        
        setTitle(subject_name);
        render();
    }
    
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate close for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.question_bar, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.close_activity:
	        	finish();
	            return true;
	        case R.id.paint_acitivity:
		        Intent detailIntent = new Intent(getApplicationContext(), PaintActivity.class);
		        startActivity(detailIntent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    
	private void setTitle(String title){
        if (title.length() > 0) {
        	ActionBar actionBar = getActionBar();
        	actionBar.setTitle(title);
        }
	}
	
	private void render(){
        if (question != null) {
        	
        	// Set Choices into view
        	setChoices();
            
            // Set Question text into view
        	setQuestion();
        	setHintHidden();
        	
        	// Set Result text into view
        	setResult();
        	
        	// Submit Button Listener
	        Button submitButton = (Button) findViewById(R.id.submit_button);
	        submitButton.setTypeface(font_text);
	        submitButton.setOnClickListener(new QuizButtonOnClickListener());
	        
        	// Hint Button Listener
	        Button hintButton = (Button) findViewById(R.id.hint_button);
	        hintButton.setTypeface(font_text);
	        hintButton.setOnClickListener(new QuizHintButtonOnClickListener());
	        
	        // Radio Listener
	        RadioGroup choiceGroup = (RadioGroup) findViewById(R.id.choices);
	        for (int i = 0; i < choiceGroup.getChildCount(); i++) {
	            ((RadioButton) choiceGroup.getChildAt(i)).setOnClickListener(new QuizRadioButtonOnClickListener());
	        }
	        if (userAnswer.getAnswer().length() > 0){
		        switch (Integer.parseInt(userAnswer.getAnswer())) {
			        case 1:
			            choiceGroup.check(R.id.choice1);
			            choice = 1;
			            break;
			        case 2:
			            choiceGroup.check(R.id.choice2);
			            choice = 2;
			            break;
			        case 3:
			            choiceGroup.check(R.id.choice3);
			            choice = 3;
			            break;
			        case 4:
			            choiceGroup.check(R.id.choice4);
			            choice = 4;
			            break;
		        }
	        }
        }
        
	}
	
    private void setChoices(){
        RadioGroup choiceGroup = (RadioGroup) findViewById(R.id.choices);
        String[] choices = question.getChoices().split("\\|");
        for (int i = 0; i < choiceGroup.getChildCount(); i++) {
            ((RadioButton) choiceGroup.getChildAt(i)).setText(choices[i]);
        }
    }
    
    private void setQuestion(){
    	TextView questionView = ((TextView) findViewById(R.id.question_title));
    	questionView.setText(question.getQuestion());
        questionView.setTypeface(font_text);
    	questionView.postInvalidate();
    }
    
    private void setHintHidden(){
    	TextView hintView = ((TextView) findViewById(R.id.hint));
    	hintView.setVisibility(View.GONE);
    }
    
    private void setHint(){
    	TextView hintView = ((TextView) findViewById(R.id.hint));
    	String hintText = question.getHint();
    	hintView.setVisibility(TextView.VISIBLE);
    	if(hintText.length() > 0){
    		hintView.setText(question.getHint());
    	}
    	else{
    		hintView.setText(R.string.no_hint);
    	}
    	hintView.postInvalidate();
    }
    
    private void setResult(){
    	String resultText = userAnswer.getResult();
    	TextView resultView = ((TextView) findViewById(R.id.result));
    	if(resultText.equals("Correct")){
    		resultView.setText(R.string.icon_correct);
    	}
    	else{
    		resultView.setText("");
    	}
		Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/fontawesome-webfont.ttf");
		resultView.setTypeface(font);
		resultView.postInvalidate();
    }
    
    public void getNextQuestion(){
		Intent intent = new Intent(getApplicationContext(), QuizItemDetailActivity.class);
		int nextQuestionNo = Integer.parseInt(question_id)+1;
		intent.putExtra(QuizItemDetailActivity.ARG_ITEM_ID, nextQuestionNo+"");
		intent.putExtra(QuizItemDetailActivity.ARG_SUBJECT_NAME, subject_name);
		intent.putExtra(QuizItemDetailActivity.ARG_SUBJECT_ID, subject_id+"");
		startActivity(intent);
		finish();
    }

    private class QuizRadioButtonOnClickListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
	    	// Is the button now checked?
	        boolean checked = ((RadioButton) view).isChecked();
	        
	        // Check which radio button was clicked
	        switch(view.getId()) {
		        case R.id.choice1:
		            if (checked){
		            	Log.v(this.getClass().getName(), "Choice1 selected");
		            	choice = 1;
		            }
		            break;
		        case R.id.choice2:
		            if (checked){
		            	Log.v(this.getClass().getName(), "Choice2 selected");
		            	choice = 2;
		            }
		            break;
		        case R.id.choice3:
		            if (checked){
		            	Log.v(this.getClass().getName(), "Choice3 selected");
		            	choice = 3;
		            }
		            break;
		        case R.id.choice4:
		            if (checked){
		            	Log.v(this.getClass().getName(), "Choice4 selected");
		            	choice = 4;
		            }
		            break;
	        }
		}
    }
    private class QuizButtonOnClickListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
	    	Log.v(this.getClass().getName(), "Choice entered: "+ Integer.toString(choice));
	    	Log.v(this.getClass().getName(), "Answer: "+ question.getAnswer());
	    	if (isCorrectChoice()) {
	    		userAnswer.setResult("Correct");
	    		Log.v(this.getClass().getName(), "Correct!");
	    		CorrectDialogFragment correctDialog = new CorrectDialogFragment();
	    		correctDialog.setDescription(question.getDescription());
	    		boolean isNext = questionsDataSource.getNumberOfQuestions(Integer.parseInt(subject_id)) != question.getId();
	    		correctDialog.setNext(isNext);
	    		correctDialog.show(getSupportFragmentManager(), "CorrectDialogFragment");
	    	}
	    	else{
	    		userAnswer.setResult("Incorrect");
	    		Log.v(this.getClass().getName(), "Incorrect!");
	    		IncorrectDialogFragment incorrectDialog = new IncorrectDialogFragment();
	    		incorrectDialog.show(getSupportFragmentManager(), "IncorrectDialogFragment");	    		
	    	}
    		userAnswersDataSource.editAnswer(userAnswer.getSubject_id(), 
					 userAnswer.getQuestion_id(), 
					 Integer.toString(choice), 
					 userAnswer.getResult());
	    	setResult();
		}
		
		private boolean isCorrectChoice(){
			if (choice == Integer.parseInt(question.getAnswer())){
				return true;
			}
			return false;
		}
    }
    private class QuizHintButtonOnClickListener implements View.OnClickListener {
		@Override
		public void onClick(View view) {
			setHint();
		}
    }
    
}
