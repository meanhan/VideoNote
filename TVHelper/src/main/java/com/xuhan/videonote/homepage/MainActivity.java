package com.xuhan.videonote.homepage;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xuhan.videonote.R;
import com.xuhan.videonote.adapter.ViewPagerAdapter;
import com.xuhan.videonote.discover.DiscoverFragment;
import com.xuhan.videonote.list.ListFragment;
import com.xuhan.videonote.me.MeFragment;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener,
        ListFragment.OnFragmentInteractionListener, DiscoverFragment.OnFragmentInteractionListener,
        MeFragment.OnFragmentInteractionListener, BottomNavigationBar.OnTabSelectedListener {

    private Toolbar toolbar;
    private BottomNavigationBar mNavigationBar;
    private FloatingActionButton mActionButton;
    private BadgeItem badgeItem; //添加角标
    private ViewPager mViewPager;
    private ArrayList<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mActionButton = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(toolbar);
        initViewPager();
        initNavigationBar();
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "bar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // 设置Menu菜单显示图标
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_searcher:
                break;
            case R.id.action_scan:
                break;
            case R.id.action_help:
                break;
            case R.id.action_settings:
                break;
            default:
                break;
        }
        return true;
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(HomeFragment.newInstance("", ""));
        mFragmentList.add(ListFragment.newInstance("", ""));
        mFragmentList.add(DiscoverFragment.newInstance("", ""));
        mFragmentList.add(MeFragment.newInstance("", ""));
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initNavigationBar() {
        mNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mNavigationBar.setTabSelectedListener(this);
        // MODE_FIXED  MODE_SHIFTING
        mNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        // BACKGROUND_STYLE_RIPPLE  BACKGROUND_STYLE_STATIC
        mNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mNavigationBar.setBarBackgroundColor(R.color.colorWhite);
        mNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "首页").setActiveColorResource(
                R.color.colorGreen))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "列表").setActiveColorResource(
                        R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.ic_find_replace_white_24dp, "发现").setActiveColorResource(
                        R.color.colorAccent))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "我的").setActiveColorResource(
                        R.color.colorRed))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    @Override
    public void onTabSelected(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
