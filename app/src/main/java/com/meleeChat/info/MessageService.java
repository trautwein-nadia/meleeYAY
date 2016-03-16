package com.meleeChat.info;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MessageService {
        @GET("getTournaments")
        Call<Brackets> get_brackets();

        @POST("addTournament")
        Call<Brackets> post_brackets(@Query("TO") String TO,
                                     @Query("domain") String domain,
                                     @Query("lat") float lat,
                                     @Query("lng") float lng);

        @GET("getPlayers")
        Call<Players> get_players(@Query("domain") String domain,
                                  @Query("userID") String user_id,
                                  @Query("lat") float lat,
                                  @Query("lng") float lng);

        @POST("addPlayer")
        Call<Players> post_players(@Query("tag") String tag,
                                   @Query("userID") String user_id,
                                   @Query("domain") String domain,
                                   @Query("lat") float lat,
                                   @Query("lng") float lng);

        @GET("getScores")
        Call<Scores> get_scores(@Query("domain") String domain);

        @POST("addScore")
        Call<Scores> post_scores(@Query("p1") String p1,
                                  @Query("p1s") String p1score,
                                  @Query("p2") String p2,
                                  @Query("p2s") String p2score,
                                  @Query("domain") String domain);

}
