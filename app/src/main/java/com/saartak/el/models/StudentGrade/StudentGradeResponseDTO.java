package com.saartak.el.models.StudentGrade;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.models.ScreenEditValidation.ScreenEditValidationResponseTable;

import java.util.ArrayList;

public class StudentGradeResponseDTO {

    @SerializedName("Table")
    @Expose
    ArrayList<StudentGradeResponseTable> studentGradeResponseTable;

    public ArrayList<StudentGradeResponseTable> getStudentGradeResponseTable() {
        return studentGradeResponseTable;
    }

    public void setStudentGradeResponseTable(ArrayList<StudentGradeResponseTable> studentGradeResponseTable) {
        this.studentGradeResponseTable = studentGradeResponseTable;
    }
}
