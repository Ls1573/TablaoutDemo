package com.ls.tablayoutdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.ls.tablayoutdemo.widget.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private SlidingTabLayout tab;
    private ViewPager vp;

    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private VpAdapter adapter;
    private String[] titles = {"第一页", "第二页"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tab = (SlidingTabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        adapter = new VpAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        tab.setViewPager(vp);

    }

    @Override
    protected void onResume() {
        super.onResume();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        tab.setTabWidth(screenWidth / 2);
        tab.notifyDataSetChanged();
    }

    public class VpAdapter extends FragmentPagerAdapter {

        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (fragmentOne == null) {
                        fragmentOne = new FragmentOne();
                        return fragmentOne;
                    }
                    break;
                case 1:
                    if (fragmentTwo == null) {
                        fragmentTwo = new FragmentTwo();
                        return fragmentTwo;
                    }
                    break;
                default:
                    return null;
            }

            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
