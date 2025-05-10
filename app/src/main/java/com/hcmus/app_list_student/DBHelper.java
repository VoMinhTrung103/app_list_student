package com.hcmus.app_list_student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "students.db";
    private static final int DB_VERSION = 2; // Tăng phiên bản từ 1 lên 2 để kích hoạt onUpgrade

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Students (id TEXT PRIMARY KEY, name TEXT, mssv TEXT, avatar INTEGER, faculty TEXT, major TEXT, credits INTEGER, gpa REAL, trainingScore INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Students");
        onCreate(db);
    }

    public void insertStudent(Student s) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", s.getId());
        cv.put("name", s.getName());
        cv.put("mssv", s.getId());
        cv.put("avatar", s.getAvatarResId());
        cv.put("faculty", s.faculty);
        cv.put("major", s.major);
        cv.put("credits", s.creditsCompleted);
        cv.put("gpa", s.gpa);
        cv.put("trainingScore", s.trainingScore);
        db.insert("Students", null, cv);
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Students", null);
        while (cursor.moveToNext()) {
            list.add(new Student(
                    cursor.getString(cursor.getColumnIndex("name")), // name
                    cursor.getString(cursor.getColumnIndex("id")),   // mssv (id)
                    cursor.getInt(cursor.getColumnIndex("avatar")),  // avatar
                    cursor.getString(cursor.getColumnIndex("faculty")), // faculty
                    cursor.getString(cursor.getColumnIndex("major")),   // major
                    cursor.getInt(cursor.getColumnIndex("credits")),    // credits
                    cursor.getFloat(cursor.getColumnIndex("gpa")),      // gpa
                    cursor.getInt(cursor.getColumnIndex("trainingScore")) // trainingScore
            ));
        }
        cursor.close();
        return list;
    }

    public boolean isStudentExists(String studentId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Students WHERE id = ?", new String[]{studentId});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}