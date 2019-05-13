package com.example.cpla.testrecycleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.cpla.testrecycleview.adapter.SimpleAdapter;
import com.example.cpla.testrecycleview.bean.ManuObject;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class Grid2Activity extends AppCompatActivity {

    private RecyclerView reflesh_rv;

    private List<ManuObject> mManuList;

    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflesh);
        initRefreshLayout();
        initView();
        mManuList = initData();
        Log.e("zhenzhen", mManuList.size()+"");
        reflesh_rv.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new SimpleAdapter(Grid2Activity.this, mManuList);
        reflesh_rv.setAdapter(adapter);
    }

    private void initRefreshLayout() {
        RefreshLayout refreshLayout = findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                mManuList.clear();
                mManuList = initData();
                adapter.reSetItems(mManuList);
                refreshlayout.setNoMoreData(false);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                adapter.setDataItems();
                if(mManuList.size() > 30){
                    refreshlayout.setNoMoreData(true);
                }

            }
        });
    }

    /**
     * 目录数据
     */
    private List<ManuObject> initData(){
        List<ManuObject> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            ManuObject m1 = new ManuObject(i, "panda_"+i);
            list.add(m1);
        }
        return list;
    }

    private void initView(){
        reflesh_rv = findViewById(R.id.reflesh_rv);
    }

}
