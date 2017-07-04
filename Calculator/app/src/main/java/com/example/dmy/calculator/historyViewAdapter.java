package com.example.dmy.calculator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by dmy on 2017/6/3.
 */

public class historyViewAdapter extends RecyclerView.Adapter<historyViewAdapter.ViewHolder> implements View.OnClickListener{

    private List<String> historyList;


    private OnItemClickListener mOnItemClickListener=null;



    public historyViewAdapter (List<String> historyList){
        this.historyList = historyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);


        return holder;



    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String history = historyList.get(position);
        holder.historyView.setText(history);

        holder.itemView.setTag(position);
    }




    @Override
    public int getItemCount() {
        return historyList.size();
    }




    @Override
    public void onClick(View v) {
        if(mOnItemClickListener!=null)
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
    }




    public void setOnItemClickListener(OnItemClickListener listener) {      //点击事件
        this.mOnItemClickListener = listener;
    }

    //define interface
    public static interface OnItemClickListener {                               //点击事件
        void onItemClick(View view , int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView historyView;

        public ViewHolder(View itemView) {
            super(itemView);


            historyView=(TextView)itemView.findViewById(R.id.history);

        }




    }
}
