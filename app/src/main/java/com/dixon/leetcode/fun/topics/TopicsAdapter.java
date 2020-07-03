package com.dixon.leetcode.fun.topics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dixon.descapi.bean.DescData;
import com.dixon.leetcode.R;
import com.dixon.leetcode.core.util.FontUtil;

import java.util.List;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: 题目适配器
 */
public class TopicsAdapter extends BaseAdapter {

    private List<DescData> descList;
    private Context context;

    public TopicsAdapter(List<DescData> descList, Context context) {
        this.descList = descList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return descList == null ? 0 : descList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_topics, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        DescData descData = descList.get(position);
        vh.indexView.setText("题目 " + descData.getIndex());
        vh.titleView.setText(descData.getTopic());
        return convertView;
    }

    private static final class ViewHolder {

        private TextView indexView, titleView;

        public ViewHolder(View convertView) {
            indexView = convertView.findViewById(R.id.tv_topic_index);
            titleView = convertView.findViewById(R.id.tv_topic_title);
            FontUtil.font(convertView);
        }
    }
}
