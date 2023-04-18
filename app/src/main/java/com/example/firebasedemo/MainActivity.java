package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.firebasedemo.adapter.NotesAdapter;
import com.example.firebasedemo.model.Note;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private ListView listView;

    private ImageView imageView;
    private int PICK_IMAGE_REQUEST = 0;

    private FireBaseService fireBaseService;



    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fireBaseService = new FireBaseService();

      //  fireBaseService.addNote("Test", "This is a test");

        listView = findViewById(R.id.listView);
        listView.setVisibility(listView.GONE);

        imageView = findViewById(R.id.imageView3);
       // adapter = new ArrayAdapter<>(this, R.layout.myrow, R.id.rowTextView);


        notesAdapter = new NotesAdapter(this, fireBaseService.notes);
        fireBaseService.setAdapter(notesAdapter);


        listView.setAdapter(notesAdapter);
        fireBaseService.getAllDocs(notesAdapter);


        listView.setOnItemClickListener((listView, linearLayout, pos, id ) ->{

            Intent intent = new Intent(this, DetailedNote.class);
            intent.putExtra("info", fireBaseService.notes.get(pos).getDetailedInfo());
            startActivity(intent);
        });

        imageView.setOnClickListener(v -> {

            // Do something when the ImageView is clicked
            Intent photoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(photoIntent, PICK_IMAGE_REQUEST);

        });






    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            fireBaseService.uploadImage(uri);
        }
    }
}