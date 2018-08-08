package com.xuhan.videonote.moment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.xuhan.videonote.R;

/**
 * @author meanhan
 *         朋友圈
 */

public class MomentsActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private ImageView mToolbarBg;
    private ImageView mToolbarIcon;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moment);
        mAppBarLayout = findViewById(R.id.me_appBar);
        mToolbar = findViewById(R.id.me_toolbar);
        mCollapsingToolbar = findViewById(R.id.me_collapsing_toolbar);
        mToolbarBg = findViewById(R.id.me_toolbar_bg);
        mToolbarIcon = findViewById(R.id.me_toolbar_icon);
        mRecyclerView = findViewById(R.id.setting_recycler_view);
        initView();
        initListener();
    }

    private void initView() {
        mCollapsingToolbar.setTitle(getString(R.string.moment));
        mCollapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.textColorGray));
        mCollapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.textColorGray));
        mToolbar.setNavigationIcon(R.drawable.icon_back_gray);
        setSupportActionBar(mToolbar);
    }

    private void initListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    //展开状态

                } else if (state == State.COLLAPSED) {
                    //折叠状态

                } else {
                    //中间状态

                }

            }
        });
    }

}
