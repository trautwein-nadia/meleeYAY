package com.meleeChat.data;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Praneetha on 3/12/2016.
 */
class GetParticipants extends AsyncTask<String, Void, String> {

    public Participant ParticipantJson;

    public Participant getParticipantJson(String s) {
        ParticipantJson = new Participant();
        try{
            JSONArray test = new JSONArray(s);
            for(int i=0; i < test.length(); i++){
                String participantName = "";
                JSONObject jsonObject = test.getJSONObject(i);
                JSONObject o1 = test.getJSONObject(i);
                //JSONObject mainObject = new JSONObject(s);
                JSONObject o2 = o1.getJSONObject("participant");
                participantName = o2.getString("name");
                Log.i("Participant name is: ", participantName);
            }
        }
        catch(JSONException e) {
            System.out.print("Could not parse");
        }

        return ParticipantJson;
    }
    @Override
    protected String doInBackground(String... urls) {
        HttpURLConnection urlConnection;
        String apiKey = urls[0];
        String subdomain = urls[1];
        String tournament = urls[2];
        String result = "";
        // do above Server call here
        try
        {
            URL url = new URL("https://api.challonge.com/v1/tournaments/"+subdomain+"-"+tournament+"/participants.json?api_key="+apiKey);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Log.i("Code is:", "" + urlConnection.getResponseCode());
            if (in != null)
            {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                getParticipantJson(result);
                Log.i("Information is", result);

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
}
