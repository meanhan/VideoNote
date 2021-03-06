package com.xuhan.videonote.moment;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.MomentRecyclerAdapter;
import com.xuhan.videonote.contants.Contants;
import com.zhl.cbdialog.CBDialogBuilder;

import java.util.ArrayList;

/**
 * @author meanhan
 *         朋友圈
 */

public class MomentsActivity extends AppCompatActivity {

    /**
     * boxing选择器可选照片数量,默认为9张
     */
    private static final int BOXING_PICKER_MAX_COUNT = 12;

    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private ImageView mToolbarBg;
    private TextView mTextMoment;
    private RecyclerView mRecyclerView;
    private MomentRecyclerAdapter mAdapter;
    private Uri takePhotoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment);
        mAppBarLayout = findViewById(R.id.me_appBar);
        mToolbar = findViewById(R.id.me_toolbar);
        mCollapsingToolbar = findViewById(R.id.me_collapsing_toolbar);
        mToolbarBg = findViewById(R.id.me_toolbar_bg);
        mRecyclerView = findViewById(R.id.setting_recycler_view);
        mTextMoment = findViewById(R.id.text_moment);
        initView();
        initListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_camera_white, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_moment) {
            openBoxingPicker();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Contants.REQUEST_TAKE_PHOTO) {
                Glide.with(this).load(takePhotoUri).into(mToolbarBg);
                Toast.makeText(this, takePhotoUri.toString(), Toast.LENGTH_SHORT).show();
            } else if (requestCode == Contants.REQUEST_SELECT_PHOTO) {
                Uri imageUri = data.getData();
                Glide.with(this).load(imageUri).into(mToolbarBg);
            } else if (requestCode == Contants.REQUEST_BOXING_PICKER) {
                ArrayList<BaseMedia> medias = Boxing.getResult(data);
                mAdapter.setDataList(medias);
                Toast.makeText(this, medias.size() + "", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
//        mCollapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.textColorGray));
//        mCollapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.textColorGray));
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MomentRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mToolbarBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPickDialog();
            }
        });

        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态
                    mToolbar.setTitle("");
                    mToolbar.setNavigationIcon(R.drawable.icon_back_white);
                    mToolbar.getMenu().clear();
                    mToolbar.inflateMenu(R.menu.menu_camera_white);
                } else if (state == State.COLLAPSED) {
                    //折叠状态
                    mToolbar.setTitleTextColor(getResources().getColor(R.color.textColorGray));
                    mToolbar.setTitle(getString(R.string.moment));
                    mToolbar.setNavigationIcon(R.drawable.icon_back_gray);
                    mToolbar.getMenu().clear();
                    mToolbar.inflateMenu(R.menu.menu_camera_gray);

                    mTextMoment.setVisibility(View.VISIBLE);
                } else {
                    //中间状态
                    if (mTextMoment.getVisibility() == View.VISIBLE) {
                        mTextMoment.setVisibility(View.GONE);
                    }
                }

            }
        });
    }

    private void showPickDialog() {
        final String[] itemOptions = new String[]{"拍照", "相册"};
        new CBDialogBuilder(this)
                .setTouchOutSideCancelable(true)
                .showConfirmButton(false)
                .setTitle(null)
                .setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM)
                .setItems(itemOptions, new CBDialogBuilder.onDialogItemClickListener() {
                    @Override
                    public void onDialogItemClick(CBDialogBuilder.DialogItemAdapter dialogItemAdapter,
                                                  Context context, CBDialogBuilder cbDialogBuilder,
                                                  Dialog dialog, int i) {
                        if (i == 0) {
                            takePhoto();
                        } else {
                            selectPhoto();
                        }
                        dialog.dismiss();
                    }
                })
                .create().show();
    }

    /**
     * 跳转至拍照
     */
    private void takePhoto() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values = new ContentValues();
            takePhotoUri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, takePhotoUri);
            startActivityForResult(intent, Contants.REQUEST_TAKE_PHOTO);
        }

    }

    /**
     * 跳转至相册选择图片
     */
    private void selectPhoto() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, Contants.REQUEST_SELECT_PHOTO);
    }

    /**
     * 跳转至boxing选择器
     */
    private void openBoxingPicker() {
        BoxingConfig config = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG)
                .needCamera(R.drawable.ic_boxing_camera_white).needGif().withMaxCount(BOXING_PICKER_MAX_COUNT);
        Boxing.of(config)
                .withIntent(this, BoxingActivity.class)
                .start(this, Contants.REQUEST_BOXING_PICKER);
    }
}
