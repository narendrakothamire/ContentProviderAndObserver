package com.softwares.swamisamarth.contentproviderandobserver.database;

import android.content.ContentResolver;
import android.database.Cursor;

import com.softwares.swamisamarth.contentproviderandobserver.models.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Narendra on 8/28/2016.
 */
public class StudentDAOImp implements StudentDAO {

    private ContentResolver contentResolver;

    public StudentDAOImp(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        Cursor cursor = contentResolver.query(StudentsContract.STUDENTS_URI, StudentTable.PROJECTION_ALL, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Student student = StudentTable.parseCursor(cursor);
            students.add(student);
            cursor.moveToNext();
        }
        return students;
    }

    @Override
    public Student getStudent(int rollNo) {
        return null;
    }

    @Override
    public void addStudent(Student student) {
        contentResolver.insert(StudentsContract.STUDENTS_URI, StudentTable.toContentValues(student));
    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudent(Student student) {

    }
}
