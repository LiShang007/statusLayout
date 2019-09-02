package com.lishang.statelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lishang.library.statuslayout.StatusLayout;
import com.lishang.library.statuslayout.StatusLayoutManager;

public class RelativeLayoutActivity extends AppCompatActivity {

    private TextView textTitle;
    private StatusLayoutManager statusLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_style);
        initView();
        String str = "inject 前 findViewById=";
        str += findViewById(R.id.ll_content).getClass().getSimpleName();

        statusLayout = StatusLayoutManager.inject(findViewById(R.id.ll_content))
                .showStatus(StatusLayout.Status.DATA);

        str += "\n";
        str += "inject 后 findViewById=";
        str += findViewById(R.id.ll_content).getClass().getSimpleName();

        textTitle.setText(str);

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
        textTitle = (TextView) findViewById(R.id.text_title);
    }
}
