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
 * @time 2019/8/30 14:07
 */
public class XmlStyleActivity extends AppCompatActivity {

    private StatusLayout statusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_style);
        initView();
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

    private void initView() {
        statusLayout = (StatusLayout) findViewById(R.id.statusLayout);
        StatusLayoutManager.wrap(statusLayout)
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
}
