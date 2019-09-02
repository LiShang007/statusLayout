package com.lishang.statelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.lishang.library.statuslayout.StatusLayout;
import com.lishang.library.statuslayout.StatusLayoutManager;

/**
 * 使用全局样式
 *
 * @author LiShang
 * @time 2019/8/30 9:48
 */
public class GlobalStyleActivity extends AppCompatActivity {
    private StatusLayoutManager statusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_style);
        statusLayout = StatusLayoutManager.inject(findViewById(R.id.ll_content))
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
                });
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
