package com.dixon.leetcode.fun.topicdetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dixon.descapi.bean.DescData;
import com.dixon.leetcode.R;
import com.dixon.leetcode.core.base.BaseActivity;
import com.dixon.leetcode.core.base.RouterConstant;
import com.dixon.simple.router.api.SimpleParam;
import com.dixon.simple.router.api.SimpleRouter;
import com.dixon.simple.router.core.SRouter;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: 题目详情页
 */
@SimpleRouter(value = RouterConstant.TOPIC_DETAIL_PAGE, interceptor = "")
public class TopicDetailActivity extends BaseActivity {

    @SimpleParam("descData")
    DescData descData;

    private TextView indexView, titleView, exampleView;
    private ListView methodView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);
        SRouter.initParams(this);
        if (descData == null) {
            Toast.makeText(this, "题目异常", Toast.LENGTH_SHORT).show();
            finish();
        }
        indexView.setText(String.valueOf(descData.getIndex()));
        titleView.setText(descData.getTopic());
        exampleView.setText(descData.getExample());
        methodView.setAdapter(new TopicMethodAdapter(descData.getExplanations(), this));
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