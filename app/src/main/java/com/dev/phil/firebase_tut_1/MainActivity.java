package com.dev.phil.firebase_tut_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "";

    public static class PlayerViewHolder extends RecyclerView.ViewHolder{
        public TextView playerName;
        public TextView playereMail;
        public TextView playerHandicap;



        public PlayerViewHolder(View v){
            super(v);
            playerName=(TextView)itemView.findViewById(R.id.name);
            playereMail=(TextView)itemView.findViewById(R.id.eMail);
            playerHandicap=(TextView)itemView.findViewById(R.id.handicap);
        }
    }
    public static final String PLAYERS = "players";

    private RecyclerView mPlayerRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<Golfer, PlayerViewHolder> mFirebaseAdapter;

    private FloatingActionButton new_player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mPlayerRecyclerView = (RecyclerView)findViewById(R.id.playerRecyclerView);
        mLinearLayoutManager= new LinearLayoutManager(this);
        //mLinearLayoutManager.setStackFromEnd(true);


        //Database Initialisation

        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Golfer, PlayerViewHolder>(
                Golfer.class,
                R.layout.player,
                PlayerViewHolder.class,
                mFirebaseDatabaseReference.child(PLAYERS))
        {
            @Override
            protected void populateViewHolder(PlayerViewHolder viewHolder, Golfer model, int position) {
                viewHolder.playerName.setText(model.getName());
                viewHolder.playereMail.setText(model.geteMail());
                viewHolder.playerHandicap.setText(model.getHandicap());
            }
        };

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){
                @Override
                public void onItemRangeInserted(int positionStart, int itemCount){
                    super.onItemRangeInserted(positionStart, itemCount);
                int playerCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiblePosition == -1 || (positionStart >= (playerCount -1) && lastVisiblePosition == (positionStart -1))){
                    mPlayerRecyclerView.scrollToPosition(positionStart);
                }
            }
        });
        mPlayerRecyclerView.setLayoutManager(mLinearLayoutManager);
        mPlayerRecyclerView.setAdapter(mFirebaseAdapter);

        new_player= (FloatingActionButton)findViewById(R.id.addPlayer);

        new_player.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewPlayer.class);
                startActivity(intent);
            }

          });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in.
        // TODO: Add code to check if user is signed in.
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}

