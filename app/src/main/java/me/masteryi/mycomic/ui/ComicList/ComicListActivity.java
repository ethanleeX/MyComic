package me.masteryi.mycomic.ui.ComicList;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseToolbarActivity;
import me.masteryi.mycomic.constant.IntentExtraKey;
import me.masteryi.mycomic.databinding.ActivityComicListBinding;

/**
 * @author master.yi
 * @date 2016/12/18
 * @blog masteryi.me
 */
public class ComicListActivity extends BaseToolbarActivity {
    private ActivityComicListBinding mBinding;
    private String mUrl;
    private String mName;

    @Override
    protected void getExtraData () {
        mUrl = getIntent().getStringExtra(IntentExtraKey.URL);
        mName = getIntent().getStringExtra(IntentExtraKey.NAME);
    }

    @Override
    protected void initContentView () {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_comic_list);
    }

    @Override
    protected void setToolbar () {
        mToolbar = mBinding.toolbar.toolbar;
    }

    @Override
    protected void initView () {
        super.initView();

        setTitle(mName);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.content, ComicListFragment.newInstance(mUrl));
        ft.commit();
    }
}
