package me.masteryi.mycomic.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import butterknife.BindView;
import me.masteryi.mycomic.base.BaseActivity;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.recommend.RecommendFragment;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId () {
        return R.layout.activity_main;
    }

    @Override
    protected void initView () {
        setupTagLayout();
        setupNavView();
        setupDrawer();
    }

    private void initViewPagerAdapter () {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(
            getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new RecommendFragment(), "推荐");
        mainViewPagerAdapter.addFragment(new RecommendFragment(), "最近更新");
        mainViewPagerAdapter.addFragment(new RecommendFragment(), "分类");
        mainViewPagerAdapter.addFragment(new RecommendFragment(), "风云榜");
        mainViewPagerAdapter.addFragment(new RecommendFragment(), "书架");
        mViewPager.setAdapter(mainViewPagerAdapter);
    }

    private void setupTagLayout () {
        initViewPagerAdapter();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupDrawer () {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                                                                 R.string.open_drawer,
                                                                 R.string.close_drawer);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mToolbar.setNavigationOnClickListener(
            view -> mDrawerLayout.openDrawer(GravityCompat.START));
    }

    private void setupNavView () {
        mNavView.setNavigationItemSelectedListener(item -> {
            //TODO
            switch (item.getItemId()) {
                case R.id.home:
                    break;
                case R.id.theme:
                    break;
                case R.id.setting:
                    break;
                case R.id.about:
                    break;
                default:
                    return false;
            }
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public void onBackPressed () {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
