package me.masteryi.mycomic.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author master.yi
 * @date 2016/12/11
 * @blog masteryi.me
 */

public abstract class ViewHolderBinder extends RecyclerView.ViewHolder {
    public ViewHolderBinder (View itemView) {
        super(itemView);
    }

    public abstract void onBindViewHolder (int position);
}
