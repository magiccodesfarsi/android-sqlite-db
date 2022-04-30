package com.shahapp.sqlitedatabase;

public class NoteDto {

    private String Title;

    public NoteDto() {
    }

    public NoteDto(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
