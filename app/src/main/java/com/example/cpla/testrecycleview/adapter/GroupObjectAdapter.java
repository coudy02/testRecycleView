package com.example.cpla.testrecycleview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cpla.testrecycleview.R;
import com.example.cpla.testrecycleview.bean.GradeThree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GroupObjectAdapter extends RecyclerView.Adapter {

    private final int TYPE_CLASS = 1;
    private final int TYPE_STUDENT = 2;

    HashMap<Integer, Integer> indexMap = new HashMap<>();

    List<GradeThree> mList;

    public GroupObjectAdapter(List<GradeThree> list) {
        this.mList = list;
    }

    public void setData(List<GradeThree> list){
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        Log.e("zhenzhen", "type=" + viewType);
        if (viewType == TYPE_CLASS) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group_leader, viewGroup, false);
            return new GroupHolder(view);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group_menber, viewGroup, false);
            return new MemberHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        if(isHead(position)){
            GroupHolder holder = (GroupHolder) viewHolder;
            // 获取班级
            holder.tv_group_name.setText(mList.get(getClassId(position)).classId+"--");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("zhenyi", mList.get(getClassId(position)).classId+"*********");
                }
            });
        } else {
            MemberHolder holder = (MemberHolder) viewHolder;

            // 该学生所在的班级的位置
            int studentIndex = position - indexMap.get(getStudentOfClass(position)) -1 ;
            holder.tv_member_name.setText(mList.get(getStudentOfClass(position)).sList.get(studentIndex).name);
        }
    }
    /**
     * 根据学生的position获取所属的班级
     * @return
     */
    private int getStudentOfClass(int position){
        int tempSize = 0;
        for (int i = 0; i < mList.size(); i++) {
            tempSize += mList.get(i).sList.size() + 1;
            if(position <= tempSize){
                return i;
            }
        }
        return 0;
    }

    @Override
    public int getItemCount() {
       if(mList.size() < 0){
           return 0;
       }
        // 一班多少人
        int classMatecount = 0;
        int index = 0; // 全部显示项的个数
         for(int i = 0; i < mList.size(); i++){
             if(index != 0){
                 index ++;
             }
             indexMap.put(i, new Integer(index));
             Log.e("zhenzhenzhen", "index=index=index="+indexMap.get(i));
             classMatecount = mList.get(i).sList.size(); // 一个班有多少学生
             index = index + classMatecount;
             Log.e("zhenzhenzhen2", "index="+index);
             classMatecount = classMatecount + classMatecount;
         }
        return mList.size() + classMatecount;
    }

    /**
     * 根据position获取当前是第几个班级
     * @param position
     * @return
     */
    private int getClassId(int position){
        int result = 0;
        Iterator<Map.Entry<Integer, Integer>> iterator = indexMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            entry.getKey();
            int value = entry.getValue();
            Log.e("zhenzhenzhenzhen", "value="+value+"--"+position);
            if(value == position){
                result = entry.getKey();
                return result;
            }
        }
        return result;
    }

    /**
     * 获取适配器类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if(isHead(position)){
            return TYPE_CLASS;
        }
        return TYPE_STUDENT;
    }

    private boolean isHead(int position){
        Iterator<Map.Entry<Integer, Integer>> iterator = indexMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            entry.getKey();
            int value = entry.getValue();
            if(value == position){
                return true;
            }
        }
        return false;
    }

    /**
     * 组长信息
     */
    class GroupHolder extends RecyclerView.ViewHolder {

        public TextView tv_group_name;
        public ImageView iv_pic;

        public GroupHolder(@NonNull View itemView) {
            super(itemView);
            tv_group_name = itemView.findViewById(R.id.tv_group_name);
            iv_pic = itemView.findViewById(R.id.iv_pic);
        }
    }

    /**
     * 组员信息
     */
    class MemberHolder extends RecyclerView.ViewHolder {
        public TextView tv_member_name;

        public MemberHolder(@NonNull View itemView) {
            super(itemView);
            tv_member_name = itemView.findViewById(R.id.tv_member_name);
        }
    }
}
