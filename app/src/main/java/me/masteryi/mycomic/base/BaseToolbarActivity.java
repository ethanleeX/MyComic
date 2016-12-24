package me.masteryi.mycomic.base;

import android.support.v7.widget.Toolbar;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 *
 * 有toolbar的activity
 */
public abstract class BaseToolbarActivity<T extends BasePresenter> extends BaseActivity<T>
    implements IBaseView {
    protected Toolbar mToolbar;

    @Override
    protected void initView () {
        initToolbar();
    }

    private void initToolbar () {
        setToolbar();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    abstract protected void setToolbar ();
}
