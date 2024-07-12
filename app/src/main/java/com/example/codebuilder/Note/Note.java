package com.example.codebuilder.Note;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String code;
    private String flow;
    private String date;
    private String time;

    public Note(String title, String description, String code, String flow, String date, String time) {
        this.title = title;
        this.description = description;
        this.code = code;
        this.flow = flow;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String priority) {
        this.code = priority;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(int priorityNumber) {
        this.flow = String.valueOf(priorityNumber);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
