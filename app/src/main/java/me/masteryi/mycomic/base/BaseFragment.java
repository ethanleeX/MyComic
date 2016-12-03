package me.masteryi.mycomic.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import me.masteryi.mycomic.utils.ActivityUtil;

/**
 * @author master.yi
 * @date 2016/11/28
 * @blog masteryi.me
 */

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment
    implements IBaseView<T> {
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentId(), container, false);
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
        initData();
    }

    @Override
    public void showErrorMsg (String msg) {
        ActivityUtil.showToast(getContext(), msg);
    }

    abstract protected int getContentId ();

    protected void initView () {

    }

    protected void initData () {

    }

    @Override
    public void setPresenter (@NonNull T presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume () {
        super.onResume();
        mPresenter.onSubscribe();
    }

    @Override
    public void onPause () {
        super.onPause();
        mPresenter.unSubscribe();
    }
}
