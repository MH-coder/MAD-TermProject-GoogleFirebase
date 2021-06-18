package com.example.termproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "AttendanceManagement.db";
    public static final String SESSION_TABLE_NAME = "Session";
    public static final String SESSION_COLUMN_SessionID = "SessionID";
    public static final String SESSION_COLUMN_SessionTitle = "SessionTitle";
    public static final String SESSION_COLUMN_SessionDesc = "SessionDesc";
    public static final String SESSION_COLUMN_SessionDate = "SessionDate";
    public static final String Session_COLUMN_SessionTime = "SessionTime";

    public static final String STUDENT_TABLE_NAME = "Student";
    public static final String STUDENT_COLUMN_ROLLNO = "StudentRollNo";
    public static final String STUDENT_COLUMN_NAME = "StudentName";
    public static final String STUDENT_COLUMN_FATHER_NAME = "StudentFatherName";
    public static final String STUDENT_COLUMN_EMAIL = "StudentEmail";
    public static final String STUDENT_COLUMN_PHONE = "StudentPhone";

    public static final String COURSE_TABLE_NAME = "Course";
    public static final String COURSE_COLUMN_CourseCode = "CourseCode";
    public static final String COURSE_COLUMN_CourseTitle = "CourseTitle";

    public static final String SECTION_TABLE_NAME = "Section";
    public static final String SECTION_COLUMN_SectionID = "SectionID";
    public static final String SECTION_COLUMN_Section = "Section";

    public static final String SIGNUP_TABLE_NAME = "SignUp";
    public static final String SIGNUP_COLUMN_EMAIL = "Email";
    public static final String SIGNUP_COLUMN_PASSWORD = "Password";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table Session" +
                        "(SessionID text primary key, SessionTitle text,SessionDesc text,SessionDate text, SessionTime text)"
        );

        db.execSQL(
                "create table Student" +
                        "(StudentRollNo text primary key, StudentName text,StudentFatherName text,StudentEmail text, StudentPhone text)"
        );

        db.execSQL(
                "create table Course" +
                        "(CourseCode text primary key, CourseTitle text)"
        );

        db.execSQL(
                "create table Section" +
                        "(SectionID text primary key, Section text)"
        );

        db.execSQL(
                "create table SignUp" +
                        "(Email text primary key, Password text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS Session");
        db.execSQL("DROP TABLE IF EXISTS Student");
        db.execSQL("DROP TABLE IF EXISTS Course");
        db.execSQL("DROP TABLE IF EXISTS Section");
        db.execSQL("DROP TABLE IF EXISTS SignUp");
        onCreate(db);
    }

    /* ==========================================================================*/
    /* ------------------------ SESSION -----------------------------------------*/
    /* ==========================================================================*/
    public boolean insertSession (String sId, String sTitle, String sDesc, String sDate, String sTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SESSION_COLUMN_SessionID, sId);
        contentValues.put(SESSION_COLUMN_SessionTitle, sTitle);
        contentValues.put(SESSION_COLUMN_SessionDesc, sDesc);
        contentValues.put(SESSION_COLUMN_SessionDate, sDate);
        contentValues.put(Session_COLUMN_SessionTime, sTime);

        db.insert("Session", null, contentValues);
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String res =  "select * from Session order by SessionID desc";
        Cursor cursor = db.rawQuery(res,null);
        return cursor;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Session where SessionID=id", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SESSION_TABLE_NAME);
        return numRows;
    }

    public boolean updateSession (String id, String sId ,String sTitle, String sDesc, String sDate, String sTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SESSION_COLUMN_SessionID, sId);
        contentValues.put(SESSION_COLUMN_SessionTitle, sTitle);
        contentValues.put(SESSION_COLUMN_SessionDesc, sDesc);
        contentValues.put(SESSION_COLUMN_SessionDate, sDate);
        contentValues.put(Session_COLUMN_SessionTime, sTime);

        db.update("Session", contentValues, "SessionID = ?", new String[] { id } );
        return true;
    }

    public Integer deleteSession (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Session",
                "SessionID = ? ",
                new String[] { id });
    }

    /* ==========================================================================*/
    /* ------------------------ STUDENT -----------------------------------------*/
    /* ==========================================================================*/

    public boolean insertStudent (String stdRollNo, String stdName, String stdFName, String stdEmail, String stdPh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_COLUMN_ROLLNO, stdRollNo);
        contentValues.put(STUDENT_COLUMN_NAME, stdName);
        contentValues.put(STUDENT_COLUMN_FATHER_NAME, stdFName);
        contentValues.put(STUDENT_COLUMN_EMAIL, stdEmail);
        contentValues.put(STUDENT_COLUMN_PHONE, stdPh);

        db.insert("Student", null, contentValues);
        return true;
    }

    public Cursor getAllStdData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String res =  "select * from Student order by StudentRollNo desc";
        Cursor cursor = db.rawQuery(res,null);
        return cursor;
    }

    public boolean updateStudent (String sRollNo, String stdRollNo, String stdName, String stdFName, String stdEmail, String stdPh) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_COLUMN_ROLLNO, stdRollNo);
        contentValues.put(STUDENT_COLUMN_NAME, stdName);
        contentValues.put(STUDENT_COLUMN_FATHER_NAME, stdFName);
        contentValues.put(STUDENT_COLUMN_EMAIL, stdEmail);
        contentValues.put(STUDENT_COLUMN_PHONE, stdPh);

        db.update("Student", contentValues, "StudentRollNo = ?", new String[] { sRollNo } );
        return true;
    }

    public Integer deleteStudent (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Student",
                "StudentRollNo = ? ",
                new String[] { id });
    }

    /* ==========================================================================*/
    /* ------------------------ course ------------------------------------------*/
    /* ==========================================================================*/

    public boolean insertCourse (String courseCode, String courseTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_COLUMN_CourseCode, courseCode);
        contentValues.put(COURSE_COLUMN_CourseTitle, courseTitle);

        db.insert("Course", null, contentValues);
        return true;
    }

    public Cursor getAllCourseData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String res =  "select * from Course order by CourseCode desc";
        Cursor cursor = db.rawQuery(res,null);
        return cursor;
    }

    public boolean updateCourse (String cc, String courseCode, String courseTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_COLUMN_CourseCode, courseCode);
        contentValues.put(COURSE_COLUMN_CourseTitle, courseTitle);

        db.update("Course", contentValues, "CourseCode = ?", new String[] { cc } );
        return true;
    }

    public Integer deleteCourse (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Course",
                "CourseCode = ? ",
                new String[] { id });
    }

    /* ==========================================================================*/
    /* ----------------------- SECTION ------------------------------------------*/
    /* ==========================================================================*/

    public boolean insertSection (String sectionId, String section) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SECTION_COLUMN_SectionID, sectionId);
        contentValues.put(SECTION_COLUMN_Section, section);

        db.insert("Section", null, contentValues);
        return true;
    }

    public Cursor getAllSectionData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String res =  "select * from Section order by SectionID desc";
        Cursor cursor = db.rawQuery(res,null);
        return cursor;
    }

    public boolean updateSection (String sid, String sectionId, String section) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SECTION_COLUMN_SectionID, sectionId);
        contentValues.put(SECTION_COLUMN_Section, section);

        db.update("Section", contentValues, "SectionID = ?", new String[] { sid } );
        return true;
    }

    public Integer deleteSection (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Section",
                "SectionID = ? ",
                new String[] { id });
    }

    /* ==========================================================================*/
    /* ----------------------- SIGNUP  ------------------------------------------*/
    /* ==========================================================================*/

    public boolean insertSignup (String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SIGNUP_COLUMN_EMAIL, email);
        contentValues.put(SIGNUP_COLUMN_PASSWORD, password);

        db.insert("SignUp", null, contentValues);
        return true;
    }

    public Cursor getAllSignUpData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String res =  "select * from SignUp order by email desc";
        Cursor cursor = db.rawQuery(res,null);
        return cursor;
    }

    public boolean updateSignUp (String e, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SIGNUP_COLUMN_EMAIL, email);
        contentValues.put(SIGNUP_COLUMN_PASSWORD, password);

        db.update("SingUp", contentValues, "Email = ?", new String[] { e } );
        return true;
    }

    public Integer deleteSignUp (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("SignUp",
                "Email = ? ",
                new String[] { id });
    }
}
