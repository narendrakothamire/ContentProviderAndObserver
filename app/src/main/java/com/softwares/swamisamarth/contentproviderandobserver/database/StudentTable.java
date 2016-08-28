package com.softwares.swamisamarth.contentproviderandobserver.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.softwares.swamisamarth.contentproviderandobserver.models.Student;

/**
 * Created by Narendra on 8/27/2016.
 */
public final class StudentTable {

    public static final String TABLE_NAME = "student";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_STD = "std";
    public static final String COLUMN_DIVISION = "division";


    public static final String CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT NOT NULL," +
                    COLUMN_GENDER + " INTEGER NOT NULL," +
                    COLUMN_DOB + " DATE NOT NULL," +
                    COLUMN_STD + " INTEGER NOT NULL," +
                    COLUMN_DIVISION+ " TEXT NOT NULL" +
                    " );";

    public static final String[] PROJECTION_ALL =
            {COLUMN_ID, COLUMN_NAME, COLUMN_GENDER, COLUMN_DOB, COLUMN_STD, COLUMN_DIVISION};


    public static final String SORT_ORDER_DEFAULT =
            COLUMN_NAME + " ASC";

    public static ContentValues toContentValues(Student student) {
        ContentValues values = new ContentValues();
    //    values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_GENDER, student.isMale());
        values.put(COLUMN_DOB, student.getDob());
        values.put(COLUMN_STD, student.getStd());
        values.put(COLUMN_DIVISION, student.getDivision());
        return values;
    }

    public static Student parseCursor(Cursor cursor) {
        Student student = new Student();
        student.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
        student.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
        int val = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GENDER));
        student.setMale((val == 0) ? false : true);
        student.setDob(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DOB)));
        student.setStd(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_STD)));
        student.setDivision(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIVISION)));
        return student;
    }
}
