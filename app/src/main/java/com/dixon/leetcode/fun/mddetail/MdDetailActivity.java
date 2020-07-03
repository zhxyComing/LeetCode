package com.dixon.leetcode.fun.mddetail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.dixon.leetcode.R;
import com.dixon.leetcode.core.base.RouterConstant;
import com.dixon.leetcode.core.bean.MdData;
import com.dixon.simple.router.api.SimpleParam;
import com.dixon.simple.router.api.SimpleRouter;
import com.dixon.simple.router.core.SRouter;

import br.tiagohm.markdownview.MarkdownView;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.03
 * Functional desc: MD文件详情页 用于展示MarkDown
 */
@SimpleRouter(value = RouterConstant.MD_DETAIL_PAGE, interceptor = "")
public class MdDetailActivity extends AppCompatActivity {

    @SimpleParam(value = "md")
    MdData mMd;

    private MarkdownView mdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_detail);
        SRouter.initParams(this);
        if (mMd == null) {
            Toast.makeText(this, "内容异常", Toast.LENGTH_SHORT).show();
            finish();
        }
        mdView.loadMarkdownFromAsset(mMd.getDir() + mMd.getName());
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mdView = findViewById(R.id.mv_md_detail);
    }
}