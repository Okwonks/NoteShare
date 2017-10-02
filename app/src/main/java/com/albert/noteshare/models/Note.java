package com.albert.noteshare.models;

import org.parceler.Parcel;

@Parcel
public class Note {
    /*The private string note is used to call data from Firebase*/
    /*It should not be confused with the public class Note or the Note constructor*/
    private String note;
    private String title;
    private String pushId;

    public Note(String note, String title) {
        this.note = note;
        this.title = title;
    }

    public Note() {}

    public String getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
