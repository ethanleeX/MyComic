package me.masteryi.mycomic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.masteryi.mycomic.utils.ActivityUtil;

/**
 * @author master.yi
 * @date 2016/11/28
 * @blog masteryi.me
 */

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView {
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container,
                              @Nullable Bundle savedInstanceState) {
        return inflateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initPresenter();
        initData();
    }

    @Override
    public void showErrorMsg (Throwable t) {
        t.printStackTrace();
        ActivityUtil.showToast(getContext(), t.getMessage());
    }

    abstract protected View inflateView (LayoutInflater inflater, @Nullable ViewGroup container,
                                         @Nullable Bundle savedInstanceState);

    protected void initView () {

    }

    protected void initData () {

    }

    protected void initPresenter () {

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
