package com.androidteamproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface KakaoAPIInterface{

    @GET("v2/local/search/keyword.json")
    Call<ResultSearchKeyword> getSearchKeyword(
            @Header("Authorization") String key,
            @Query("query") String query



    );
}

