package com.example.movieslist.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchObject extends BaseObservable implements Parcelable {
    @SerializedName("Search")
    ArrayList<SearchList> Search;

    protected SearchObject(Parcel in) {
        Search = in.createTypedArrayList(SearchList.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(Search);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchObject> CREATOR = new Creator<SearchObject>() {
        @Override
        public SearchObject createFromParcel(Parcel in) {
            return new SearchObject(in);
        }

        @Override
        public SearchObject[] newArray(int size) {
            return new SearchObject[size];
        }
    };

    public ArrayList<SearchList> getSearch() {
        return Search;
    }

    public void setSearch(ArrayList<SearchList> search) {
        Search = search;
    }
}
