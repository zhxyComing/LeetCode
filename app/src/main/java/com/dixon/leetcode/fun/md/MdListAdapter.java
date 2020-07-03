package com.dixon.leetcode.fun.md;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dixon.leetcode.R;
import com.dixon.leetcode.core.bean.MdData;

import java.util.List;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.03
 * Functional desc: MD列表适配器
 */
public class MdListAdapter extends BaseAdapter {

    private List<MdData> mdDataList;
    private Context context;

    public MdListAdapter(List<MdData> mdDataList, Context context) {
        this.mdDataList = mdDataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mdDataList == null ? 0 : mdDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_md, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        MdData mdData = mdDataList.get(position);
        vh.titleView.setText(mdData.getName());
        return convertView;
    }

    private static final class ViewHolder {

        private TextView titleView;

        public ViewHolder(View convertView) {
            titleView = convertView.findViewById(R.id.tv_md_title);
        }
    }
}
