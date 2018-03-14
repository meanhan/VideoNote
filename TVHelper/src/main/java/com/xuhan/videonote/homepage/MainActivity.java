package com.xuhan.videonote.homepage;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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


    private BottomNavigationBar mNavigationBar;
    private BadgeItem badgeItem; //添加角标
    private ArrayList<Fragment> mFragmentList;
    private HomeFragment mHomeFragment;
    private ListFragment mListFragment;
    private DiscoverFragment mDiscoverFragment;
    private MeFragment meFragment;
    private FragmentManager mFragmentManager;
//    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initNavigationBar();
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

    private void initFragment() {
        mHomeFragment = HomeFragment.newInstance("", "");
        mListFragment = ListFragment.newInstance("", "");
        mDiscoverFragment = DiscoverFragment.newInstance("", "");
        meFragment = MeFragment.newInstance("", "");
        mFragmentList = new ArrayList<>();
        mFragmentList.add(mHomeFragment);
        mFragmentList.add(mListFragment);
        mFragmentList.add(mDiscoverFragment);
        mFragmentList.add(meFragment);
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        mFragmentTransaction.add(mHomeFragment,"home");
//        mFragmentTransaction.add(mListFragment,"list");
//        mFragmentTransaction.add(mDiscoverFragment,"discover");
//        mFragmentTransaction.add(meFragment,"me");
//        mFragmentTransaction.show(mHomeFragment);
//        mFragmentTransaction.hide(mListFragment);
//        mFragmentTransaction.hide(mDiscoverFragment);
//        mFragmentTransaction.hide(meFragment);
        fragmentTransaction.replace(R.id.layout_frame, mHomeFragment);
        fragmentTransaction.commit();
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
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_frame, mFragmentList.get(position));
        fragmentTransaction.commit();
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
