package com.shahapp.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private Context context;
    private ArrayList<Note> notes;

    public NoteRecyclerAdapter(Context context, ArrayList<Note> notes){
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note, parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        Note note = notes.get(position);

        holder.tvTitle.setText(note.getTitle());
        holder.tvDescription.setText(note.getDescription());
        holder.switchIsDone.setChecked(note.getIsDone() == 1);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pass id to addNoteActivity for update operation:
                Intent intent = new Intent(context, AddNoteActivity.class);
                intent.putExtra("IdExtra", note.getId());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}







