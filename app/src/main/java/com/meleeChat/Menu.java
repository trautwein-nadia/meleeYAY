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

import com.meleeChat.info.Brackets;
import com.meleeChat.info.MessageService;
import com.meleeChat.info.Players;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Menu extends AppCompatActivity{
    private static final String LOG_TAG = "MENU";
    Bundle b;
    Boolean isTO;
    String domain;

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
    }

/*
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(msg1)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        cancel = false;
                    }
                });
        if (negative == true) {
                alertDialogBuilder.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });
        }
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    */

    public void logout(View v) {
        if (isTO) {
            String msg = "Logging out will end the tournament and delete data. Continue?";

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Warning!");
            alertDialogBuilder
                    .setMessage(msg)
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            finishTournament();
                            startActivity(i);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                    }
                });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        else {
            String msg = "Are you sure you want to log out?";
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Warning!");
            alertDialogBuilder
                    .setMessage(msg)
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            logoutPlayer();
                            startActivity(i);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (vib == null) {
            Log.i(LOG_TAG, "Vibrator not detected");
        }

        b = getIntent().getExtras();
        isTO = b.getBoolean("isTO");
        domain = b.getString("DOMAIN");
    }


    @Override
    public void onBackPressed() {
        //overridden. do nothing. you must log out to go back
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
    public void finishTournament() {
        //Magic HTTP stuff
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://melee121.pythonanywhere.com/melee/default/")
                .addConverterFactory(GsonConverterFactory.create())    //parse Gson string
                .client(httpClient)    //add logging
                .build();

        MessageService service = retrofit.create(MessageService.class);

        Call<Brackets> queryResponseCall = service.finish_tournament(domain);

        //Call retrofit asynchronously
        queryResponseCall.enqueue(new Callback<Brackets>() {
            @Override
            public void onResponse(Response<Brackets> response) {
                Log.i(LOG_TAG, "Code is: " + response.code());
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
            }
        });
    }

    public void logoutPlayer() {
        //Magic HTTP stuff
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://melee121.pythonanywhere.com/melee/default/")
                .addConverterFactory(GsonConverterFactory.create())    //parse Gson string
                .client(httpClient)    //add logging
                .build();

        MessageService service = retrofit.create(MessageService.class);

        String tag = b.getString("TAG");
        String userid = b.getString("ID");
        Call<Players> queryResponseCall = service.logout_player(tag, userid, domain);

        //Call retrofit asynchronously
        queryResponseCall.enqueue(new Callback<Players>() {
            @Override
            public void onResponse(Response<Players> response) {
                Log.i(LOG_TAG, "Code is: " + response.code());
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
            }
        });
    }

}
