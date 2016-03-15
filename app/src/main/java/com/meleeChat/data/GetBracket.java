package com.meleeChat.data;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.meleeChat.data.JSONAdapter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Praneetha on 3/12/2016.
 */
public class GetBracket extends AsyncTask<String, Void, List<String>> {
    public static String output = "";
    public String result;
    @Override
    protected List<String> doInBackground(String... urls) {
        HttpURLConnection urlConnection;
        String apiKey = urls[0];
        String subdomain = urls[1];
        result = "";
        // do above Server call here
        try
        {
            URL url = new URL("https://api.challonge.com/v1/tournaments.json?subdomain="+subdomain+"&api_key="+apiKey);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Log.i("Code is:", "" + urlConnection.getResponseCode());
            if (in != null)
            {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line + "(:)";
                }
                Log.i("Information is", result);

            }

            //convert result to java Tournament object


            in.close();
            urlConnection.disconnect();

            return getTournamentJson(result);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return getTournamentJson(result);
    }


    public List<String> getTournamentJson(String s) {
        output = "";
        List<String> tournaments = new ArrayList<String>();
        try{
            JSONArray test = new JSONArray(s);
            for(int i=0; i < test.length(); i++){
                String tournamentName = "";
                JSONObject jsonObject = test.getJSONObject(i);
                JSONObject o1 = test.getJSONObject(i);
                //JSONObject mainObject = new JSONObject(s);
                JSONObject o2 = o1.getJSONObject("tournament");

                tournamentName = o2.getString("name");
                tournaments.add(tournamentName);
                Log.i("Tournament name is: ", tournamentName);
                output += tournamentName + "\n";
            }
        }
        catch(JSONException e) {
            System.out.print("Could not parse");
        }

        Log.i("Tournaments are ", ""+tournaments);

        return tournaments;
    }
}
