package com.example.clipvidva.quizzes;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.clipvidva.ClipVidvaDatabaseHelper;

/**
 * Created by nuttt on 12/9/13.
 */

public class QuestionsDataSource {
    private SQLiteDatabase database;
    private ClipVidvaDatabaseHelper dbHelper;
    private String[] allColumns = { ClipVidvaDatabaseHelper.QUIZ_COL_ID,
            ClipVidvaDatabaseHelper.QUIZ_COL_SUBJECT,
            ClipVidvaDatabaseHelper.QUIZ_COL_QUESTION,
            ClipVidvaDatabaseHelper.QUIZ_COL_TYPE,
            ClipVidvaDatabaseHelper.QUIZ_COL_CHOICES,
            ClipVidvaDatabaseHelper.QUIZ_COL_ANSWER,
            ClipVidvaDatabaseHelper.QUIZ_COL_HINT,
            ClipVidvaDatabaseHelper.QUIZ_COL_DESCRIPTION};

    public QuestionsDataSource(Context context) {
        dbHelper = new ClipVidvaDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<Question> getAllQuizzesIn(String subject_id) {
        return getAllQuizzesIn(Integer.parseInt(subject_id));
    }

    public List<Question> getAllQuizzesIn(int subject_id) {
        List<Question> quizzes = new ArrayList<Question>();
        String where_clause = ClipVidvaDatabaseHelper.QUIZ_COL_SUBJECT + " = " + Integer.toString(subject_id);
        Cursor cursor = database.query(ClipVidvaDatabaseHelper.TABLE_QUIZZES,
                allColumns, where_clause, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Question quiz = cursorToQuiz(cursor);
            quizzes.add(quiz);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return quizzes;
    }
    public Question getOneQuestion(String subject_id, String question_id){
        String where_clause = ClipVidvaDatabaseHelper.QUIZ_COL_SUBJECT + " = " + subject_id + 
        					  " and " + ClipVidvaDatabaseHelper.QUIZ_COL_ID + " = " + question_id;
        Cursor cursor = database.query(ClipVidvaDatabaseHelper.TABLE_QUIZZES,
                allColumns, where_clause, null, null, null, null);

        cursor.moveToFirst();
        Question question = cursorToQuiz(cursor);
        // Make sure to close the cursor
        cursor.close();
        return question;
    }
    
    public int getNumberOfQuestions(int subject_id){
    	String where_clause = ClipVidvaDatabaseHelper.QUIZ_COL_SUBJECT + " = " + subject_id;
        Cursor rowCount = database.rawQuery("select count(*) from "+ClipVidvaDatabaseHelper.TABLE_QUIZZES+
        		                            " where "+where_clause+";", null);
        rowCount.moveToFirst();
        int count = rowCount.getInt(0);
        rowCount.close();
        return count;
    }
    
    public Question getOneQuestion(int subject_id, int question_id){
    	return getOneQuestion(Integer.toString(question_id), Integer.toString(subject_id));
    }
    
    private Question cursorToQuiz(Cursor cursor){
        Question quiz = new Question();
        quiz.setId(cursor.getInt(0));
        quiz.setSubject_id(cursor.getInt(1));
        quiz.setQuestion(cursor.getString(2));
        quiz.setType(cursor.getString(3));
        quiz.setChoices(cursor.getString(4));
        quiz.setAnswer(cursor.getString(5));
        quiz.setHint(cursor.getString(6));
        quiz.setDescription(cursor.getString(7));
        return quiz;
    }
}