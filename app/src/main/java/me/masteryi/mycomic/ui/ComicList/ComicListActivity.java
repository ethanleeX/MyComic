package me.masteryi.mycomic.ui.ComicList;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseToolbarActivity;
import me.masteryi.mycomic.databinding.ActivityComicListBinding;

public class ComicListActivity extends BaseToolbarActivity {
    public static final String NAME = "name";
    private ActivityComicListBinding mBinding;
    private String mUrl;
    private String mName;

    @Override
    protected void getExtraData () {
        mUrl = getIntent().getStringExtra(ComicListFragment.URL);
        mName = getIntent().getStringExtra(NAME);
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
