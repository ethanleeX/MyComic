package me.masteryi.mycomic.main;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.bookshelf.BookshelfFragment;
import me.masteryi.mycomic.category.CategoryFragment;
import me.masteryi.mycomic.latestupdate.LatestUpdateFragment;
import me.masteryi.mycomic.range.RankFragment;
import me.masteryi.mycomic.recommend.RecommendFragment;

/**
 * @author master.yi
 * @date 2016/11/26
 * @blog masteryi.me
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    String[] mTitles;

    public MainViewPagerAdapter (Activity activity, FragmentManager fm) {
        super(fm);
        mTitles = activity.getResources().getStringArray(R.array.main_tabs);
    }

    @Override
    public Fragment getItem (int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new RecommendFragment();
                break;
            case 1:
                fragment = new LatestUpdateFragment();
                break;
            case 2:
                fragment = new CategoryFragment();
                break;
            case 3:
                fragment = new RankFragment();
                break;
            case 4:
                fragment = new BookshelfFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount () {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle (int position) {
        return mTitles[position];
    }

    //public void addFragment (Fragment fragment, String title) {
    //    mFragments.add(fragment);
    //    mTitles.add(title);
    //}
}
