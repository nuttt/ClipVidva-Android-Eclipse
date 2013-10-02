package com.example.clipvidva.quizzes;

import com.example.clipvidva.Subject;

public class SubjectWithProgress extends Subject {
	private int progress;
	private int maxProgress;
	
	public static SubjectWithProgress parseSubjectWithProgress(Subject subject, int progress, int maxProgress){
		SubjectWithProgress subjectWithProgress = new SubjectWithProgress();
		subjectWithProgress.id = subject.getId();
		subjectWithProgress.name = subject.getName();
        subjectWithProgress.category_id = subject.getCategory_id();
		subjectWithProgress.progress = progress;
		subjectWithProgress.maxProgress = maxProgress;
		return subjectWithProgress;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getMaxProgress() {
		return maxProgress;
	}

	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}
	
}
