package com.dixon.leetcode.fun.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dixon.descapi.bean.DescData;
import com.dixon.leetcode.R;

import java.util.List;

import io.github.kbiakov.codeview.CodeView;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: 方法适配器
 */
public class MethodAdapter extends BaseAdapter {

    private List<DescData.Method> descList;
    private Context context;

    public MethodAdapter(List<DescData.Method> descList, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_method, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        DescData.Method method = descList.get(position);
        vh.indexView.setText(String.valueOf(method.getIndex()));
        vh.descView.setText(method.getExplanation());
        vh.timeOView.setText("O(" + method.getTimeComplexity() + ")");
        vh.spaceOView.setText("O(" + method.getSpaceComplexity() + ")");
        vh.codeView.setCode(method.getCode());
        return convertView;
    }

    private static final class ViewHolder {

        private TextView indexView, descView, timeOView, spaceOView;
        private CodeView codeView;

        public ViewHolder(View convertView) {
            indexView = convertView.findViewById(R.id.tv_method_index);
            descView = convertView.findViewById(R.id.tv_method_desc);
            timeOView = convertView.findViewById(R.id.tv_method_time_o);
            spaceOView = convertView.findViewById(R.id.tv_method_space_o);
            codeView = convertView.findViewById(R.id.tv_method_code);
        }
    }
}
