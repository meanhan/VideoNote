package com.xuhan.videonote.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xuhan on 18-7-25.
 */

public class LocalMediaEntity implements Parcelable {
    private String title;
    private String path;
    private String thumb;

    public LocalMediaEntity() {
    }

    protected LocalMediaEntity(Parcel in) {
        title = in.readString();
        path = in.readString();
        thumb = in.readString();
    }

    public static final Creator<LocalMediaEntity> CREATOR = new Creator<LocalMediaEntity>() {
        @Override
        public LocalMediaEntity createFromParcel(Parcel in) {
            return new LocalMediaEntity(in);
        }

        @Override
        public LocalMediaEntity[] newArray(int size) {
            return new LocalMediaEntity[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Override
    public String toString() {
        return "LocalMediaEntity{" +
                "title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(path);
        parcel.writeString(thumb);
    }
}
