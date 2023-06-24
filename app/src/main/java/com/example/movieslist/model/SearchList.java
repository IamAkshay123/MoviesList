package com.example.movieslist.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class SearchList extends BaseObservable implements Parcelable {
    @SerializedName("Title")
    private  String Title;

    @SerializedName("Year")
    private  String Year;

    @SerializedName("imdbID")
    private  String imdbID;

    @SerializedName("Type")
    private  String Type;

    @SerializedName("Poster")
    private  String Poster;

    protected SearchList(Parcel in) {
        Title = in.readString();
        Year = in.readString();
        imdbID = in.readString();
        Type = in.readString();
        Poster = in.readString();
    }

    public static final Creator<SearchList> CREATOR = new Creator<SearchList>() {
        @Override
        public SearchList createFromParcel(Parcel in) {
            return new SearchList(in);
        }

        @Override
        public SearchList[] newArray(int size) {
            return new SearchList[size];
        }
    };

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(Title);
        parcel.writeString(Year);
        parcel.writeString(imdbID);
        parcel.writeString(Type);
        parcel.writeString(Poster);
    }
}
