package com.meleeChat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.meleeChat.info.MessageService;
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

public class AlertList extends AppCompatActivity {
    private static final String LOG_TAG = "CHAT_ACTIVITY";
    private String user_id;
    private String domain;
    private float lat;
    private float lon;
    private List<Players.Player> responses;
    private ArrayList<ListElement> aList;
    private MyAdapter aa;

    private class ListElement {
        ListElement(String tl, String bl, String x) {
            msg = tl;
            tag = bl;
            id = x;
        }

        public String msg;
        public String tag;
        public String id;
    }

    public void refresh(View v) {
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
                service.get_players(domain, user_id, lat, lon);

        //Call retrofit asynchronously
        queryResponseCall.enqueue(new Callback<Players>() {
            @Override
            public void onResponse(Response<Players> response) {
                if (response.code() == 200) {
                    Log.i(LOG_TAG, "Code is: " + response.code());
                    Log.i(LOG_TAG, "resultList: " + response.body().players);

                    responses = response.body().players;

                    populateList();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // Log error here since request failed
            }
        });
    }

    //not sure if this is the correct place for this?//
    //I think I can add these in the aa listview loop thing?
    public void alert(View v) {
        Log.i(LOG_TAG, "THIS CURRENTLY DOES NOTHING");
    }

    private void populateList() {
        aList = new ArrayList<ListElement>();
        aa = new MyAdapter(this, R.layout.player_info, aList);
        ListView myListView = (ListView) findViewById(R.id.player_list);

        for (int i = (responses.size() - 1); i >= 0; i--) {
            String tag = responses.get(i).tag;
            String info = responses.get(i).domain;
            String id = responses.get(i).userID;
            if (info.equals(domain) && !id.equals(user_id)) {
                ListElement le = new ListElement("", tag, "");
                myListView.setAdapter(aa);
                aList.add(le);
            }
        }
        aa.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        getSupportActionBar().setTitle("Choose a player to notify");
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        user_id = settings.getString("user_id", null);
        Bundle b = getIntent().getExtras();
        lat = b.getFloat("LAT");
        lon = b.getFloat("LON");
        domain = b.getString("domain", domain);


        refresh(findViewById(R.id.chat));
    }


    private class MyAdapter extends ArrayAdapter<ListElement> {
        private int resource;
        private Context context;

        public MyAdapter(Context _context, int _resource, List<ListElement> items) {
            super(_context, _resource, items);
            resource = _resource;
            context = _context;
            this.context = _context;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout newView;

            ListElement w = getItem(position);

                // Inflate a new view if necessary.
                if (convertView == null) {
                    newView = new LinearLayout(getContext());
                    String inflater = Context.LAYOUT_INFLATER_SERVICE;
                    LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
                    vi.inflate(resource,  newView, true);
                } else {
                    newView = (LinearLayout) convertView;
                }

                TextView tv = (TextView) newView.findViewById(R.id.player_tag);
                tv.setText(w.tag);
            return newView;
        }//end getView

    } //end MyAdapter class
}//end AlertList class
