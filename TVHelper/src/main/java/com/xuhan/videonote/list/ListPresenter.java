package com.xuhan.videonote.list;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.xuhan.videonote.bean.LocalMediaEntity;
import com.xuhan.videonote.mvp.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * MVPPlugin
 */

public class ListPresenter extends BasePresenterImpl<ListContract.View> implements ListContract.Presenter {

    @Override
    public void loadData() {
        if (mView != null) {
            List<LocalMediaEntity> mediaList = scanMediaFromSDCard();
            if (!mediaList.isEmpty()) {
                mView.loadSuccess(mediaList);
            } else {
                mView.loadFailed("no media");
            }
        }
    }

    private List<LocalMediaEntity> scanMediaFromSDCard() {
        List<LocalMediaEntity> dataList = new ArrayList<>();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        // MediaStore.Video.Thumbnails.DATA:视频缩略图的文件路径
        String[] thumbColumns = {MediaStore.Video.Thumbnails.DATA,
                MediaStore.Video.Thumbnails.VIDEO_ID};

        // MediaStore.Video.Media.DATA：视频文件路径；
        // MediaStore.Video.Media.DISPLAY_NAME : 视频文件名，如 testVideo.mp4
        // MediaStore.Video.Media.TITLE: 视频标题 : testVideo
        String[] mediaColumns = {MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA, MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.MIME_TYPE,
                MediaStore.Video.Media.DISPLAY_NAME};

        Cursor cursor = mView.getContext().getContentResolver().query(uri,
                mediaColumns, null, null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    LocalMediaEntity media = new LocalMediaEntity();
                    int id = cursor.getInt(cursor
                            .getColumnIndex(MediaStore.Video.Media._ID));
                    String title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                    String thumbPath = "";
                    Cursor thumbCursor = mView.getContext().getContentResolver().query(
                            MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                            thumbColumns, MediaStore.Video.Thumbnails.VIDEO_ID
                                    + "=" + id, null, null);
                    if (thumbCursor.moveToFirst()) {
                        thumbPath = (thumbCursor.getString(thumbCursor
                                .getColumnIndex(MediaStore.Video.Thumbnails.DATA)));
                    }
                    media.setTitle(title);
                    media.setPath(path);
                    media.setThumb(thumbPath);
                    dataList.add(media);
                } while (cursor.moveToNext());
            }
        }

        return dataList;
    }
}
