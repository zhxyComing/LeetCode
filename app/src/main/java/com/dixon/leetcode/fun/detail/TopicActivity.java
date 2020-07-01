package com.dixon.leetcode.fun.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dixon.descapi.bean.DescData;
import com.dixon.leetcode.R;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: 题目详情页
 */
public class TopicActivity extends AppCompatActivity {

    private DescData descData;

    private TextView indexView, titleView, exampleView;
    private ListView methodView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        descData = (DescData) getIntent().getSerializableExtra("descData");
        if (descData == null) {
            Toast.makeText(this, "题目异常", Toast.LENGTH_SHORT).show();
            finish();
        }
        indexView.setText(String.valueOf(descData.getIndex()));
        titleView.setText(descData.getTopic());
        exampleView.setText(descData.getExample());
        methodView.setAdapter(new MethodAdapter(descData.getExplanations(), this));
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        indexView = findViewById(R.id.tv_topic_index);
        titleView = findViewById(R.id.tv_topic_title);
        exampleView = findViewById(R.id.tv_topic_example);
        methodView = findViewById(R.id.lv_topic_method);
    }
}