package me.masteryi.mycomic.ui.category;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseFragment;
import me.masteryi.mycomic.beans.Category;
import me.masteryi.mycomic.databinding.FragmentCategoryBinding;

/**
 * 分类
 *
 * @author master.yi
 * @date 2017/1/4
 * @blog masteryi.me
 */
public class CategoryFragment extends BaseFragment {
    private FragmentCategoryBinding mBinding;
    private List<Category> mCategories;
    private CategoryAdapter mCategoryAdapter;
    private GridLayoutManager mLayoutManager;
    private String[] mCategoryNames;
    private String[] mCategoryUrls;
    private int[] mCategoryDrawables = new int[] {
        R.drawable.category_quanbu, R.drawable.category_shaonianrexue,
        R.drawable.category_wuxiagedou, R.drawable.category_kehuanmohuan,
        R.drawable.category_kejingjitiyu, R.drawable.category_baoxiaoxiju,
        R.drawable.category_zhentantuili, R.drawable.category_kongbulingyi,
        R.drawable.category_shaonvaiqing, R.drawable.category_lianaishenghuo
    };

    public CategoryFragment () {
    }

    @Override
    protected View inflateView (LayoutInflater inflater, @Nullable ViewGroup container,
                                @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView () {
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        mCategoryAdapter = new CategoryAdapter(getContext());
        mBinding.recyclerView.setAdapter(mCategoryAdapter);
        mBinding.recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void initData () {
        initCategory();
        mCategoryAdapter.update(mCategories);
    }

    /**
     * 初始化分类
     * 因为网站上没有图片这里直接写死
     *
     * @return
     */
    private void initCategory () {
        mCategories = new ArrayList<>();
        mCategoryNames = getResources().getStringArray(R.array.category_name);
        mCategoryUrls = getResources().getStringArray(R.array.category_url);

        for(int i = 0; i < mCategoryNames.length; i++) {
            mCategories.add(
                new Category(mCategoryNames[i], mCategoryUrls[i], mCategoryDrawables[i]));
        }
    }
}
