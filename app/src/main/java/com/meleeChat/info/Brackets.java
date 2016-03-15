package com.meleeChat.info;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brackets {

    @SerializedName("brackets")
    @Expose
    public List<Bracket> brackets = new ArrayList<Bracket>();
    public class Bracket {

        @SerializedName("lat")
        @Expose
        public String lat;
        @SerializedName("organizer")
        @Expose
        public String organizer;
        @SerializedName("domain")
        @Expose
        public String domain;
        @SerializedName("lng")
        @Expose
        public String lng;

    }

}
