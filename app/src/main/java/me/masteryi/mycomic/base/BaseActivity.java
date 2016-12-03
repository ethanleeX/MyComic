package me.masteryi.mycomic.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.masteryi.mycomic.R;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */

public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    protected void initToolbar () {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    abstract protected int getContentViewId ();

    abstract protected void initView ();
}
