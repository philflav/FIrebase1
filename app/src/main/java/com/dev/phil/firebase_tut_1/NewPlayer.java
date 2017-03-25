package com.dev.phil.firebase_tut_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.games.Player;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Phil on 25/03/2017.
 */

public class NewPlayer extends AppCompatActivity {

    private Button save;
    private EditText name, eMail, handicap;
    private DatabaseReference mDatabaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_player);
        name = (EditText)findViewById(R.id.name);
        eMail = (EditText)findViewById(R.id.eMail);
        handicap = (EditText)findViewById(R.id.Handicap);
        save = (Button)findViewById(R.id.save);
        mDatabaseReference= FirebaseDatabase.getInstance().getReference();
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Golfer newGolfer = new Golfer(name.getText().toString(), eMail.getText().toString(),handicap.getText().toString());
                mDatabaseReference.child("players").push().setValue(newGolfer);
                finish();
            }
        });
    }


}
