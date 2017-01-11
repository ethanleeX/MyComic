package me.masteryi.mycomic.ui.recommend;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.orhanobut.logger.Logger;
import me.masteryi.mycomic.R;

/**
 * @author master.yi
 * @date 2017/1/10
 * @blog masteryi.me
 */

public class RecommendItemDecoration extends RecyclerView.ItemDecoration {
    private int mTopSpace;
    private int mLeftSpace;
    private int mRightSpace;
    private int mItemSpace;

    public RecommendItemDecoration (Context context) {
        super();
        mTopSpace = context.getResources().getDimensionPixelSize(R.dimen.margin_micro);
        mLeftSpace = context.getResources().getDimensionPixelSize(R.dimen.margin_small);
        mRightSpace = context.getResources().getDimensionPixelSize(R.dimen.margin_small);
        mItemSpace = context.getResources().getDimensionPixelSize(R.dimen.margin_micro);
    }

    @Override
    public void getItemOffsets (Rect outRect, int itemPosition, RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
    }

    @Override
    public void getItemOffsets (Rect outRect, View view, RecyclerView parent,
                                RecyclerView.State state) {
        switch (parent.getChildAdapterPosition(view) %
                (RecommendFragment.COVER_COUNT_PER_LIST + 1)) {
            case 1:
            case 4:
                outRect.set(mLeftSpace, mTopSpace, 0, 0);
                break;
            case 2:
            case 5:
                outRect.set(mItemSpace, mTopSpace, mItemSpace, 0);
                break;
            case 3:
            case 6:
                outRect.set(0, mTopSpace, mRightSpace, 0);
                break;
        }
    }
}
