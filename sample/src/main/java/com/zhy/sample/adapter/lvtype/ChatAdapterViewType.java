package com.zhy.sample.adapter.lvtype;

import android.content.Context;

import com.zhy.adapter.abslistview.MultiItemTypeAdapter;
import com.zhy.sample.bean.ChatMessage;

import java.util.List;

/**
 * Created by zhy on 15/9/4.
 */
public class ChatAdapterViewType extends MultiItemTypeAdapter<ChatMessage>
{
    public ChatAdapterViewType(Context context, List<ChatMessage> datas)
    {
        super(context, datas);

        addItemViewDelegate(new MsgComingItemDelagate1());
        addItemViewDelegate(new MsgSendItemDelagate1());
        addItemViewDelegate(new MsgSendItemEndDelagate());
        addItemViewDelegate(new MsgComingItemEndDelagate());
    }

}
