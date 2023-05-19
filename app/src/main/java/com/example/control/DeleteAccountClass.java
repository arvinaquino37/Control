package com.example.control;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteAccountClass extends AppCompatActivity {

    ListView listViewArtist;
    List<Artist> artistList;
    DatabaseReference databaseArtists;
    FirebaseAuth firebaseAuth;
    TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deleteaccount_xml);

        listViewArtist = findViewById(R.id.listViewArtist);

        userEmail = findViewById(R.id.email);
        firebaseAuth = FirebaseAuth.getInstance();

//        userEmail.setText(firebaseUser.getEmail());

        databaseArtists = FirebaseDatabase.getInstance().getReference("Users");

        artistList = new ArrayList<>();

        listViewArtist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Artist artist = artistList.get(i);
                showUpdateDialog(artist.getEmail(), artist.getUserId());

                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                artistList.clear();

                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }
                ArtistList adapter = new ArtistList(DeleteAccountClass.this, artistList);
                listViewArtist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDialog(final String email, final String userId){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setMessage("Are you sure you want to delete " + email + " ?");
        dialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteArtist(userId);

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(MainActivity.this,"No", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

    }

    private void deleteArtist(String userId) {
        DatabaseReference drArtist = FirebaseDatabase.getInstance().getReference("Users").child(userId);
        drArtist.removeValue();
//        delete();

//        Toast.makeText(this,"Account would be succesfully deleted after 30 working days", Toast.LENGTH_SHORT).show();
        Toast.makeText(DeleteAccountClass.this, "Account was deleted", Toast.LENGTH_SHORT).show();
    }

    private void delete(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DeleteAccountClass.this, "Account was deleted", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(DeleteAccountClass.this, "Ayaw", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }



}
