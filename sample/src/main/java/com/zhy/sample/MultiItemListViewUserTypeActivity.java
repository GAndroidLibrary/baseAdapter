package com.zhy.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.sample.adapter.lvtype.ChatAdapterViewType;
import com.zhy.sample.bean.ChatMessage;

/**
 * *
 * *
 * Project_Name:baseAdapter
 *
 * @author zhangxc
 * @date 2019/2/22 3:41 PM
 * *
 */
public class MultiItemListViewUserTypeActivity extends AppCompatActivity {


    private ListView mListView;
    private CommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mListView = (ListView) findViewById(R.id.id_listview_list);
        mListView.setDivider(null);
        mListView.setAdapter(new ChatAdapterViewType(this, ChatMessage.MOCK_DATAS_ViewType));

    }

}

