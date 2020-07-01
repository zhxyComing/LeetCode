package com.dixon.leetcode.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.01
 * Functional desc: 左右滑动不拦截 以防影响codeView
 */
public class NoHorScrollView extends ScrollView {

    private float mDownPosX;
    private float mDownPosY;

    public NoHorScrollView(Context context) {
        super(context);
    }

    public NoHorScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoHorScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final float x = ev.getX();
        final float y = ev.getY();

        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownPosX = x;
                mDownPosY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = Math.abs(x - mDownPosX);
                float deltaY = Math.abs(y - mDownPosY);
                // 左右滑动 不拦截
                if (deltaX > deltaY) {
                    return false;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }
}
