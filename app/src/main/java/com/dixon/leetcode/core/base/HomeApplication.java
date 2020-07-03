package com.dixon.leetcode.core.base;

import android.app.Application;

import com.dixon.leetcode.core.util.FontUtil;
import com.dixon.simple.router.core.SRouter;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.03
 * Functional desc: Application
 */
public class HomeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SRouter.init(this);
        FontUtil.init(this);
        FontUtil.setDefaultFont("Yun-Book.ttf");
    }
}
