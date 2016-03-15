package com.meleeChat.info;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Scores {

    @SerializedName("scores")
    @Expose
    public List<Score> scores = new ArrayList<Score>();

    public class Score {

        @SerializedName("player2")
        @Expose
        public String player2;
        @SerializedName("domain")
        @Expose
        public String domain;
        @SerializedName("player1")
        @Expose
        public String player1;
        @SerializedName("score1")
        @Expose
        public String score1;
        @SerializedName("score2")
        @Expose
        public String score2;

    }

}

