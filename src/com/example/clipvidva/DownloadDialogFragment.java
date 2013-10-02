package com.example.clipvidva;

import java.io.File;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

public class DownloadDialogFragment extends DialogFragment {
	private String videoFilename;
	private Context context;
	
	public void setVideoFilename(String filename){
		this.videoFilename = filename;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
		context = getActivity().getApplicationContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Download File")
        	   .setMessage("Do you want to download?")
               .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   
//                	   String name = Environment.getExternalStorageDirectory().getAbsolutePath();

//                	   name += "/clipvidva/" ;
                	   
                	   File direct = new File(Environment.getExternalStorageDirectory()
                               + "/clipvidva");
                	   
//                	   File direct = new File(name);

                       if (!direct.exists()) {
                           direct.mkdirs();
                       }

                       DownloadManager mgr = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

                       Uri downloadUri = Uri.parse("http://www.withlovee.com/clipvidva-vid/"+videoFilename+".mp4");
                       DownloadManager.Request request = new DownloadManager.Request(
                               downloadUri);

                       request.setAllowedNetworkTypes(
                               DownloadManager.Request.NETWORK_WIFI
                                       | DownloadManager.Request.NETWORK_MOBILE)
                               .setAllowedOverRoaming(false).setTitle(videoFilename+".mp4")
                               .setDescription(videoFilename+".mp4")
                               .setDestinationInExternalPublicDir("/clipvidva", videoFilename+".mp4");

                       mgr.enqueue(request);
                	   
                   }
               })
               .setNegativeButton("No", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Intent intent = new Intent(context , VideoViewer.class);
           				intent.putExtra("VIDEO_FILENAME", videoFilename);
           				intent.putExtra("VIDEO_EXIST", "FALSE");
           				startActivity(intent);
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
