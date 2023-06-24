package com.example.movieslist.network;



import com.example.movieslist.model.SearchObject;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitAPI {
    @GET("?")
    Call<SearchObject> getAllMovieData(@Query("s") String s, @Query("apikey") String apikey);

}
