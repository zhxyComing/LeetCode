package com.dixon.leetcode.fun.home;

import android.os.Bundle;
import android.view.View;

import com.dixon.leetcode.R;
import com.dixon.leetcode.core.base.BaseActivity;
import com.dixon.leetcode.core.base.RouterConstant;
import com.dixon.simple.router.api.SimpleRouter;
import com.dixon.simple.router.core.SRouter;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.03
 * Functional desc: 功能列表页
 */
@SimpleRouter(value = RouterConstant.HOME_PAGE, interceptor = "")
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        findViewById(R.id.tv_md).setOnClickListener(this);
        findViewById(R.id.tv_leet_code).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_md:
                SRouter.build(this, RouterConstant.MD_LIST_PAGE).execute();
                break;
            case R.id.tv_leet_code:
                SRouter.build(this, RouterConstant.TOPICS_PAGE).execute();
                break;
        }
    }
}