package com.zhy.sample.adapter.lvtype;

import com.zhy.adapter.abslistview.ViewHolder;
import com.zhy.adapter.abslistview.base.ItemViewDelegate;
import com.zhy.sample.R;
import com.zhy.sample.bean.ChatMessage;

/**
 * Created by zhy on 16/6/22.
 */
public class MsgComingItemEndDelagate implements ItemViewDelegate<ChatMessage> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.main_chat_from_msg_end;
    }

    @Override
    public boolean isForViewType(ChatMessage item, int position) {

        return ChatMessage.SEND_MSG ==item.getViewType();
    }

    @Override
    public void convert(ViewHolder holder, ChatMessage chatMessage, int position) {
        holder.setText(R.id.chat_from_content, chatMessage.getContent());
        holder.setText(R.id.chat_from_name, chatMessage.getName());
        holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());
    }
}
