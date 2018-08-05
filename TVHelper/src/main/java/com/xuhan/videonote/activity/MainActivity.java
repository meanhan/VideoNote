package com.xuhan.videonote.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.xuhan.videonote.R;
import com.xuhan.videonote.discover.DiscoverFragment;
import com.xuhan.videonote.home.HomeFragment;
import com.xuhan.videonote.list.ListFragment;
import com.xuhan.videonote.personalcenter.PersonalCenterFragment;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author meanhan
 */
public class MainActivity extends AppCompatActivity implements HomeFragment.OnHomeFragmentListener,
        ListFragment.OnListFragmentListener, DiscoverFragment.OnDiscoverFragmentListener,
        PersonalCenterFragment.OnPersonalFragmentListener, BottomNavigationBar.OnTabSelectedListener {

    public static final int REQUEST_CODE_ASK_PERMISSIONS = 100;
    private BottomNavigationBar mNavigationBar;
    /**
     *  添加角标
     */
    private BadgeItem badgeItem;
    private ArrayList<Fragment> mFragmentList;
    private HomeFragment mHomeFragment;
    private ListFragment mListFragment;
    private DiscoverFragment mDiscoverFragment;
    private PersonalCenterFragment mPersonalFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
        initFragment();
        initNavigationBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 设置Menu菜单显示图标
      */
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

    public static boolean checkPermission(final Activity activity, final String permission){
        if(Build.VERSION.SDK_INT >= 23) {
            int storagePermission = ActivityCompat.checkSelfPermission(activity, permission);
            if (storagePermission != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void showPermissionDialog(final Activity activity, String permission) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity,permission)) {
            ActivityCompat.requestPermissions(activity, new String[]{permission},REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        ActivityCompat.requestPermissions(activity,new String[]{permission},REQUEST_CODE_ASK_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_ASK_PERMISSIONS){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"取得权限",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"未取得权限",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestPermissions() {
        if (!checkPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setMessage("为了正常读取视频,需要授权.")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            showPermissionDialog(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
                        }
                    })
                    .show();
        }
    }

    private void initFragment() {
        mHomeFragment = HomeFragment.newInstance("", "");
        mListFragment = ListFragment.newInstance("");
        mDiscoverFragment = DiscoverFragment.newInstance("");
        mPersonalFragment = PersonalCenterFragment.newInstance("");
        mFragmentList = new ArrayList<>();
        mFragmentList.add(mHomeFragment);
        mFragmentList.add(mListFragment);
        mFragmentList.add(mDiscoverFragment);
        mFragmentList.add(mPersonalFragment);
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        fragmentTransaction.add(mHomeFragment,"home");
//        fragmentTransaction.add(mListFragment,"list");
//        fragmentTransaction.add(mDiscoverFragment,"discover");
//        fragmentTransaction.add(mPersonalFragment,"me");
//        fragmentTransaction.show(mHomeFragment);
//        fragmentTransaction.hide(mListFragment);
//        fragmentTransaction.hide(mDiscoverFragment);
        fragmentTransaction.replace(R.id.layout_frame, mHomeFragment);
        fragmentTransaction.commit();
    }

    private void initNavigationBar() {
        mNavigationBar =  findViewById(R.id.bottom_navigation_bar);
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
    public void onHomeFragmentClick() {

    }

    @Override
    public void onListFragmentClick() {

    }

    @Override
    public void onDiscoverFragmentClick() {

    }

    @Override
    public void onPersonalFragmentClick() {

    }
}
