package com.saartak.el.models;

import com.google.gson.annotations.Expose;
import com.saartak.el.database.entity.CenterMeetingCollectionTable;

import java.util.List;

public class CenterMeetingCollectionDTO {
    @Expose
    String groupName;
    @Expose
    List<CenterMeetingCollectionTable> centerMeetingCollectionTableList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<CenterMeetingCollectionTable> getCenterMeetingCollectionTableList() {
        return centerMeetingCollectionTableList;
    }

    public void setCenterMeetingCollectionTableList(List<CenterMeetingCollectionTable> centerMeetingCollectionTableList) {
        this.centerMeetingCollectionTableList = centerMeetingCollectionTableList;
    }
}
