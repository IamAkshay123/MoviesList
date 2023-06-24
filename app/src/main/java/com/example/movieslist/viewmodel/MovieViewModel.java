package com.example.movieslist.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieslist.model.SearchObject;
import com.example.movieslist.repository.MovieRepository;

public class MovieViewModel extends AndroidViewModel {
    private MovieRepository movieRepository;


    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
    }

    public LiveData<SearchObject> getMoviesList(String s, String api_key){

        return movieRepository.getMoviesList(s, api_key);
    }
}
