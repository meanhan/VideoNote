package com.xuhan.videonote.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xuhan on 18-7-25.
 */

public class MediaBean implements Parcelable {
    private String title;
    private String path;
    private String thumb;

    public MediaBean() {
    }

    protected MediaBean(Parcel in) {
        title = in.readString();
        path = in.readString();
        thumb = in.readString();
    }

    public static final Creator<MediaBean> CREATOR = new Creator<MediaBean>() {
        @Override
        public MediaBean createFromParcel(Parcel in) {
            return new MediaBean(in);
        }

        @Override
        public MediaBean[] newArray(int size) {
            return new MediaBean[size];
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
        return "MediaBean{" +
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
