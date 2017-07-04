package com.example.dmy.forward_sms;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dmy on 2017/5/19.
 */

public class smsAdapter extends RecyclerView.Adapter<smsAdapter.ViewHolder> {

    private List<SMS> mSmsList;


    public smsAdapter(List<SMS> smsList){

        mSmsList=smsList;

    }


    @Override
    public smsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_item,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(smsAdapter.ViewHolder holder, int position) {

        SMS sms = mSmsList.get(position);
        holder.smsItemTextView.setText(sms.getSms_num()+"\n"+sms.getSms_body());           //设置文字


    }

    @Override
    public int getItemCount() {
        return mSmsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView smsItemTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            smsItemTextView = (TextView)itemView.findViewById(R.id.sms_item);
        }
    }






}
