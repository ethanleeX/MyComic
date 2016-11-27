package me.masteryi.mycomic.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author master.yi
 * @date 2016/11/26
 * @blog masteryi.me
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments = new ArrayList<>(5);
    private List<String> mTitles = new ArrayList<>(5);

    public MainViewPagerAdapter (FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem (int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount () {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle (int position) {
        return mTitles.get(position);
    }

    public void addFragment (Fragment fragment, String title) {
        mFragments.add(fragment);
        mTitles.add(title);
    }
}
