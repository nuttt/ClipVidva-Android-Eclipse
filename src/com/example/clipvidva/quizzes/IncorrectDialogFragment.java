package com.example.clipvidva.quizzes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.example.clipvidva.R;

public class IncorrectDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.incorrect_title)
        	   .setMessage(R.string.incorrect_desc)
               .setNegativeButton(R.string.try_again, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Log.v(this.getClass().getName(), "Click No!");
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}