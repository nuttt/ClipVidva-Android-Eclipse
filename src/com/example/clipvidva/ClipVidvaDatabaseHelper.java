package com.example.clipvidva;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by Vee on 7/9/2556.
 */
public class ClipVidvaDatabaseHelper extends SQLiteOpenHelper {

    private String[] tables;
    private String[] creates;
    public static final String TABLE_CATEGORIES = "categories";
    public static final String TABLE_SUBJECTS = "subjects";
    public static final String TABLE_VIDEOS = "videos";
    public static final String TABLE_QUIZZES = "quizzes";
    public static final String TABLE_USER_ANSWERS = "user_answers";

    public static final String CATEGORY_COL_ID = "_id";
    public static final String CATEGORY_COL_NAME = "name";
    public static final String CATEGORY_COL_IMG = "img";
    public static final String SUBJECT_COL_ID = "_id";
    public static final String SUBJECT_COL_NAME = "_name";
    public static final String SUBJECT_COL_CATEGORY = "category_id";
    public static final String VIDEO_COL_ID = "_id";
    public static final String VIDEO_COL_NAME = "name";
    public static final String VIDEO_COL_FILE = "file";
    public static final String VIDEO_COL_SUBJECT = "subject_id";
    public static final String QUIZ_COL_SUBJECT = "subject_id";
    public static final String QUIZ_COL_ID = "_id";
    public static final String QUIZ_COL_QUESTION = "question";
    public static final String QUIZ_COL_TYPE = "type";
    public static final String QUIZ_COL_CHOICES = "choices";
    public static final String QUIZ_COL_HINT = "hint";
    public static final String QUIZ_COL_ANSWER = "answer";
    public static final String QUIZ_COL_DESCRIPTION = "description";
    public static final String USER_ANSWERS_COL_SUBJECT_ID = "subject_id";
    public static final String USER_ANSWERS_COL_QUESTION_ID = "question_id";
    public static final String USER_ANSWERS_COL_ANSWER = "answer";
    public static final String USER_ANSWERS_COL_RESULT = "result";

    private static final String DATABASE_NAME = "clipvidva.db";
    private static final int DATABASE_VERSION = 21;

    private Context context;

    // Database creation sql statement
    private static final String CATEGORIES_CREATE = "create table "
            + TABLE_CATEGORIES + "("
            + CATEGORY_COL_ID + " integer primary key autoincrement, "
            + CATEGORY_COL_NAME + " text not null, "
            + CATEGORY_COL_IMG + " text);";
    private static final String SUBJECTS_CREATE = "create table "
            + TABLE_SUBJECTS + "("
            + SUBJECT_COL_ID + " integer primary key autoincrement, "
            + SUBJECT_COL_NAME + " text not null, "
            + SUBJECT_COL_CATEGORY + " integer not null);";
    private static final String VIDEOS_CREATE = "create table "
            + TABLE_VIDEOS + "("
            + VIDEO_COL_ID + " integer primary key autoincrement, "
            + VIDEO_COL_NAME + " text not null, "
            + VIDEO_COL_FILE + " text not null, "
            + VIDEO_COL_SUBJECT + " integer not null);";
    private static final String QUIZZES_CREATE = "create table "
            + TABLE_QUIZZES + "("
            + QUIZ_COL_SUBJECT + " integer not null, "
            + QUIZ_COL_ID + " integer not null, "
            + QUIZ_COL_QUESTION + " text not null, "
            + QUIZ_COL_TYPE + " text not null, "
            + QUIZ_COL_CHOICES + " text, "
            + QUIZ_COL_ANSWER + " text not null, "
            + QUIZ_COL_HINT + " text, "
            + QUIZ_COL_DESCRIPTION + " text, "
            + "primary key (" + QUIZ_COL_SUBJECT + ", " + QUIZ_COL_ID + "));";
    private static final String USER_ANSWERS_CREATE = "create table "
            + TABLE_USER_ANSWERS + "("
            + USER_ANSWERS_COL_SUBJECT_ID + " integer not null, "
            + USER_ANSWERS_COL_QUESTION_ID + " integer not null, "
            + USER_ANSWERS_COL_ANSWER + " text not null, "
            + USER_ANSWERS_COL_RESULT + " text not null, "
            + "primary key (" + USER_ANSWERS_COL_SUBJECT_ID + ", " + USER_ANSWERS_COL_QUESTION_ID + "));";

