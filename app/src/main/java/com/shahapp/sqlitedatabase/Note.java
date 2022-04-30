package com.shahapp.sqlitedatabase;

public class Note {

    private int Id;
    private String Title;
    private String Description;
    private int IsDone;

    public Note() {
    }

    public Note(int id, String title, String description, int isDone) {
        Id = id;
        Title = title;
        Description = description;
        IsDone = isDone;
    }

    public Note(String title, String description, int isDone) {
        Title = title;
        Description = description;
        IsDone = isDone;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getIsDone() {
        return IsDone;
    }

    public void setIsDone(int isDone) {
        IsDone = isDone;
    }
}
