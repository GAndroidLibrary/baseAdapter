package com.zhy.adapter.abslistview;

import android.content.Context;

import com.zhy.adapter.abslistview.base.ItemViewDelegate;

import java.util.List;

/**
 * 通用的Adapter，即非 多cell类型（MultiItemType）的方式。
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {

    public CommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            /**
             *     seachal annotation ： 被调用的过程 MultiItemTypeAdapter convert() ->ItemViewDelegateManager convert()
             *  -> ItemViewDelegate convert()  -> CommonAdapter convert().
             */

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }


    @Override
    protected abstract void convert(ViewHolder viewHolder, T item, int position);

}
