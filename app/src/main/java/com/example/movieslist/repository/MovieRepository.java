package com.example.movieslist.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.movieslist.model.SearchObject;
import com.example.movieslist.network.RetrofitClient;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private Application application;
    private MutableLiveData<SearchObject> moviesListMutableLiveData = new MutableLiveData<>();
    public MovieRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<SearchObject> getMoviesList(String s, String api_key) {
        try {
            Call<SearchObject> call = RetrofitClient.getRetrofitApiInstance().getAllMovieData(s,api_key);
            Log.i("getMoviesList_api", call.request().url().toString());
            call.enqueue(new Callback<SearchObject>() {
                @Override
                public void onResponse(Call<SearchObject> call, Response<SearchObject> response) {
                    Log.i("getMoviesList_api", new Gson().toJson(response.body()));
                    if (response.body() != null) {
                        try {
                            moviesListMutableLiveData.setValue(response.body());
                        } catch (Exception e) {
                            e.printStackTrace();
                            moviesListMutableLiveData = null;
                        }
                    } else {
                        moviesListMutableLiveData = null;
                    }
                }

                @Override
                public void onFailure(Call<SearchObject> call, Throwable t) {
                    moviesListMutableLiveData = null;
                    t.printStackTrace();
                }
            });

        } catch (Exception e) {
            moviesListMutableLiveData = null;
            e.printStackTrace();
        }
        return moviesListMutableLiveData;
    }
}
