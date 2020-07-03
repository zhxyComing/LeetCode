package com.dixon.leetcode.fun.md;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dixon.leetcode.R;
import com.dixon.leetcode.core.base.BaseActivity;
import com.dixon.leetcode.core.base.RouterConstant;
import com.dixon.leetcode.core.bean.MdData;
import com.dixon.simple.router.api.SimpleRouter;
import com.dixon.simple.router.core.SRouter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by: dixon.xu
 * Create on: 2020.07.03
 * Functional desc: MD文件列表页 会加载assets/md/下的md文件列表
 */
@SimpleRouter(value = RouterConstant.MD_LIST_PAGE, interceptor = "")
public class MdListActivity extends BaseActivity {

    private ListView mdsView;
    private List<MdData> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md_list);
        loadData();
    }

    private void loadData() {
        try {
            String[] mds = getAssets().list("md");
            if (mds != null) {
                for (String md : mds) {
                    data.add(new MdData("md/", md));
                }
                mdsView.setAdapter(new MdListAdapter(data, this));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        mdsView = findViewById(R.id.lv_md_list);
        mdsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MdData mdData = data.get(position);
                SRouter.build(MdListActivity.this, RouterConstant.MD_DETAIL_PAGE)
                        .addParams("md", mdData)
                        .execute();
            }
        });
    }
}