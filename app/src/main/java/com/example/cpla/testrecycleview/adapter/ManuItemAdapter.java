package com.example.cpla.testrecycleview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cpla.testrecycleview.R;
import com.example.cpla.testrecycleview.bean.ManuObject;

import java.util.List;

/**
 * 菜单适配器
 */
public class ManuItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ManuObject> manuList;

    private OnManuClickListener onClickListener;

    // 用于外部调用
    public void setOnClickListener(OnManuClickListener onClickListener){
        if(onClickListener!= null){
            this.onClickListener = onClickListener;
        }
    }

    public ManuItemAdapter(List<ManuObject> manuList){
        this.manuList = manuList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_manu, viewGroup, false);
        return new ManuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ManuObject obj = manuList.get(i);
        ((ManuHolder)viewHolder).tv_manu_name.setText(obj.content);
        viewHolder.itemView.setOnClickListener(new ManuOnClick(viewHolder.itemView, obj));
    }

    @Override
    public int getItemCount() {
        return manuList.size();
    }

    class ManuHolder extends RecyclerView.ViewHolder{
        public TextView tv_manu_name ;
        public ManuHolder(@NonNull View itemView) {
            super(itemView);
            tv_manu_name = itemView.findViewById(R.id.tv_manu_name);
        }
    }

    class ManuOnClick implements View.OnClickListener {
        Object obj;
        public ManuOnClick(View view, Object obj){
            this.obj = obj;
        }

        @Override
        public void onClick(View v) { // 回调方法给外部实现
            onClickListener.onClick(v, obj);
        }
    }

    /**
     * 用于外部调用
     */
    public interface OnManuClickListener{
        void onClick(View view, Object obj);
    }

}
