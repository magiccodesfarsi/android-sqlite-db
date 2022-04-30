package com.shahapp.sqlitedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private SwitchMaterial switchIsDone;
    private Button btnAddNote;
    private DbHelper db;
    private int idExtra = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        switchIsDone = findViewById(R.id.switchIsDone);
        btnAddNote = findViewById(R.id.btnAddNote);

        db = new DbHelper(this);
        db.openDatabase();


        if (getIntent().hasExtra("IdExtra")){
            idExtra = getIntent().getExtras().getInt("IdExtra");

            Note note = db.getNote(idExtra);

            etTitle.setText(note.getTitle());
            etDescription.setText(note.getDescription());
            switchIsDone.setChecked(note.getIsDone() == 1);

            btnAddNote.setText("Update Note");
        }


        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Note note = new Note(
                        etTitle.getText().toString(),
                        etDescription.getText().toString(),
                        switchIsDone.isChecked() ? 1 : 0
                );

                if (idExtra == 0){
                    db.insertNote(note);
                } else {
                    db.updateNote(idExtra, note);
                }


                Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
                intent.putExtra("RefreshItems", true);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add_note, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.miDeleteNote:

                if (idExtra == 0){
                    startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
                    finish();
                } else {
                    db.deleteNote(idExtra);

                    startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
                    finish();
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}











