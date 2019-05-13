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
import com.bumptech.glide.request.RequestOptions;
import com.example.cpla.testrecycleview.R;
import com.example.cpla.testrecycleview.bean.ManuObject;

import java.util.ArrayList;
import java.util.List;

public class WaterfallAdapter extends RecyclerView.Adapter {

    List<ManuObject> mList;
    Context mContext;

    public WaterfallAdapter(Context context, List<ManuObject> mList){
        this.mList = mList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_waterfall, viewGroup,false);
        return new SimpleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        Log.e("zhenzhen", "--onBindViewHolder--");

        ManuObject obj = mList.get(position);
        SimpleHolder holder = (SimpleHolder) viewHolder;
        holder.tv_manu_name.setText(obj.content);

        if (obj.picUrl == null) {
            //当ViewHolder复用的时候，如果当前返回的图片url为null，为了防止上一个复用的viewHolder图片
            //遗留，要clear并且将图片设置为空。
            Glide.with(mContext).clear(holder.iv_pic);
            holder.iv_pic.setImageDrawable(null);
            holder.iv_pic.setTag(R.id.iv_pic, position);
            return;
        }
        Object tag=holder.iv_pic.getTag(R.id.iv_pic);
        if (tag!=null && (int) tag!= position) {
            //如果tag不是Null,并且同时tag不等于当前的position。
            //说明当前的viewHolder是复用来的
            //Cancel any pending loads Glide may have for the view
            //and free any resources that may have been loaded for the view.
            Glide.with(mContext).clear(holder.iv_pic);
        }
        String url = obj.picUrl;

        RequestOptions options = new RequestOptions();
        options.centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(url )
                .apply(options)
                .into(holder.iv_pic);
        //给ImageView设置唯一标记。
        holder.iv_pic.setTag(R.id.iv_pic, position);


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
