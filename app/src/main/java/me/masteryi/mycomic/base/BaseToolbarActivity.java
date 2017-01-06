package me.masteryi.mycomic.base;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    abstract protected void setToolbar ();

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
