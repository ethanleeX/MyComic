package me.masteryi.mycomic.ui.main;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.ui.bookshelf.BookshelfFragment;
import me.masteryi.mycomic.ui.category.CategoryFragment;
import me.masteryi.mycomic.ui.range.RangeFragment;
import me.masteryi.mycomic.ui.recent.RecentFragment;
import me.masteryi.mycomic.ui.recommend.RecommendFragment;

/**
 * @author master.yi
 * @date 2016/11/26
 * @blog masteryi.me
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles;

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
                fragment = new RecentFragment();
                break;
            case 2:
                fragment = new CategoryFragment();
                break;
            case 3:
                fragment = new RangeFragment();
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
}
