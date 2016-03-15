package com.meleeChat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class Menu extends AppCompatActivity{
    private static final String LOG_TAG = "MENU";
    Bundle b;
    final Context context = this;

    @Override
    protected void onResume() {
        getSupportActionBar().setTitle("Main Menu");
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    public void alertPlayersButton(View v) {
        Intent intent = new Intent(this, AlertList.class);

        intent.putExtras(b); //pass lat and lon along

        startActivity(intent);
    }

    public void reportResultsButton(View v) {
        vibrateCheck();

        Log.i(LOG_TAG, "NOTHINGGG");
    }

    public void displayInfoButton(View v) {
        vibrateCheck();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("There's nothing here!");

        // set dialog message
        alertDialogBuilder
                .setMessage("Come back later")
                .setCancelable(false)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Fuck you",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Log.i(LOG_TAG, "NOTHINGGG2");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (vib == null) {
            Log.i(LOG_TAG, "Vibrator not detected");
        }

        b = getIntent().getExtras(); //get lat an lon to pass to other activities

    }
    @Override
    public void onBackPressed() {
    }


    protected Vibrator vib;
    protected void vibrateCheck() {
        long timeNow = System.currentTimeMillis();


        if (vib != null) {
            long[] once = {0, 200};
            long[] twice = {0, 200, 400, 200};
            long[] thrice = {0, 200, 400, 200, 400, 200};


            vib.vibrate(thrice, -1);

            //vib.vibrate(twice, -1);

            //vib.vibrate(once, -1);
        }
    }


}
