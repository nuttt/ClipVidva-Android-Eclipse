package com.example.clipvidva;

import com.example.clipvidva.quizzes.QuizItemDetailActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DownloadDialogFragment extends DialogFragment {
	private String videoFilename;
	
	public void setVideoFilename(String filename){
		this.videoFilename = filename;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Download File")
        	   .setMessage("Do you want to download?")
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   
                   }
               })
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Intent intent = new Intent(getActivity().getApplicationContext() , VideoViewer.class);
           				intent.putExtra("VIDEO_FILENAME", videoFilename);
           				startActivity(intent);
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
