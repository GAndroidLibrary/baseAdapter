package com.zhy.adapter.abslistview.base;

import android.support.v4.util.SparseArrayCompat;

import com.zhy.adapter.abslistview.ViewHolder;


/**
 * sechal annotation:
 * 参考研磨设计模式，
 * ItemViewDelegateManager更像是一个一个代理， 符合代理的特点:实现与具体的目标对象一样的接口，
 * 这样就可以使用代理来代替具体的目标对象。保存一个指向具体目标对象的引用，可以在需要的时候调用具体的目标对象。
 * 可以控制对具体目标对象的访问，并可以负责创建和删除它。
 * <p>
 * ItemViewDelegateManager 持有ItemViewDelegate
 * <p>
 * ItemViewDelegate 更像是一个目标接口，定义具体的目标对象
 * <p>
 *
 * <p>
 * <p>
 * Created by zhy on 16/6/22.
 */
public class ItemViewDelegateManager<T> {
    SparseArrayCompat<ItemViewDelegate<T>> delegates = new SparseArrayCompat();

    public int getItemViewDelegateCount() {
        return delegates.size();
    }


    public ItemViewDelegateManager<T> addDelegate(ItemViewDelegate<T> delegate) {
        //        sechal annotation: viewType从0开始
        int viewType = delegates.size();
        if (delegate != null) {
            delegates.put(viewType, delegate);
            viewType++;
        }
        return this;
    }

    public ItemViewDelegateManager<T> addDelegate(int viewType, ItemViewDelegate<T> delegate) {
        if (delegates.get(viewType) != null) {
            //           sechal annotation:    如果delegates里面有值就抛出异常
            throw new IllegalArgumentException(
                    "An ItemViewDelegate is already registered for the viewType = "
                            + viewType
                            + ". Already registered ItemViewDelegate is "
                            + delegates.get(viewType));
        }
        delegates.put(viewType, delegate);
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(ItemViewDelegate<T> delegate) {
        if (delegate == null) {
            throw new NullPointerException("ItemViewDelegate is null");
        }
        int indexToRemove = delegates.indexOfValue(delegate);

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public ItemViewDelegateManager<T> removeDelegate(int itemType) {
        int indexToRemove = delegates.indexOfKey(itemType);

        if (indexToRemove >= 0) {
            delegates.removeAt(indexToRemove);
        }
        return this;
    }

    public int getItemViewType(T item, int position) {
        int delegatesCount = delegates.size();
        //          sechal annotation:  根据索引从大向小。
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);
            //             seachal annotation:  如果代理item 需要的ViewType和delegate的ViewType匹配，则返回此 viewType值（也就是SparseArrayCompat key）
            if (delegate.isForViewType(item, position)) {
                //          seachal annotation: 如果return语句行不被执行，就会抛出不数据与代理不匹配异常，
                return delegates.keyAt(i);
            }
        }
        //
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public void convert(ViewHolder holder, T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = 0; i < delegatesCount; i++) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);

            if (delegate.isForViewType(item, position)) {
                delegate.convert(holder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }


    public int getItemViewLayoutId(int viewType) {
        return delegates.get(viewType).getItemViewLayoutId();
    }

    public int getItemViewType(ItemViewDelegate itemViewDelegate) {
        return delegates.indexOfValue(itemViewDelegate);
    }

    public ItemViewDelegate getItemViewDelegate(T item, int position) {
        int delegatesCount = delegates.size();
        for (int i = delegatesCount - 1; i >= 0; i--) {
            ItemViewDelegate<T> delegate = delegates.valueAt(i);
            if (delegate.isForViewType(item, position)) {
                return delegate;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }

    public int getItemViewLayoutId(T item, int position) {
        return getItemViewDelegate(item, position).getItemViewLayoutId();
    }
}
