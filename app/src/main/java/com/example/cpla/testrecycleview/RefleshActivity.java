package com.example.cpla.testrecycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.cpla.testrecycleview.adapter.SimpleAdapter;
import com.example.cpla.testrecycleview.bean.ManuObject;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RefleshActivity extends AppCompatActivity {

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
        reflesh_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new SimpleAdapter(RefleshActivity.this, mManuList);
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
                adapter = new SimpleAdapter(RefleshActivity.this, mManuList);
                reflesh_rv.setAdapter(adapter);
                refreshlayout.setNoMoreData(false);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
//                adapter.setDataItems();

                adapter.setDataItems(getMoreData());

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

    private List<ManuObject> getMoreData(){
        List<ManuObject> objectList = new ArrayList<>();
        for(int i = mManuList.size(); i < mManuList.size()+6; i++){
            ManuObject obj = new ManuObject(i, "bamboo&&_"+i);
            if(i%2 == 0){
                obj.picUrl = "http://m.qpic.cn/psb?/V11dVhE81rDMcu/1omDBxpZEKq6IEpXyUO8H4N*YmVxPSF6vy6T05B7CO0!/b/dBKmpFedDwAA&bo=VQOAAgAAAAABF.Q!&rf=viewer_4";
            } else {
                obj.picUrl = "http://m.qpic.cn/psb?/V11dVhE81rDMcu/VqCS2I6kULOatlCpQJDIOHCQjnVrQl8TgnG78gMkrw0!/b/dJ3itk5YIgAA&bo=VQOAAgAAAAABF.Q!&rf=viewer_4";
            }
            objectList.add(obj);
        }
        Log.e("zhenzhen", objectList.size()+"");
        return objectList;
    }

    private void initView(){
        reflesh_rv = findViewById(R.id.reflesh_rv);
    }
}
