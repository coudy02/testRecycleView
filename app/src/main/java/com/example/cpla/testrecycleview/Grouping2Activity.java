package com.example.cpla.testrecycleview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.cpla.testrecycleview.adapter.GroupObjectAdapter;
import com.example.cpla.testrecycleview.adapter.LinearRecyclerAdapter;
import com.example.cpla.testrecycleview.bean.GradeThree;
import com.example.cpla.testrecycleview.bean.Student;
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
public class Grouping2Activity extends AppCompatActivity {

    private PinnedHeaderRecyclerView mRecyclerView;
    private Context mContext;
    private List<GradeThree> gList;
    private GroupObjectAdapter adapter;

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

        for(GradeThree g:gList){
            Log.e("zhenzhen", g.classId+"--"+g.sList.size());
        }


        adapter = new GroupObjectAdapter(gList);
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
                List<GradeThree> addList = addData();
//                gList.addAll(addList);
                adapter.setData(addList);
//                if(gList.size() > 50){
//                    refreshlayout.setNoMoreData(true);
//                }

            }
        });
    }

    private List<GradeThree> obtainData() {
        List<GradeThree> list = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            ArrayList<Student> sList = new ArrayList<>();
            for(int j = 0; j < i + 1; j++){
                Student s = new Student(j, "padd_"+j);
                sList.add(s);
            }
            GradeThree g = new GradeThree(i, sList, true);
            list.add(g);
        }
        return list;
    }

    private List<GradeThree> addData(){
        List<GradeThree> list = new ArrayList<>();
        for(int i = gList.size(); i < gList.size() + 6; i++){
            ArrayList<Student> sList = new ArrayList<>();
            for(int j = 0; j < 3; j++){
                Student s = new Student(j, "Fifi_"+j);
                sList.add(s);
            }
            GradeThree g = new GradeThree(i, sList, true);
            list.add(g);
        }
        return list;
    }
}
