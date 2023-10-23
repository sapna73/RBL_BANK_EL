package com.saartak.el.models.TypeOfProfession;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class GetLeadDropDownTypeOfProfessionTable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("ID")
    @Expose(serialize = false, deserialize = false)
    private int id;

    @SerializedName("TypeOfProfession")
    @Expose
    private String typeOfProfession;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfProfession() {
        return typeOfProfession;
    }

    public void setTypeOfProfession(String typeOfProfession) {

        this.typeOfProfession = typeOfProfession;

    }
}
