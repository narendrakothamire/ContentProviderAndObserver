package com.softwares.swamisamarth.contentproviderandobserver.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.softwares.swamisamarth.contentproviderandobserver.R;
import com.softwares.swamisamarth.contentproviderandobserver.models.Student;

import java.util.ArrayList;

/**
 * Created by Narendra on 8/28/2016.
 */
public class StudentsRecyclerAdapter extends RecyclerView.Adapter<StudentsRecyclerAdapter.StudentViewHolder> {

    private ArrayList<Student> students;
    private Context context;
    private LayoutInflater inflater;

    public StudentsRecyclerAdapter(Context context, ArrayList<Student> students) {
        this.students = students;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_students_list, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student student = students.get(position);
        holder.studentNoTV.setText(student.getId()+"");
        holder.studentNameTV.setText(student.getName());
        holder.studentDOBTV.setText(student.getDob());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView studentNoTV, studentNameTV, studentGenderTV, studentDOBTV;

        public StudentViewHolder(View itemView) {
            super(itemView);

            studentNoTV = (TextView) itemView.findViewById(R.id.studentRollNo);
            studentNameTV = (TextView) itemView.findViewById(R.id.studentNameTV);
            studentGenderTV = (TextView) itemView.findViewById(R.id.studentGender);
            studentDOBTV = (TextView) itemView.findViewById(R.id.studentDOB);
        }
    }
}
