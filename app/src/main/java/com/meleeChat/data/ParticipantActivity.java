package com.meleeChat.data;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.meleeChat.R;
import com.meleeChat.data.ParticipantActivity;
import com.meleeChat.data.Participant;
import com.meleeChat.data.Tournament;


import javax.net.ssl.HttpsURLConnection;

public class ParticipantActivity extends AppCompatActivity {
    private static String username;
    private static String password;
    private static String responseMessage;
    private static String input;
    private static int responseCode;
    private static HttpsURLConnection connection;


    public static void init() {
        String out = "";
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants);

    }

}
