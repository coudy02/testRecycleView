package com.example.cpla.testrecycleview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cpla.testrecycleview.R;
import com.example.cpla.testrecycleview.bean.ManuObject;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends RecyclerView.Adapter {

    List<ManuObject> mList;
    Context mContext;

    public SimpleAdapter(Context context, List<ManuObject> mList){
        this.mList = mList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_simple, viewGroup,false);
        return new SimpleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ManuObject obj = mList.get(position);
        SimpleHolder holder = (SimpleHolder) viewHolder;
        holder.tv_manu_name.setText(obj.content);
        if(!TextUtils.isEmpty(obj.picUrl)){
            Glide.with(mContext)
                    .load(obj.picUrl)
//                .apply(options)
                    .into(holder.iv_pic);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 更新
     */
    public void reSetItems(List<ManuObject> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     */
    public void setDataItems(){
        List<ManuObject> objectList = new ArrayList<>();
        for(int i = getItemCount(); i < getItemCount()+6; i++){
            ManuObject obj = new ManuObject(i, "bamboo_"+i);
            if(i%2 == 0){
                obj.picUrl = "http://m.qpic.cn/psb?/V11dVhE81rDMcu/1omDBxpZEKq6IEpXyUO8H4N*YmVxPSF6vy6T05B7CO0!/b/dBKmpFedDwAA&bo=VQOAAgAAAAABF.Q!&rf=viewer_4";
            } else {
                obj.picUrl = "http://m.qpic.cn/psb?/V11dVhE81rDMcu/VqCS2I6kULOatlCpQJDIOHCQjnVrQl8TgnG78gMkrw0!/b/dJ3itk5YIgAA&bo=VQOAAgAAAAABF.Q!&rf=viewer_4";
            }
            objectList.add(obj);
        }
        mList.addAll(objectList);
        notifyDataSetChanged();
    }
    /**
     * 加载更多
     */
    public void setDataItems(List<ManuObject> objectList){
        Log.e("zhenzhen", "objectList="+objectList.size());
        mList.addAll(objectList);
        notifyDataSetChanged();
    }

    class SimpleHolder extends RecyclerView.ViewHolder{

        public TextView tv_manu_name;
        public ImageView iv_pic;

        public SimpleHolder(@NonNull View itemView) {
            super(itemView);
            tv_manu_name = itemView.findViewById(R.id.tv_simple_name);
            iv_pic = itemView.findViewById(R.id.iv_pic);
        }
    }

}
