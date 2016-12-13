package me.masteryi.mycomic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import me.masteryi.mycomic.utils.ActivityUtil;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
    implements IBaseView {
    protected Toolbar mToolbar;
    protected T mPresenter;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtraData();
        initContentView();
        initToolbar();
        initView();
        initPresenter();
        initData();
    }

    protected void getExtraData () {

    }

    private void initToolbar () {
        setToolbar();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    abstract protected void initView ();

    abstract protected void initContentView ();

    abstract protected void setToolbar ();

    protected void initPresenter () {
    }

    protected void initData () {
    }

    public void showErrorMsg (String msg) {
        ActivityUtil.showToast(this, msg);
    }

    @Override
    protected void onResume () {
        super.onResume();
        if(mPresenter != null) {
            mPresenter.onSubscribe();
        }
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }
}
