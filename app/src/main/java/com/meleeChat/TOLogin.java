package com.meleeChat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;

import android.util.Base64;
import android.view.View;
import android.widget.EditText;

import com.meleeChat.info.MessageService;
import com.meleeChat.info.Brackets;
import com.meleeChat.info.Players;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by nadia on 3/12/16.
 */
public class TOLogin extends AppCompatActivity {

    private SharedPreferences settings;
    private static final String LOG_TAG = "TO_LOGIN";
    private String user_id;
    private String username = "21Pretzels"; //hardcoded for our convenience and demonstration (see getLoginInfo)
    private String APIkey = "FlyxNHAwJNMvcoibWQvxIp4jaFcu28tIgh0eUQak";
    private String domain = "smashing121";
    private float lat;
    private float lon;

    //to make testing and grading of our app easier, we have filled out these values for you
    private boolean getLoginInfo() {
        EditText editText = (EditText) findViewById(R.id.username);
        //username = editText.getText().toString();
        editText.setText(username);

        editText = (EditText) findViewById(R.id.key);
        //APIkey = editText.getText().toString();
        editText.setText(APIkey);

        editText = (EditText) findViewById(R.id.to_domain);
        //domain = editText.getText().toString();
        editText.setText(domain);

        if (username.equals("") || APIkey.equals("") || domain.equals("")) {
            return false;
        }
        return true;
    }

    public void toLogin(View v) {
        if (getLoginInfo()) {
            sendMessage(domain);
            Intent intent = new Intent(this, Menu.class);

            Bundle b = new Bundle();
            b.putFloat("LAT", lat);
            b.putFloat("LON", lon);
            b.putString("DOMAIN", domain);
            intent.putExtras(b);

            startActivity(intent);
        }
    }


    public void sendMessage(String message) {
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

        if (!message.equals("")) {
            Call<Brackets> queryResponseCall =
                    service.post_brackets(username, domain, lat, lon);


            //Call retrofit asynchronously
            queryResponseCall.enqueue(new Callback<Brackets>() {
                @Override
                public void onResponse(Response<Brackets> response) {
                    Log.i(LOG_TAG, "Code is: " + response.code());
                    if (response.code() == 200) {
                    //Log.i(LOG_TAG, "The result is: " + response.body().brackets);
                    }
                    else {
                        Log.i(LOG_TAG, "error! Code is: " + response.code());
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
    }


    @Override
    protected void onResume() {
        getSupportActionBar().setTitle("TO Login");
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_login);

        Bundle b = getIntent().getExtras();
        lat = b.getFloat("LAT");
        lon = b.getFloat("LON");
        settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        user_id = settings.getString("user_id", user_id);

        Log.i(LOG_TAG, "LAT: " + lat + " LON: " + lon + " user_id: " + user_id);

        EditText e = (EditText) findViewById(R.id.key);
        e.setTypeface(Typeface.DEFAULT);
        e.setTransformationMethod(new PasswordTransformationMethod());
    }

    //what are we doing with this?
    private class Feedback extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            HttpURLConnection urlConnection;
            String userpass = username + ":" + APIkey;
            Log.i(LOG_TAG, "USERPASS: " + userpass);
            String result = "";
            // do above Server call here
            try
            {
                URL url = new URL("https://api.challonge.com/v1/tournaments.json?subdomain=smashing121");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Authorization", "Basic " + new String(Base64.encode(userpass.getBytes(), Base64.NO_WRAP)));
                BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());
                Log.i("Code is:", "" + urlConnection.getResponseCode());
                if (in != null)
                {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    String line = bufferedReader.readLine();
                    Log.i("Information is", "" + line);

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }

                }

                in.close();
                urlConnection.disconnect();

                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return "some message";
        }

        @Override
        protected void onPostExecute(String message) {
            //process message
        }
    }

}
