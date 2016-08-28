package com.softwares.swamisamarth.contentproviderandobserver.database;

import com.softwares.swamisamarth.contentproviderandobserver.models.Student;

import java.util.List;

/**
 * Created by Narendra on 8/28/2016.
 */
public interface StudentDAO {

    List<Student> getAllStudents();

    Student getStudent(int rollNo);

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(Student student);
}
