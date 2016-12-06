package me.masteryi.mycomic.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseActivity;
import me.masteryi.mycomic.databinding.ActivityMainBinding;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView () {
        setupTagLayout();
        setupNavView();
        setupDrawer();
    }

    @Override
    protected void initContentView () {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void setToolbar () {
        mToolbar = mBinding.toolbar;
    }

    private void initViewPagerAdapter () {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(this,
            getSupportFragmentManager());
        mBinding.viewPager.setAdapter(mainViewPagerAdapter);
    }

    private void setupTagLayout () {
        initViewPagerAdapter();
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
    }

    private void setupDrawer () {
        mDrawerLayout = mBinding.drawerLayout;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,
            R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void setupNavView () {
        mBinding.navView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected (@NonNull MenuItem item) {
                    {
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
                    }
                }
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
