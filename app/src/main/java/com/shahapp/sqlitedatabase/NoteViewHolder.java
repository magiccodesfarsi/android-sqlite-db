package com.shahapp.sqlitedatabase;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import org.w3c.dom.Text;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle, tvDescription;
    public SwitchMaterial switchIsDone;

    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        switchIsDone = itemView.findViewById(R.id.switchIsDone);

    }
}
