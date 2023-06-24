package com.example.movieslist.activities;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieslist.R;
import com.example.movieslist.adapter.MovieAdapter;
import com.example.movieslist.databinding.ActivityMoviesListBinding;
import com.example.movieslist.model.SearchList;
import com.example.movieslist.viewmodel.MovieViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MovielistActivity extends BaseActivity {
    RecyclerView rvMoviesList;
    EditText edMovieName;
    TextView txtNoData;
    NestedScrollView nestedScrollView;
    private MovieViewModel movieViewModel;
    ArrayList<SearchList> searchListArrayList;
    String apiKey = "3ad582ab";
    int page = 0, limit = 10;
    String txt = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        rvMoviesList = findViewById(R.id.rvMoviesList);
        edMovieName = findViewById(R.id.edMovieName);
        txtNoData = findViewById(R.id.txtNoData);
        nestedScrollView = findViewById(R.id.nestedScrollView);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        edMovieName.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        txt = edMovieName.getText().toString();
                        if (s.length() != 0) {
                            rvMoviesList.setVisibility(View.VISIBLE);
                            txtNoData.setVisibility(View.GONE);
                            getMovieList(txt, apiKey, page, limit);
                        } else {
                            txtNoData.setVisibility(View.VISIBLE);
                            rvMoviesList.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                }
        );

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())
                {
                    page++;
                    showProgressDialog(MovielistActivity.this);
                    getMovieList(txt, apiKey, page, limit);

                }
            }
        });
    }

    private void getMovieList(String name, String apiKey, int page, int limit) {
        if (isOnline(MovielistActivity.this)) {
            try {
                if(page > limit)
                {
                   dismissProgressDialog();
                }
                rvMoviesList.setVisibility(View.GONE);
                txtNoData.setVisibility(View.VISIBLE);
                movieViewModel.getMoviesList(name, apiKey).observe(this, response -> {
                    Log.e("TAG", "Response: " + new Gson().toJson(response));
                    if (response != null) {
                        dismissProgressDialog();
                        searchListArrayList = response.getSearch();
                        rvMoviesList.setVisibility(View.VISIBLE);
                        txtNoData.setVisibility(View.GONE);
                        rvMoviesList.setLayoutManager(new LinearLayoutManager(MovielistActivity.this, LinearLayoutManager.VERTICAL, false));
                        MovieAdapter movieAdapter = new MovieAdapter(searchListArrayList, MovielistActivity.this);
                        rvMoviesList.setAdapter(movieAdapter);
                    } else {
                        dismissProgressDialog();
                        rvMoviesList.setVisibility(View.GONE);
                        txtNoData.setVisibility(View.VISIBLE);
                    }
                });

            } catch (Exception e) {
                dismissProgressDialog();
                e.printStackTrace();
            }

        } else {
            Toast.makeText(MovielistActivity.this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
        }

    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo;
        try {
            netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}