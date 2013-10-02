package com.example.clipvidva;

import java.io.File;

import android.os.Environment;

public class VideoWithStatus extends Video {
	protected boolean status = false;
	
	public static VideoWithStatus parseVideoWithStatus(Video video){
		VideoWithStatus videoWithStatus = new VideoWithStatus();
		videoWithStatus.id = video.getId();
		videoWithStatus.name = video.getName();
		videoWithStatus.file = video.getFile();
		videoWithStatus.subject_id = video.getSubject_id();
		File direct = new File(Environment.getExternalStorageDirectory()
                + "/clipvidva/" + videoWithStatus.file + ".mp4");
		if (direct.exists()) {
			videoWithStatus.status = true;
		} else {
			videoWithStatus.status = false;
		}
		return videoWithStatus;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
