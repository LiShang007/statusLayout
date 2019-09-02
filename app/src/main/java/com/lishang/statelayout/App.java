package com.lishang.statelayout;

import android.app.Application;

import com.lishang.library.statuslayout.StatusLayoutManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //设置全局状态管理
        StatusLayoutManager.setEmptyLayoutId(R.layout.layout_status_empty)
                .setErrorLayoutId(R.layout.layout_status_error)
                .setLoadingLayoutId(R.layout.layout_status_loading);
    }
}
