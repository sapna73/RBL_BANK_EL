package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.saartak.el.database.entity.CenterMeetingAttendanceTable;

import java.util.List;

public class CenterMeetingAttendanceDTO {
    @Expose
    String groupName;
    @Expose
    List<CenterMeetingAttendanceTable> centerMeetingAttendanceTableList;

    @SerializedName("file_name")
    @Expose
    private String file_name;
    @SerializedName("file_path")
    @Expose
    private String file_path;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<CenterMeetingAttendanceTable> getCenterMeetingAttendanceTableList() {
        return centerMeetingAttendanceTableList;
    }

    public void setCenterMeetingAttendanceTableList(List<CenterMeetingAttendanceTable> centerMeetingAttendanceTableList) {
        this.centerMeetingAttendanceTableList = centerMeetingAttendanceTableList;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
