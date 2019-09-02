package com.lishang.statelayout;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.lishang.library.statuslayout.StatusLayout;
import com.lishang.library.statuslayout.StatusLayoutManager;

/**
 * 自定义样式
 *
 * @author LiShang
 * @time 2019/8/30 9:48
 */
public class CustomStyleActivity extends AppCompatActivity {
    private StatusLayoutManager statusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_style);
        statusLayout = StatusLayoutManager.newBuilder()
                .setEmptyLayoutId(R.layout.layout_status_custom)
                .setErrorLayoutId(R.layout.layout_status_custom)
                .inject(findViewById(R.id.ll_content))
                .showStatus(StatusLayout.Status.LOADING)
                .bindClick(StatusLayout.Status.ERROR, R.id.btn_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        statusLayout.showStatus(StatusLayout.Status.LOADING);
                    }
                })
                .bindClick(StatusLayout.Status.EMPTY, R.id.btn_retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        statusLayout.showStatus(StatusLayout.Status.LOADING);
                    }
                })
                .setText(StatusLayout.Status.EMPTY, R.id.text_title, "没有找到亲需要的数据~")
                .setText(StatusLayout.Status.ERROR, R.id.text_title, "亲，迷路了~")
                .setImageRes(StatusLayout.Status.EMPTY, R.id.img_icon, R.drawable.icon_empty_1)
                .setImageRes(StatusLayout.Status.ERROR, R.id.img_icon, R.drawable.icon_error_1);
    }


    public void onClick1(View view) {
        statusLayout.showStatus(StatusLayout.Status.EMPTY);
    }

    public void onClick2(View view) {
        statusLayout.showStatus(StatusLayout.Status.ERROR);
    }

    public void onClick3(View view) {
        statusLayout.showStatus(StatusLayout.Status.LOADING);
    }

    public void onClick4(View view) {
        statusLayout.showStatus(StatusLayout.Status.DATA);
    }
}
