package com.example.clipvidva.quizzes;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.clipvidva.ClipVidvaDatabaseHelper;

public class UserAnswersDataSource {
    private SQLiteDatabase database;
    private ClipVidvaDatabaseHelper dbHelper;
    private String[] allColumns = { ClipVidvaDatabaseHelper.USER_ANSWERS_COL_SUBJECT_ID,
            ClipVidvaDatabaseHelper.USER_ANSWERS_COL_QUESTION_ID,
            ClipVidvaDatabaseHelper.USER_ANSWERS_COL_ANSWER,
            ClipVidvaDatabaseHelper.USER_ANSWERS_COL_RESULT};

    public UserAnswersDataSource(Context context) {
        dbHelper = new ClipVidvaDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
    	dbHelper.close();
    }

    public boolean editAnswer(int subject_id, int question_id, String answer, String result) {
		ContentValues values = new ContentValues();
        values.put(ClipVidvaDatabaseHelper.USER_ANSWERS_COL_SUBJECT_ID, subject_id);
        values.put(ClipVidvaDatabaseHelper.USER_ANSWERS_COL_QUESTION_ID, question_id);
        values.put(ClipVidvaDatabaseHelper.USER_ANSWERS_COL_ANSWER, answer);
        values.put(ClipVidvaDatabaseHelper.USER_ANSWERS_COL_RESULT, result);
        String where_clause = ClipVidvaDatabaseHelper.USER_ANSWERS_COL_SUBJECT_ID + " = " + subject_id 
        					  + " and " 
        					  + ClipVidvaDatabaseHelper.USER_ANSWERS_COL_QUESTION_ID + " = " + question_id;
        int row_effected = database.update(ClipVidvaDatabaseHelper.TABLE_USER_ANSWERS, values, where_clause, null);
        return row_effected > 0;
    }
    
    public UserAnswer getUserAnswer(String subject_id, String question_id) {
        String where_clause = ClipVidvaDatabaseHelper.USER_ANSWERS_COL_SUBJECT_ID + " = " + subject_id 
				  + " and " 
				  + ClipVidvaDatabaseHelper.USER_ANSWERS_COL_QUESTION_ID + " = " + question_id;
        Cursor cursor = database.query(ClipVidvaDatabaseHelper.TABLE_USER_ANSWERS,
                		allColumns, where_clause, null, null, null, null);

        cursor.moveToFirst();
        UserAnswer userAnswer = cursorToUserAnswer(cursor);
        cursor.close();
        return userAnswer;
    }

    public List<UserAnswer> getAllAnswersIn(String subject_id) {
        return getAllAnswersIn(Integer.parseInt(subject_id));
    }

    public List<UserAnswer> getAllAnswersIn(int subject_id) {
        List<UserAnswer> userAnswers = new ArrayList<UserAnswer>();
        String where_clause = ClipVidvaDatabaseHelper.USER_ANSWERS_COL_SUBJECT_ID + " = " + Integer.toString(subject_id);
        Cursor cursor = database.query(ClipVidvaDatabaseHelper.TABLE_USER_ANSWERS,
                allColumns, where_clause, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            UserAnswer userAnswer = cursorToUserAnswer(cursor);
            userAnswers.add(userAnswer);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return userAnswers;
    }

    public UserAnswer getUserAnswer(int subject_id, int question_id) {
    	return getUserAnswer(Integer.toString(subject_id), Integer.toString(question_id));
    }

    private UserAnswer cursorToUserAnswer(Cursor cursor) {
    	UserAnswer userAnswer = new UserAnswer();
    	userAnswer.setSubject_id(cursor.getInt(0));
    	userAnswer.setQuestion_id(cursor.getInt(1));
    	userAnswer.setAnswer(cursor.getString(2));
    	userAnswer.setResult(cursor.getString(3));
    	return userAnswer;
    }
}
