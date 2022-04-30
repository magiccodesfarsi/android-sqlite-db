package com.shahapp.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;

public class DbHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "NoteDb";
    private static final String NOTE_TABLE = "Notes";
    private static final String ID = "Id";
    private static final String TITLE = "Title";
    private static final String DESCRIPTION = "Description";
    private static final String IS_DONE = "IsDone";

    private static final String CREATE_NOTE_TABLE_QUERY = "CREATE TABLE " + NOTE_TABLE + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT, "
            + DESCRIPTION + " TEXT, "
            + IS_DONE + " INTEGER)";

    private static final String DROP_NOTE_TABLE_QUERY = "DROP TABLE IF EXISTS " + NOTE_TABLE;



    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_NOTE_TABLE_QUERY);

        onCreate(db);
    }

    //open database:
    public void openDatabase(){
        db = this.getWritableDatabase();
    }

    //insert a note:
    public int insertNote(Note note){
        ContentValues cv = new ContentValues();
        cv.put(TITLE, note.getTitle());
        cv.put(DESCRIPTION, note.getDescription());
        cv.put(IS_DONE, note.getIsDone());

        int id = (int) db.insert(NOTE_TABLE, null, cv);

        return  id;
    }

    //get list of notes:
    public ArrayList<Note> getNotes(NoteDto noteDto){
        ArrayList<Note> notes = new ArrayList<>();

        Cursor cursor = null;

        try {
            cursor = db.query(NOTE_TABLE, null,
                    (noteDto != null ?
                            TITLE + " LIKE '%" + noteDto.getTitle().toLowerCase() + "%'" :
                            ID + " > " + 0),
                    null, null, null, null, null);

            if (cursor != null){
                if (cursor.moveToFirst()){

                    do {

                        Note note = new Note(
                                cursor.getInt(cursor.getColumnIndexOrThrow(ID)),
                                cursor.getString(cursor.getColumnIndexOrThrow(TITLE)),
                                cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)),
                                cursor.getInt(cursor.getColumnIndexOrThrow(IS_DONE))
                        );

                        notes.add(note);

                    } while (cursor.moveToNext());
                }
            }


        } finally {
            cursor.close();
        }

        return notes;
    }

    //get a single note:
    public Note getNote(int noteId){
        Note note = null;
        Cursor cursor = null;

        try{
            cursor = db.rawQuery("SELECT * FROM " + NOTE_TABLE + " WHERE " + ID + " = " + noteId, null);
            if (cursor != null){
                if (cursor.moveToFirst()){
                    note = new Note(
                            cursor.getInt(cursor.getColumnIndexOrThrow(ID)),
                            cursor.getString(cursor.getColumnIndexOrThrow(TITLE)),
                            cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)),
                            cursor.getInt(cursor.getColumnIndexOrThrow(IS_DONE))
                    );
                }
            }


        } finally {
            cursor.close();
        }

        return note;
    }

    //update a note:
    public void updateNote(int noteId, Note note){
        ContentValues cv = new ContentValues();
        cv.put(TITLE, note.getTitle());
        cv.put(DESCRIPTION, note.getDescription());
        cv.put(IS_DONE, note.getIsDone());

        db.update(NOTE_TABLE, cv, ID + "=?", new String[]{String.valueOf(noteId)});
    }

    //delete a note
    public void deleteNote(int noteId){
        db.delete(NOTE_TABLE, ID + "=?", new String[]{String.valueOf(noteId)});
    }


}


















