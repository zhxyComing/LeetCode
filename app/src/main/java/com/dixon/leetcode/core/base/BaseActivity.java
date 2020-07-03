package com.dixon.leetcode.core.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dixon.leetcode.core.util.FontUtil;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.03
 * Functional desc:
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        FontUtil.font(getWindow().getDecorView());
    }
}
