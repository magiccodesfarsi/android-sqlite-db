package com.shahapp.sqlitedatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DbHelper db;
    private FloatingActionButton fabOpenAddNoteActivity;
    private RecyclerView recyclerView;
    private NoteRecyclerAdapter noteRecyclerAdapter;
    private ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DbHelper(this);
        db.openDatabase();

        fabOpenAddNoteActivity = findViewById(R.id.fabOpenAddNoteActivity);
        fabOpenAddNoteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });

        notes = db.getNotes(null);

        recyclerView = findViewById(R.id.recyclerView);
        noteRecyclerAdapter = new NoteRecyclerAdapter(this, notes);
        recyclerView.setAdapter(noteRecyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem menuItem = menu.findItem(R.id.miSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchNotes(newText);

                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void searchNotes(String title){
        NoteDto noteDto = new NoteDto(title);
        notes = db.getNotes(noteDto);

        noteRecyclerAdapter = new NoteRecyclerAdapter(MainActivity.this, notes);
        recyclerView.setAdapter(noteRecyclerAdapter);
    }
}













