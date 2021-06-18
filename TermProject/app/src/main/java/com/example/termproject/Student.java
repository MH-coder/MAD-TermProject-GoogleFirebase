package com.example.termproject;

public class Student {
    private String rollNo;
    private String Name;
    private String fatherName;
    private String email;
    private String phone;

    public Student(String rollNo, String name, String fatherName, String email, String phone) {
        this.rollNo = rollNo;
        this.Name = name;
        this.fatherName = fatherName;
        this.email = email;
        this.phone = phone;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return rollNo + "," + Name + "," + fatherName + ","  +
                email + "," +phone ;
    }
}
