package com.example.firebasedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.firebasedemo.R;
import com.example.firebasedemo.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends ArrayAdapter<Note> {


    public NotesAdapter(Context context, List<Note> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Note note = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.myrow, parent, false);
        }

        // Lookup view for data population
        TextView textView = convertView.findViewById(R.id.rowTextView);

        // Populate the data into the template view using the data object
        textView.setText(note.getTitle());

        // Return the completed view to render on screen
        return convertView;
    }
}
