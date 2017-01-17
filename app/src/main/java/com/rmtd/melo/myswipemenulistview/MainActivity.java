package com.rmtd.melo.myswipemenulistview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> mList;
    SwipeMenuListView mListView;
    MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (SwipeMenuListView) findViewById(R.id.listview);
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("count" + i + "");
        }

        mMyAdapter = new MyAdapter(this, mList);
        mListView.setAdapter(mMyAdapter);

        //1.item显示按钮构造器  (此处只设置两个按钮，当然可以设置多个)
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                createMenu(menu);
            }
        };
        //2.set 构造器
        mListView.setMenuCreator(creator);
        //3.set  按钮的监听事件
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        //置顶
                        String s = mList.get(position);
                        mList.remove(position);
                        mList.add(0, s);
                        mMyAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        //删除
                        mList.remove(position);
                        mMyAdapter.notifyDataSetChanged();
                        break;
                }

            }
        });

    }

    private void createMenu(SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(this);
        item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xE0,
                0x3F)));
        item1.setWidth(DensityUtils.dp2px(this, 50));
        item1.setHeight(DensityUtils.dp2px(this, 50));
        item1.setIcon(R.mipmap.ic_action_important);
        menu.addMenuItem(item1);
        SwipeMenuItem item2 = new SwipeMenuItem(
                getApplicationContext());
        item2.setBackground(new ColorDrawable(Color.rgb(0xF9,
                0x3F, 0x25)));
        item2.setWidth(DensityUtils.dp2px(this, 50));
        item2.setHeight(DensityUtils.dp2px(this, 50));
        item2.setIcon(R.mipmap.ic_action_discard);
        menu.addMenuItem(item2);

    }
}
