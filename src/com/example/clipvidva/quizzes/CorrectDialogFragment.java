package com.example.clipvidva.quizzes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.example.clipvidva.R;

public class CorrectDialogFragment extends DialogFragment {
	private String description;
	private boolean next = true;
	
	
	public CorrectDialogFragment() {
		super();
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isNext() {
		return next;
	}


	public void setNext(boolean next) {
		this.next = next;
	}


	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.correct_title)
        	   .setMessage(description)
               .setPositiveButton(R.string.next, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   if(next){
                		   ((QuizItemDetailActivity)getActivity()).getNextQuestion();
                	   }
                	   else{
                		   ((QuizItemDetailActivity)getActivity()).finish();
                	   }
                   }
               })
               .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Log.v(this.getClass().getName(), "Click Close!");
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}