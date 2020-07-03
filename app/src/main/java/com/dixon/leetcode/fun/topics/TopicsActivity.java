package com.dixon.leetcode.fun.topics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dixon.descapi.bean.DescData;
import com.dixon.leetcode.R;
import com.dixon.leetcode.core.base.BaseActivity;
import com.dixon.leetcode.core.base.RouterConstant;
import com.dixon.leetcode.core.util.AssetsUtil;
import com.dixon.simple.router.api.SimpleRouter;
import com.dixon.simple.router.core.SRouter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: LeetCode题目列表页
 */
@SimpleRouter(value = RouterConstant.TOPICS_PAGE, interceptor = "")
public class TopicsActivity extends BaseActivity {

    private ListView topicsView;
    private List<DescData> descDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        loadData();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        topicsView = findViewById(R.id.lv_topics);
    }

    private void loadData() {
        String topics = AssetsUtil.getFromAssets(this, "topics.txt");
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
            topicsView.setAdapter(new TopicsAdapter(descDataList, TopicsActivity.this));
            topicsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DescData descData = descDataList.get(position);
                    SRouter.build(TopicsActivity.this, RouterConstant.TOPIC_DETAIL_PAGE)
                            .addParams("descData", descData)
                            .execute();
                }
            });
        }
    }
}