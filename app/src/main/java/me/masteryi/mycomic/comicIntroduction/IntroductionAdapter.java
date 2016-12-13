package me.masteryi.mycomic.comicIntroduction;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import me.masteryi.mycomic.BR;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseAdapter;
import me.masteryi.mycomic.base.ViewHolderBinder;
import me.masteryi.mycomic.beans.Chapter;
import me.masteryi.mycomic.databinding.ChapterItemBinding;
import me.masteryi.mycomic.databinding.ChapterItemIntroductionBinding;

/**
 * @author master.yi
 * @date 2016/12/11
 * @blog masteryi.me
 */

class IntroductionAdapter extends BaseAdapter {
    private static final int VIEW_TYPE_TITLE = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    private Context mContext;
    private String mIntroduction;
    private List<Chapter> mChapters = new ArrayList<>();

    IntroductionAdapter (Context context) {
        mContext = context;
    }

    @Override
    public ViewHolderBinder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_TITLE:
                view = LayoutInflater.from(mContext)
                                     .inflate(R.layout.chapter_item_introduction, parent, false);
                return new TitleViewHolder(view);
            case VIEW_TYPE_ITEM:
                view = LayoutInflater.from(mContext).inflate(R.layout.chapter_item, parent, false);
                return new ItemViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount () {
        return mChapters.size() + 1;
    }

    @Override
    public int getItemViewType (int position) {
        return position == 0 ? VIEW_TYPE_TITLE : VIEW_TYPE_ITEM;
    }

    void update (String introduction, List<Chapter> chapters) {
        mIntroduction = introduction;
        mChapters = chapters;
        notifyDataSetChanged();
    }

    private class TitleViewHolder extends ViewHolderBinder {
        ChapterItemIntroductionBinding mBinding;

        TitleViewHolder (View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }

        @Override
        public void onBindViewHolder (int position) {
            mBinding.setVariable(BR.introduction, mIntroduction);
            mBinding.executePendingBindings();

            if(mBinding.textFullText.getLineCount() > 2) {
                //简介大于两行 出现展开收起按钮 并且默认收起
                mBinding.textFullText.setVisibility(View.GONE);
                mBinding.textCollapse.setVisibility(View.VISIBLE);
                mBinding.collapse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick (View view) {
                        if(mBinding.textFullText.getVisibility() == View.VISIBLE) {
                            //当前为展开状态 点击之后收起
                            mBinding.collapse.setText(R.string.full_text);
                            mBinding.textFullText.setVisibility(View.GONE);
                            mBinding.textCollapse.setVisibility(View.VISIBLE);
                        } else {
                            //当前为收起状态 点击之后展开
                            mBinding.collapse.setText(R.string.collapse);
                            mBinding.textFullText.setVisibility(View.VISIBLE);
                            mBinding.textCollapse.setVisibility(View.GONE);
                        }
                    }
                });
            } else {
                //简介小于两行 隐藏展开收起按钮
                mBinding.textCollapse.setVisibility(View.GONE);
                mBinding.textFullText.setVisibility(View.VISIBLE);
                //mBinding.collapse.setVisibility(View.GONE);
            }
        }
    }

    private class ItemViewHolder extends ViewHolderBinder {
        ChapterItemBinding mBinding;

        ItemViewHolder (View view) {
            super(view);
            mBinding = DataBindingUtil.bind(view);
        }

        @Override
        public void onBindViewHolder (int position) {
            mBinding.setVariable(BR.chapter, mChapters.get(position - 1));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View view) {
                    //TODO
                }
            });
            mBinding.executePendingBindings();
        }
    }
}
