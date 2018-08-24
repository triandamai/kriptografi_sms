package com.ebook.trian.kripto.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebook.trian.kripto.Daftar_inbox;
import com.ebook.trian.kripto.Detail_pesan;
import com.ebook.trian.kripto.Model.SmsModel;
import com.ebook.trian.kripto.R;

import java.util.ArrayList;

public class SmsAdapter extends BaseAdapter {
    ArrayList<SmsModel> smsModelArrayList;
    Context context;
    private static LayoutInflater inflater = null;

    public SmsAdapter(Daftar_inbox mainActivity, ArrayList<SmsModel> smsModelArrayList){
        context = mainActivity;
        this.smsModelArrayList = smsModelArrayList;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return smsModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class Holder{
        TextView tv_mobile;
        TextView tv_message;
        RelativeLayout parent;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder=new Holder();
        View view;
        final SmsModel item = smsModelArrayList.get(position);
        view = inflater.inflate(R.layout.layout_sms_item, null);

        holder.parent = (RelativeLayout) view.findViewById(R.id.parent_baca);
        holder.tv_mobile = (TextView) view.findViewById(R.id.tv_mobile);
        holder.tv_message = (TextView) view.findViewById(R.id.tv_message);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, Detail_pesan.class).putExtra("no",item.getMobile()).putExtra("pesan",item.getMessage()));

            }
        });
        holder.tv_mobile.setText(item.getMobile());
        holder.tv_message.setText(item.getMessage());



        return view;
    }
}
