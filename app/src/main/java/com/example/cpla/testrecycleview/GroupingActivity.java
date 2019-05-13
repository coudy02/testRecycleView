package com.example.cpla.testrecycleview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.cpla.testrecycleview.adapter.LinearRecyclerAdapter;
import com.example.cpla.testrecycleview.widget.PinnedHeaderItemDecoration;
import com.example.cpla.testrecycleview.widget.PinnedHeaderRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * 分组
 */
public class GroupingActivity extends AppCompatActivity {

    private PinnedHeaderRecyclerView mRecyclerView;
    private Context mContext;
    private List<String> gList;
    private LinearRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grouping);
        mContext = this;
        initRefreshLayout();
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_linear);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void initEvent() {
        mRecyclerView.setOnPinnedHeaderClickListener(new PinnedHeaderRecyclerView.OnPinnedHeaderClickListener() {
            @Override
            public void onPinnedHeaderClick(int adapterPosition) {
                Toast.makeText(mContext, "点击了悬浮标题 position = " + adapterPosition, LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        gList = obtainData();
        adapter = new LinearRecyclerAdapter(gList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new PinnedHeaderItemDecoration());
    }

    private void initRefreshLayout() {
        RefreshLayout refreshLayout = findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                gList.clear();
                gList = obtainData();
//                adapter = new LinearRecyclerAdapter(gList);
                adapter.setData(gList);
                refreshlayout.setNoMoreData(false);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                List<String> addList = addData();
                gList.addAll(addList);
                adapter.setData(gList);
                if(gList.size() > 30){
                    refreshlayout.setNoMoreData(true);
                }

            }
        });
    }

    private List<String> obtainData() {
        List<String> list = new ArrayList<>();
        list.add("2016-07-20");
        list.add("萍乡");
        list.add("高安");
        list.add("江西");
        list.add("南昌");
        list.add("2016-07-21");
        list.add("江西");
        list.add("南昌");
        list.add("江西");
        list.add("南昌");
        list.add("2016-07-22");
        list.add("中国");
        list.add("北京");
        list.add("江西");
        list.add("南昌");
        list.add("2016-07-23");
        list.add("辽宁");
        list.add("沈阳");
        list.add("江西");
        list.add("南昌");
        return list;
    }

    private List<String> addData(){
        List<String> list = new ArrayList<>();
        list.add("2018-07-24");
        list.add("辽宁");
        list.add("沈阳");
        list.add("江西");
        list.add("南昌");
        list.add("2018-07-25");
        list.add("辽宁");
        list.add("沈阳");
        list.add("江西");
        list.add("南昌");
        list.add("2018-07-26");
        list.add("辽宁");
        list.add("沈阳");
        list.add("江西");
        list.add("南昌");
        list.add("2018-07-27");
        list.add("辽宁");
        list.add("沈阳");
        list.add("江西");
        list.add("南昌");
        list.add("2018-07-28");
        list.add("辽宁");
        list.add("沈阳");
        list.add("江西");
        list.add("南昌");
        return list;
    }






}
