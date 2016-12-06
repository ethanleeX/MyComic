package me.masteryi.mycomic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentView();
        initToolbar();
        initView();
    }

    protected void initToolbar () {
        setToolbar();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    abstract protected void initView ();

    abstract protected void initContentView ();

    abstract protected void setToolbar ();

    @Override
    protected void onDestroy () {
        super.onDestroy();
    }
}
