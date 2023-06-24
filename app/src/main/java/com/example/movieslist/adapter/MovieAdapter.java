package com.example.movieslist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieslist.R;
import com.example.movieslist.databinding.RowMoviesListBinding;
import com.example.movieslist.model.SearchList;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    private ArrayList<SearchList> searchListArrayList;
    Context context;

    public MovieAdapter(ArrayList<SearchList> searchListArrayList, Context context) {
        this.searchListArrayList = searchListArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowMoviesListBinding binding = RowMoviesListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        final ViewHolder viewHolderClass = (ViewHolder) holder;
        viewHolderClass.binding.setSearchlist(searchListArrayList.get(position));
        Glide.with(context)
                .load(searchListArrayList.get(position).getPoster())
                .error(R.drawable.images)
                .placeholder(R.drawable.images)
                .into(holder.binding.posterImg);
    }



    @Override
    public int getItemCount() {
        if(searchListArrayList == null)
        {
            return 0;
        }
        else {
            return searchListArrayList.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RowMoviesListBinding binding;

        public ViewHolder(RowMoviesListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
