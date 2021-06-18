package com.example.termproject;

import androidx.annotation.CallSuper;

import java.sql.Time;
import java.util.Date;

public class Session {

    private String sessionId;
    private String sessionTitle;
    private String sessionDesc;
    private String sessionDate;
    private String sessionTime;


    public Session(String sid, String st, String sdesc, String sdate, String stime){
        sessionId = sid;
        sessionTitle = st;
        sessionDesc = sdesc;
        sessionDate = sdate;
        sessionTime = stime;
    }

    public Session(String str) {
        String[] Data = str.split(",");

        sessionId = Data[0];
        sessionTitle = Data[1];
        sessionDesc = Data[2];
        sessionDate = Data[3];
        sessionTime = Data[4];
    }

    public String getSessionId() { return sessionId; }

    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public String getSessionTitle() { return sessionTitle; }

    public void setSessionTitle(String sessionTitle) { this.sessionTitle = sessionTitle; }

    public String getSessionDesc() { return sessionDesc;}

    public void setSessionDesc(String sessionDesc) { this.sessionDesc = sessionDesc; }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(String sessionTime) {
        this.sessionTime = sessionTime;
    }

    @Override
    public String toString() {
        return sessionId + "," + sessionTitle + "," + sessionDesc + ","  +
                sessionDate + "," +sessionTime ;
    }
}
