package com.example.firebasedemo.model;

public class Note {

    private String id;
    private String title;
    private String detailedInfo;


    public Note(String id, String title, String detailedInfo) {
        this.id = id;
        this.title = title;
        this.detailedInfo = detailedInfo;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetailedInfo() {
        return detailedInfo;
    }

    public void setDetailedInfo(String detailedInfo) {
        this.detailedInfo = detailedInfo;
    }
}
