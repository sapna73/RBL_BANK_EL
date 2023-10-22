package com.saartak.el.models;

import java.io.Serializable;

public class ReferenceCheckContactDTO implements Serializable {

    private String id;
    private String name;
    private String contactNo;

    public ReferenceCheckContactDTO(String id, String name, String contactNo) {
        this.id = id;
        this.name = name;
        this.contactNo = contactNo;
    }

    public ReferenceCheckContactDTO(String name, String contactNo) {
        this.name = name;
        this.contactNo = contactNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
