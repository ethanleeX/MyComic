package me.masteryi.mycomic.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * @author master.yi
 * @date 2016/12/11
 * @blog masteryi.me
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<ViewHolderBinder> {
    protected Context mContext;

    public BaseAdapter (Context context) {
        mContext = context;
    }

    @Override
    public void onBindViewHolder (ViewHolderBinder holder, int position) {
        holder.onBindViewHolder(position);
    }
}
