package com.dixon.leetcode.fun.topics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dixon.descapi.bean.DescData;
import com.dixon.leetcode.R;
import com.dixon.leetcode.fun.detail.TopicActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: 题目列表页
 */
public class HomeActivity extends AppCompatActivity {

    private ListView topicsView;
    private List<DescData> descDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        topicsView = findViewById(R.id.lv_topics);
    }

    private void loadData() {
        String topics = getFromAssets("topics.txt");
        if (!TextUtils.isEmpty(topics)) {
            descDataList = new Gson().fromJson(topics,
                    new TypeToken<ArrayList<DescData>>() {
                    }.getType());
            // 次序从小到大排序
            Collections.sort(descDataList, new Comparator<DescData>() {
                @Override
                public int compare(DescData o1, DescData o2) {
                    return o1.getIndex() - o2.getIndex();
                }
            });
            topicsView.setAdapter(new TopicsAdapter(descDataList, HomeActivity.this));
            topicsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DescData descData = descDataList.get(position);
                    Intent intent = new Intent(HomeActivity.this, TopicActivity.class);
                    intent.putExtra("descData", descData);
                    startActivity(intent);
                }
            });
        }
    }

    public String getFromAssets(String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuilder Result = new StringBuilder();
            while ((line = bufReader.readLine()) != null)
                Result.append(line);
            return Result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}