package com.softwristband.softwristband;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import com.softwristband.fragment.*;
public class MainActivity extends AppCompatActivity implements MaterialTabListener {

    MaterialTabHost tabHost;
    ViewPager pager;
    ViewPagerAdapter adapter;
    List<Fragment> viewList =new ArrayList<>();
    List<String> tabTitle = new ArrayList<>();
    Fragment ArticleFragment = new ArticleFragment();
    Fragment MusicFragment = new MusicFragment();
    Fragment MovieFragment = new MovieFragment();
    Fragment SentimentFragment = new SentimentFragment();
    Fragment SettingFragment = new SettingFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        init();

    }
    private void init()
    {
        viewList.add(ArticleFragment);
        viewList.add(MusicFragment);
        viewList.add(MovieFragment);
        viewList.add(SentimentFragment);
        viewList.add(SettingFragment);
        tabTitle.add("文 章");
        tabTitle.add("音 乐");
        tabTitle.add("电 影");
        tabTitle.add("情 感");
        tabTitle.add("设 置");

        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        tabHost.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        pager = (ViewPager) this.findViewById(R.id.pager );
        // init view pager
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }
    }
    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabReselected(MaterialTab tab) {}

    @Override
    public void onTabUnselected(MaterialTab tab) {}

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int num) {
            return viewList.get(num);
        }
        @Override
        public int getCount() {
            return viewList.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle.get(position);
        }
    }
}

