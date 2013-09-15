package com.example.clipvidva.quizzes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.clipvidva.R;

public class QuizItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ITEM_NAME = "item_name";
    private Question question;
    private int choice;
    private LayoutInflater inflater;

    public QuizItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getActivity().getIntent().getExtras();
        String id = extras.getString(ARG_ITEM_ID);
        String name = extras.getString(ARG_ITEM_NAME);
    	String id2 = getArguments().getString(ARG_ITEM_ID);
    	String name2 = getArguments().getString(ARG_ITEM_NAME);
        Log.v(this.getClass().getName(), "ITEM_NAME "+name);
        Log.v(this.getClass().getName(), "ITEM_ID "+id);
    	Log.v(this.getClass().getName(), "ITEM_ID2 "+id2);
        Log.v(this.getClass().getName(), "ITEM_NAME2 "+name2);

        QuestionsDataSource questionsDataSource = new QuestionsDataSource(getActivity());
        questionsDataSource.open();
        question = questionsDataSource.getOneQuestion("1", "1");
        
        if (name.length() > 0 && id.length() > 0) {
            ((QuizItemDetailActivity)getActivity()).setActionBarTitle(name);
        }
        
/*
        Bundle extras = getActivity().getIntent().getExtras();
        String id = extras.getString(ARG_ITEM_ID);
        String name = extras.getString(ARG_ITEM_NAME);
        Log.v(this.getClass().getName(), "ITEM_NAME "+name);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
            String name = getArguments().getString(ARG_ITEM_NAME);
            Log.v(this.getClass().getName(), "ITEM_NAME "+name);
            
            // Set title bar
            ((QuizItemDetailActivity)getActivity()).setActionBarTitle(name);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.quiz_fragment_item_detail, container, false);
        this.inflater = inflater;   
        
        if (question != null) {
        	
        	Log.v(this.getClass().getName(), "Question: " + question.getQuestion());
        	
        	// Set Choices into view
        	setChoices(rootView);
            
            // Set Question text into view
        	setQuestion(rootView);
        	
        	// Button Listener
	        Button button = (Button) rootView.findViewById(R.id.submit_button);
	        button.setOnClickListener(new QuizButtonOnClickListener());

	        // Radio Listener
	        RadioGroup choiceGroup = (RadioGroup) rootView.findViewById(R.id.choices);
	        for (int i = 0; i < choiceGroup.getChildCount(); i++) {
	            ((RadioButton) choiceGroup.getChildAt(i)).setOnClickListener(new QuizRadioButtonOnClickListener());
	        }
        }
        
        return rootView;
    }
    
    private void setChoices(View rootView){
        RadioGroup choiceGroup = (RadioGroup) rootView.findViewById(R.id.choices);
        String[] choices = question.getChoices().split("\\|");
        for (int i = 0; i < choiceGroup.getChildCount(); i++) {
        	Log.v(this.getClass().getName(), "Choice" + Integer.toString(i) + ": " + choices[i]);
            ((RadioButton) choiceGroup.getChildAt(i)).setText(choices[i]);
        }
    }
    
    private void setQuestion(View rootView){
    	TextView Questionview = ((TextView) rootView.findViewById(R.id.question_title));
    	Questionview.setText(question.getQuestion());
    	Questionview.postInvalidate();
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
	    		Log.v(this.getClass().getName(), "Correct!");
	    		CorrectDialogFragment correctDialog = new CorrectDialogFragment();
	    		correctDialog.setDescription(question.getDescription());
	    		correctDialog.show(getActivity().getSupportFragmentManager(), "CorrectDialogFragment");
	    	}
	    	else{
	    		Log.v(this.getClass().getName(), "Incorrect!");
	    		IncorrectDialogFragment incorrectDialog = new IncorrectDialogFragment();
	    		incorrectDialog.show(getActivity().getSupportFragmentManager(), "IncorrectDialogFragment");	    		
	    	}
		}
		
		private boolean isCorrectChoice(){
			if (choice == Integer.parseInt(question.getAnswer())){
				return true;
			}
			return false;
		}
    }
 
}