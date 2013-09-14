package com.example.clipvidva.quizzes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.clipvidva.Category;
import com.example.clipvidva.ClipVidvaDatabaseHelper;
import com.example.clipvidva.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuttt on 12/9/13.
 */

public class QuizzesDataSource {
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

    public QuizzesDataSource(Context context) {
        dbHelper = new ClipVidvaDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public List<Quiz> getAllQuizzesIn(String subject_id) {
        return getAllQuizzesIn(Integer.parseInt(subject_id));
    }

    public List<Quiz> getAllQuizzesIn(int subject_id) {
        List<Quiz> quizzes = new ArrayList<Quiz>();
        String where_clause = ClipVidvaDatabaseHelper.QUIZ_COL_SUBJECT + " = " + Integer.toString(subject_id);
        Cursor cursor = database.query(ClipVidvaDatabaseHelper.TABLE_QUIZZES,
                allColumns, where_clause, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Quiz quiz = cursorToQuiz(cursor);
            quizzes.add(quiz);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return quizzes;
    }
    private Quiz cursorToQuiz(Cursor cursor){
        Quiz quiz = new Quiz();
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