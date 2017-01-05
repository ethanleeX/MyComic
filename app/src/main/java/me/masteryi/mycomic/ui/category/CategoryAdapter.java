package me.masteryi.mycomic.ui.category;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import me.masteryi.mycomic.R;
import me.masteryi.mycomic.base.BaseAdapter;
import me.masteryi.mycomic.base.ViewHolderBinder;
import me.masteryi.mycomic.beans.Category;
import me.masteryi.mycomic.databinding.CategoryItemBinding;

/**
 * @author master.yi
 * @date 2017/1/4
 * @blog masteryi.me
 */

public class CategoryAdapter extends BaseAdapter {
    private List<Category> mCategories;

    public CategoryAdapter (Context context) {
        super(context);
    }

    @Override
    public ViewHolderBinder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount () {
        return mCategories.size();
    }

    class ItemViewHolder extends ViewHolderBinder {
        CategoryItemBinding mItemBinding;

        public ItemViewHolder (View itemView) {
            super(itemView);
            mItemBinding = CategoryItemBinding.bind(itemView);
        }

        @Override
        public void onBindViewHolder (int position) {
            mItemBinding.setCategory(mCategories.get(position));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick (View v) {
                    // TODO: 2017/1/4 跳转到列表页
                }
            });

            Uri uri = Uri.parse(
                "res://" + mContext.getPackageName() + "/" + mCategories.get(position).getResId());
            mItemBinding.image.setImageURI(uri);
        }
    }

    public void update (List<Category> categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }
}
