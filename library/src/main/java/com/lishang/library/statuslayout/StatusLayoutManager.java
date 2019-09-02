package com.lishang.library.statuslayout;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 状态管理
 *
 * @author LiShang
 * @time 2019/8/29 17:06
 */
public class StatusLayoutManager {
    private static DefaultStatus defaultStatus;

    private StatusLayout statusLayout;

    private StatusLayoutManager(StatusLayout statusLayout) {
        this.statusLayout = statusLayout;
    }


    /**
     * 用于自定义样式使用
     *
     * @return
     */
    public static Builder newBuilder() {
        Builder builder = new Builder();
        if (defaultStatus != null) {
            builder.setEmptyLayoutId(defaultStatus.emptyLayoutId)
                    .setErrorLayoutId(defaultStatus.errorLayoutId)
                    .setLoadingLayoutId(defaultStatus.loadingLayoutId);
        }
        return builder;
    }

    /**
     * 设置全局默认样式
     *
     * @param layout
     * @return
     */
    public static DefaultStatus setEmptyLayoutId(int layout) {
        if (defaultStatus == null) {
            defaultStatus = new DefaultStatus();
        }
        return defaultStatus.setEmptyLayoutId(layout);
    }

    /**
     * 设置内容view
     *
     * @param view
     * @return
     */
    public static StatusLayoutManager inject(View view) {
        if (defaultStatus == null) {
            throw new NullPointerException("default status is not init.");
        }

        return newBuilder().inject(view);
    }

    /**
     * 主要是为了xml中布局包裹成StatusLayoutManager
     *
     * @param statusLayout
     * @return
     */
    public static StatusLayoutManager wrap(StatusLayout statusLayout) {
        return new StatusLayoutManager(statusLayout);
    }

    /**
     * 绑定点击事件
     *
     * @param id
     * @param listener
     */
    public StatusLayoutManager bindClick(StatusLayout.Status status, int id, View.OnClickListener listener) {
        statusLayout.bindClick(status, id, listener);
        return this;
    }

    /**
     * 设置文本
     *
     * @param status
     * @param id
     * @param text
     */
    public StatusLayoutManager setText(StatusLayout.Status status, int id, CharSequence text) {
        statusLayout.setText(status, id, text);
        return this;
    }

    /**
     * 设置文字颜色
     *
     * @param status
     * @param id
     * @param color
     */
    public StatusLayoutManager setTextColor(StatusLayout.Status status, int id, int color) {
        statusLayout.setTextColor(status, id, color);
        return this;
    }

    /**
     * 设置文字大小
     *
     * @param status
     * @param id
     * @param size
     */
    public StatusLayoutManager setTextSize(StatusLayout.Status status, int id, int size) {
        statusLayout.setTextSize(status, id, size);
        return this;
    }

    /**
     * 设置图片
     *
     * @param status
     * @param id
     * @param res
     * @return
     */
    public StatusLayoutManager setImageRes(StatusLayout.Status status, int id, int res) {
        statusLayout.setImageRes(status, id, res);
        return this;
    }

    /**
     * 设置图片
     *
     * @param status
     * @param id
     * @param drawable
     * @return
     */
    public StatusLayoutManager setImageDrawable(StatusLayout.Status status, int id, Drawable drawable) {
        statusLayout.setImageDrawable(status, id, drawable);
        return this;
    }

    /**
     * 显示当前状态
     *
     * @param status
     */
    public StatusLayoutManager showStatus(StatusLayout.Status status) {
        statusLayout.showStatus(status);
        return this;
    }

    /**
     * 隐藏对应状态
     *
     * @param status
     */
    public StatusLayoutManager hidStatus(StatusLayout.Status status) {
        statusLayout.hidStatus(status);
        return this;
    }

    /**
     * 对应状态是否显示中
     *
     * @param status
     * @return
     */
    public boolean isShowing(StatusLayout.Status status) {
        return statusLayout.isShowing(status);
    }

    /**
     * 获取状态布局
     *
     * @return
     */
    public StatusLayout getStatusLayout() {
        return statusLayout;
    }

    /**
     * 获取各个状态的布局
     *
     * @param status
     * @return
     */
    public View getStatusView(StatusLayout.Status status) {
        switch (status) {
            case EMPTY:
                return statusLayout.getEmptyView();
            case ERROR:
                return statusLayout.getErrorView();
            case LOADING:
                return statusLayout.getLoadingView();
            case DATA:
                return statusLayout.getDataView();
        }
        return null;
    }

    /**
     * 用于自定义状态样式
     */
    public static class Builder {

        private int emptyLayoutId;
        private int errorLayoutId;
        private int loadingLayoutId;

        public Builder setEmptyLayoutId(int emptyLayoutId) {
            this.emptyLayoutId = emptyLayoutId;
            return this;
        }

        public Builder setErrorLayoutId(int errorLayoutId) {
            this.errorLayoutId = errorLayoutId;
            return this;
        }

        public Builder setLoadingLayoutId(int loadingLayoutId) {
            this.loadingLayoutId = loadingLayoutId;
            return this;
        }

        public StatusLayoutManager inject(View view) {
            //获取view的父节点
            ViewGroup parent = (ViewGroup) view.getParent();
            //动态创建状态布局
            StatusLayout layout = new StatusLayout(parent.getContext());
            //将View的LayoutParams赋值给创建的布局，以便后面替换
            layout.setLayoutParams(view.getLayoutParams());
            //获取View在父节点里面的位置
            int index = parent.indexOfChild(view);
            //将View从父节点移除
            parent.removeView(view);
            //将状态布局添加到对应位置
            parent.addView(layout, index);
            //设置各种状态布局
            layout.setLoadingView(loadingLayoutId);
            layout.setErrorView(errorLayoutId);
            layout.setEmptyView(emptyLayoutId);
            layout.setDataView(view);

            if (parent instanceof RelativeLayout) {
                layout.setId(view.getId());
            }

            return new StatusLayoutManager(layout);
        }

    }

    /**
     * 全局默认状态样式
     * 空布局、异常布局、加载中的布局
     */
    public static class DefaultStatus {
        private int emptyLayoutId;
        private int errorLayoutId;
        private int loadingLayoutId;

        public DefaultStatus setEmptyLayoutId(int emptyLayoutId) {
            this.emptyLayoutId = emptyLayoutId;
            return this;
        }

        public DefaultStatus setErrorLayoutId(int errorLayoutId) {
            this.errorLayoutId = errorLayoutId;
            return this;
        }

        public DefaultStatus setLoadingLayoutId(int loadingLayoutId) {
            this.loadingLayoutId = loadingLayoutId;
            return this;
        }
    }


}
