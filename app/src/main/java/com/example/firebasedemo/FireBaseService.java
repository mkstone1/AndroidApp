package com.example.firebasedemo;

import android.net.Uri;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.firebasedemo.adapter.NotesAdapter;
import com.example.firebasedemo.model.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireBaseService {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();


    private String notesCollection = "notes";
    List<Note> notes = new ArrayList<>();

    private NotesAdapter testAdapter;


    public void addNote(String title, String detailedInfo){
        DocumentReference ref = db.collection(notesCollection).document();
        Map<String, String> map = new HashMap<>();
        map.put("title", title);
        map.put("detailedInfo", detailedInfo);
        ref.set(map).addOnSuccessListener(unused -> System.out.println("doucment saved with title: " + title))
                .addOnFailureListener(e -> System.out.println("Document saving failed"));
    }

    public void getAllDocs(NotesAdapter adapter){
        db.collection(notesCollection).addSnapshotListener((snap, error) -> {
            if(error == null){
                notes.clear();
                for(DocumentSnapshot document :snap.getDocuments()){
                    Note note = new Note(document.getId(), document.getData().get("title").toString(), document.getData().get("detailedInfo").toString());
                    notes.add(note);
                }
                adapter.notifyDataSetChanged(); // will update the gui
            }

        });

    }

    public void setAdapter(NotesAdapter adapter){
        testAdapter = adapter;
    }

    public void uploadImage(Uri fileUri ){
        StorageReference storageRef = storage.getReference().child("images/myimage.jpg");
        UploadTask uploadTask = storageRef.putFile(fileUri);

        uploadTask.addOnProgressListener(taskSnapshot -> {
            // Track the upload progress
            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
            String TAG = "";
            Log.d(TAG, "Upload is " + progress + "% done");
        });

        // Add a listener to handle success and failure cases
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // Handle the upload success
            String TAG = "";
            Log.d(TAG, "Upload successful");

            // Get the download URL of the uploaded file
            storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                // Handle the download URL success
                Log.d(TAG, "Download URL: " + uri.toString());

                // Save the download URL to a database or display it in the UI
                // ...
            });
        }).addOnFailureListener(e -> {
            // Handle the upload failure
            String TAG = "";
            Log.e(TAG, "Upload failed", e);
        });






    }
}
