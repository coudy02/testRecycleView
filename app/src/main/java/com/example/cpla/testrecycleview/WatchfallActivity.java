package com.example.cpla.testrecycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.example.cpla.testrecycleview.adapter.SimpleAdapter;
import com.example.cpla.testrecycleview.adapter.WaterfallAdapter;
import com.example.cpla.testrecycleview.bean.ManuObject;
import com.example.cpla.testrecycleview.widget.StaggeredDividerItemDecoration;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class WatchfallActivity extends AppCompatActivity {

    private RecyclerView reflesh_rv;

    private List<ManuObject> mManuList;

    private WaterfallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflesh);

        initRefreshLayout();
        initView();
        mManuList = initData();
        Log.e("zhenzhen", mManuList.size()+"");
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        adapter = new WaterfallAdapter(WatchfallActivity.this, mManuList);

        reflesh_rv.setLayoutManager(layoutManager);
        reflesh_rv.setAdapter(adapter);
        reflesh_rv.addItemDecoration(new StaggeredDividerItemDecoration(this, 10));
        reflesh_rv.setItemAnimator(null);

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
            if(i%2==0){
                m1.picUrl = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1960816299,803825902&fm=27&gp=0.jpg";
            } else {
                m1.picUrl = "http://m.qpic.cn/psb?/V11dVhE81rDMcu/JuD*z9adb8HvR15b*mkxb4gN0YMyynio6OrEF2u6YjY!/b/dEghFVfQDwAA&bo=VQOAAgAAAAABF.Q!&rf=viewer_4";
            }
            list.add(m1);
        }
        return list;
    }

    private void initView(){
        reflesh_rv = findViewById(R.id.reflesh_rv);
    }
}
