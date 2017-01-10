package me.masteryi.mycomic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import me.masteryi.mycomic.utils.ActivityUtil;

/**
 * @author master.yi
 * @date 2016/12/18
 * @blog masteryi.me
 *
 * 没有toolbar的activity
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity
    implements IBaseView {
    protected T mPresenter;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtraData();
        initContentView();
        initView();
        initPresenter();
        initData();
    }

    protected void getExtraData () {

    }

    abstract protected void initView ();

    abstract protected void initContentView ();

    protected void initPresenter () {
    }

    protected void initData () {
    }

    public void showErrorMsg (Throwable t) {
        t.printStackTrace();
        ActivityUtil.showToast(this, t.getMessage());
    }

    protected void showMessage (String message) {
        ActivityUtil.showToast(this, message);
    }

    protected void showMessage (int resId) {
        ActivityUtil.showToast(this, getString(resId));
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.unSubscribe();
        }
    }
}

