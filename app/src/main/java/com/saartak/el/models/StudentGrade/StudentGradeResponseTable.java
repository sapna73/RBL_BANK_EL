package com.saartak.el.models.StudentGrade;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentGradeResponseTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("Id")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("Board")
    @Expose
    private String board;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
}
