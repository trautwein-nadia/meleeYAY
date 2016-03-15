package com.meleeChat.info;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Players {

    @SerializedName("players")
    @Expose
    public List<Player> players = new ArrayList<Player>();

    public class Player {

        @SerializedName("lat")
        @Expose
        public String lat;
        @SerializedName("domain")
        @Expose
        public String domain;
        @SerializedName("tag")
        @Expose
        public String tag;
        @SerializedName("userID")
        @Expose
        public String userID;
        @SerializedName("lng")
        @Expose
        public String lng;

    }

}