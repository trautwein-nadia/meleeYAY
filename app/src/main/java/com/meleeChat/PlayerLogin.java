
package com.meleeChat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Vibrator;

import com.meleeChat.info.MessageService;
import com.meleeChat.info.Brackets;
import com.meleeChat.info.Players;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlayerLogin extends AppCompatActivity {

    private static final String LOG_TAG = "PLAYER_LOGIN";
    private String user_id;
    private float lat;
    private float lon;
    private String tag;
    private String domain;
    private SharedPreferences settings;
    private List<Players.Player> tagList;
    private List<Brackets.Bracket> domainList;
    private ArrayList<String> tournaments;
    private ListView tournamentList;
    private String username;
    final Context context = this;


    protected Vibrator vib;

    protected void vibrateCheck() {
        long timeNow = System.currentTimeMillis();
        if (vib != null) {
            long[] once = {0, 100};
            long[] twice = {0, 100, 400, 100};
            long[] thrice = {0, 100, 400, 100, 400, 100};
            vib.vibrate(thrice, -1);
            //vib.vibrate(twice, -1);
            //vib.vibrate(once, -1);
        }
    }


    private boolean getLoginInfo() {
        EditText editText = (EditText) findViewById(R.id.username);
        username = editText.getText().toString();

        TextView t = (TextView) findViewById(R.id.tournamentName);
        domain = t.getText().toString();
        getPlayers(); //here for now...
        if (username.equals("")) {
            return false;
        }
        return true;
    }

    private void startMenu() {
        Intent intent = new Intent(this, Menu.class);

        Bundle b = new Bundle();
        b.putFloat("LAT", lat);
        b.putFloat("LON", lon);
        b.putString("DOMAIN", domain);
        b.putString("TAG", username);
        b.putString("ID", user_id);
        intent.putExtras(b);

        startActivity(intent);
    }

    public void playerLogin(View v) {
        if (getLoginInfo()) {
            if (tagList != null) {
                //parse responses so that username isnt taken
                for (int i = (tagList.size() - 1); i >= 0; i--) {
                    tag = tagList.get(i).tag;
                    domain = tagList.get(i).domain;
                    String oldID = tagList.get(i).userID;
                    Log.i(LOG_TAG, "tag list: " + tag);
                    if (tag.equals(username)) {
                        //add message
                        if (user_id.equals(oldID)) {
                            Log.i(LOG_TAG, tag + " is ALLOWED and player is logging in again");
                            postMessage();
                            startMenu(); //tag was not taken
                        }
                        else {
                            Log.i(LOG_TAG, "tag taken?");

                            tagTaken();
                            return;
                        }
                    }
                }
            }
            else {
                Log.i(LOG_TAG, tag + " is ALLOWED and was not perviously set");
                postMessage();
                startMenu();
            }
        }
    }

    public void tagTaken(){
            vibrateCheck();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            // set title
            alertDialogBuilder.setTitle("Tag already taken!");
            // set dialog message
            alertDialogBuilder
            .setMessage("Please enter a new Tag")
            .setCancelable(false)
            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // if this button is clicked, close
                    // current activity
                    dialog.cancel();
                }
            });
                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    // show it
                    alertDialog.show();
                    Log.i(LOG_TAG, tag + " is already taken!");
    }

    public void postMessage() {
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

        Call<Players> queryResponseCall =
                service.post_players(username, user_id, domain, lat, lon);

        queryResponseCall.enqueue(new Callback<Players>() {
            @Override
            public void onResponse(Response<Players> response) {
                Log.i(LOG_TAG, "postMesasge code is: " + response.code());
                if (response.code() == 200) {
                    //Log.i(LOG_TAG, "The result is: " + response.body().players);
                }
                else {
                    Log.i(LOG_TAG, "Code is: " + response.code());
                    //toast with error
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
                //toast error
            }
        });
    }

    public void getPlayers() {
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

        Call<Players> queryResponseCall = service.get_players(domain, user_id, lat, lon);

            queryResponseCall.enqueue(new Callback<Players>() {
                @Override
                public void onResponse(Response<Players> response) {
                    Log.i(LOG_TAG, "getPlayers code is: " + response.code());
                    if (response.code() == 200) {
                        Log.i(LOG_TAG, "The result is: " + response.body().players);

                        tagList = response.body().players;

                    } else {
                        Log.i(LOG_TAG, "Code is: " + response.code());
                        //toast with error
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    // Log error here since request failed
                    //toast error
                }

            });
    }
    @Override
    protected void onResume() {
        getSupportActionBar().setTitle("Competitor Login");
        getDomains();
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_login);
        vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        if (vib == null) {
            Log.i(LOG_TAG, "Vibrator not detected");

        }
        Bundle b = getIntent().getExtras();
        lat = b.getFloat("LAT");
        lon = b.getFloat("LON");
        settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        user_id = settings.getString("user_id", user_id);


        Log.i(LOG_TAG, "LAT: " + lat + " LON: " + lon + " user_id: " + user_id);
    }

    private void getDomains() {
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

        Call<Brackets> queryResponseCall =
                service.get_brackets();

        queryResponseCall.enqueue(new Callback<Brackets>() {
            @Override
            public void onResponse(Response<Brackets> response) {
                Log.i(LOG_TAG, "getDomains code is: " + response.code());
                if (response.code() == 200) {
                    //Log.i(LOG_TAG, "The result is: " + response.body().brackets);
                    domainList = response.body().brackets;
                    tournaments = new ArrayList<String>();
                    for (int i = (domainList.size() - 1); i >= 0; i--) {
                        tournaments.add(response.body().brackets.get(i).domain);
                    }
                    getTournaments();
                }
                else {
                    Log.i(LOG_TAG, "Code is: " + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
            }
        });
    }

    public void getTournaments() {
        tournamentList = (ListView) findViewById(R.id.tournamentList);

        if (tournaments != null) {
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<String>(this,
                    R.layout.tournament_listview,
                    R.id.textView5,
                    tournaments);
            tournamentList.setAdapter(adapter);
        }

        tournamentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = (TextView) findViewById(R.id.tournamentName);
                TextView text = (TextView) tournamentList.getChildAt(position - tournamentList.getFirstVisiblePosition()).findViewById(R.id.textView5);
                name.setText(text.getText());
            }
        });
    }
}