    public ClipVidvaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        String[] all_tables = {TABLE_CATEGORIES, TABLE_SUBJECTS, TABLE_VIDEOS, TABLE_QUIZZES, TABLE_USER_ANSWERS};
        String[] all_creates = {CATEGORIES_CREATE, SUBJECTS_CREATE, VIDEOS_CREATE, QUIZZES_CREATE, USER_ANSWERS_CREATE};
        this.tables = all_tables;
        this.creates = all_creates;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        for(String create_query: this.creates){
            database.execSQL(create_query);
        }
        // TODO: Change from hard-code to read from file
        database.execSQL("INSERT INTO categories VALUES(1,'" + context.getResources().getString(R.string.math_category) + "','hsmaths');");
        database.execSQL("INSERT INTO categories VALUES(2,'" + context.getResources().getString(R.string.askvidva_category) + "','askeng');");
        // Subjects for Maths category
        database.execSQL("INSERT INTO subjects VALUES(1,'" + context.getResources().getString(R.string.subject_real_number) + "', 1);");
        database.execSQL("INSERT INTO subjects VALUES(2,'" + context.getResources().getString(R.string.subject_conic_section) + "', 1);");
        database.execSQL("INSERT INTO subjects VALUES(3,'" + context.getResources().getString(R.string.subject_functions) + "', 1);");
        // Subjects for Askeng category
        database.execSQL("INSERT INTO subjects VALUES(4,'" + context.getResources().getString(R.string.subject_askeng_cp) + "', 2);");
        
        // Videos for Real Number
        database.execSQL("INSERT INTO videos VALUES(1,'Video 1','real123', 1);");
        database.execSQL("INSERT INTO videos VALUES(2,'Video 2','real223', 1);");
        database.execSQL("INSERT INTO videos VALUES(3,'Video 3','real323', 1);");
        // Videos for Conic Section
        database.execSQL("INSERT INTO videos VALUES(4,'Conic Video 1','conic123', 2);");
        database.execSQL("INSERT INTO videos VALUES(5,'Conic Video 2','conic223', 2);");
        // Videos for Askeng-CP
        database.execSQL("INSERT INTO videos VALUES(6,'" + context.getResources().getString(R.string.subject_askeng_cp) + "','askeng_cp', 4);");
        
        
        // Question
        database.execSQL("INSERT INTO quizzes VALUES(1, 1, 'Real Question', 'choices', '15a + b|a*b*c + 2|3d + 4|a', '1', 'this is the hint', 'description goes here');");
        database.execSQL("INSERT INTO quizzes VALUES(1, 2, 'Real Question q2', 'choices', 'c1|cc2|ccc3|cccc4', '1', 'this is the hint', 'description goes here again');");
        database.execSQL("INSERT INTO quizzes VALUES(1, 3, 'Real Question', 'text', '', '1', '', 'description goes here');");
        database.execSQL("INSERT INTO quizzes VALUES(2, 1, 'Conic 1', 'choices', 'c1|c2|c3|c4', '2', 'hint hint hint!', 'description goes here 22');");
        database.execSQL("INSERT INTO quizzes VALUES(3, 1, 'Function No 1', 'choices', 'ffff1|f 2|f f  3|ff 4', '4', '', 'description goes here for function');");
        // User Answers
        database.execSQL("INSERT INTO user_answers VALUES(1, 1, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(1, 2, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(1, 3, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(2, 1, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(3, 1, '', '');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ClipVidvaDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        for(String table_name: this.tables){
            db.execSQL("DROP TABLE IF EXISTS " + table_name);
        }
        onCreate(db);
    }

}