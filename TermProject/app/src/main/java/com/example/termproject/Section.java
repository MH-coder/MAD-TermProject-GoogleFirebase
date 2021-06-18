package com.example.termproject;

public class Section {
    private String sectionID;
    private String section;

    public Section(String sectionID, String section) {
        this.sectionID = sectionID;
        this.section = section;
    }

    public String getSectionID() {
        return sectionID;
    }

    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return sectionID + "," + section;
    }
}
