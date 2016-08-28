package com.softwares.swamisamarth.contentproviderandobserver;

import android.database.ContentObserver;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.softwares.swamisamarth.contentproviderandobserver.adapters.StudentsRecyclerAdapter;
import com.softwares.swamisamarth.contentproviderandobserver.database.StudentContentObserver;
import com.softwares.swamisamarth.contentproviderandobserver.database.StudentDAOImp;
import com.softwares.swamisamarth.contentproviderandobserver.models.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private StudentDAOImp studentsDAOImp;
    private StudentsRecyclerAdapter adapter;
    private ArrayList<Student> students = new ArrayList<>();
    private StudentContentObserver studentContentObserver;

    private StudentContentObserver.StudentContentObserverCallback callBack = new StudentContentObserver.StudentContentObserverCallback() {
        @Override
        public void update() {
            students.clear();
            students.addAll(studentsDAOImp.getAllStudents());
            adapter.notifyDataSetChanged();
        }
    };
    private EditText nameEditText, dobEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        nameEditText = (EditText)findViewById(R.id.studentNameET);
        dobEditText = (EditText)findViewById(R.id.studentDOBET);
        studentsDAOImp = new StudentDAOImp(getContentResolver());
        studentContentObserver = new StudentContentObserver(new Handler(), callBack);
        adapter = new StudentsRecyclerAdapter(this, students);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentContentObserver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        studentContentObserver.unRegister(this);
    }

    public void addStudent(View view) {
        Student student = new Student();
        student.setDivision("A");
        student.setName(nameEditText.getText().toString());
        student.setDob(dobEditText.getText().toString());
        studentsDAOImp.addStudent(student);
    }
}
