package com.center.demoapplication.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.center.demoapplication.R;
import com.center.demoapplication.bean.MediaItemBean;
import com.center.demoapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 2019/3/19.
 */

public class LocalVideoAdapter extends BaseAdapter {

    private Context mContext;
    private List<MediaItemBean> list = new ArrayList<>();
    private LayoutInflater inflater;
    private Utils utils;

    public LocalVideoAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
        utils = new Utils();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.item_local_video,null);
            vh = new ViewHolder();
            vh.tvName = convertView.findViewById(R.id.tv_name);
            vh.tvDuration = convertView.findViewById(R.id.tv_duration);
            vh.tvSize = convertView.findViewById(R.id.tv_size);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        MediaItemBean bean = list.get(position);
        vh.tvName.setText(bean.getName());
        vh.tvSize.setText(Formatter.formatFileSize(mContext,bean.getSize()));
        vh.tvDuration.setText(utils.stringForTime((int)bean.getDuration()));
        return convertView;
    }

    static class ViewHolder{
        TextView tvName;
        TextView tvDuration;
        TextView tvSize;
    }

    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }

    public void add(List<MediaItemBean> mlist) {
        list.addAll(mlist);
        notifyDataSetChanged();
    }
    public void remove(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }
}
