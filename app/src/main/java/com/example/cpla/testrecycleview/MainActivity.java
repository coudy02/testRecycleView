package com.example.cpla.testrecycleview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cpla.testrecycleview.adapter.ManuItemAdapter;
import com.example.cpla.testrecycleview.bean.ManuObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ManuObject> mManuList = new ArrayList<>();
    RecyclerView main_rv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mManuList = initData();

        main_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ManuItemAdapter adapter = new ManuItemAdapter(mManuList);

        main_rv.setAdapter(adapter);

        adapter.setOnClickListener(new ManuItemAdapter.OnManuClickListener() {
            @Override
            public void onClick(View view, Object obj) {
//                Toast.makeText(MainActivity.this, (String)obj, Toast.LENGTH_SHORT).show();
                ManuObject object = (ManuObject) obj;
                switch (object.number){
                    case 1:{
                        startActivity(new Intent(MainActivity.this, RefleshActivity.class));
                        break;
                    }
                    case 2:{
                        startActivity(new Intent(MainActivity.this, GroupingActivity.class));
                        break;
                    }
                    case 3:{
                        startActivity(new Intent(MainActivity.this, Grouping2Activity.class)); // 点击组长伸缩
                        break;
                    }
                    case 4:{
                        startActivity(new Intent(MainActivity.this, GroupingActivity.class));
                        break;
                    }
                    case 5:{
                        startActivity(new Intent(MainActivity.this, WatchfallActivity.class));
                        break;
                    }
                    case 6:{
                        startActivity(new Intent(MainActivity.this, GridActivity.class));
                        break;
                    }
                }


            }
        });
    }

    private void initView(){
        main_rv = findViewById(R.id.main_rv);
    }

    /**
     * 目录数据
     */
    private List<ManuObject> initData(){
        List<ManuObject> list = new ArrayList<>();
        ManuObject m1 = new ManuObject(1, "上下刷新");
        list.add(m1);
        ManuObject m5 = new ManuObject(5, "瀑布模式");
        list.add(m5);
        ManuObject m6 = new ManuObject(6, "网格模式");
        list.add(m6);
        ManuObject m2 = new ManuObject(2, "分组，组长悬浮");
        list.add(m2);
        ManuObject m3 = new ManuObject(3, "点击组长伸缩");
        list.add(m3);
//        ManuObject m4 = new ManuObject(4, "组长悬浮");
//        list.add(m4);
        return list;
    }



}
