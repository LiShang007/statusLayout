package com.lishang.library.statuslayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 状态管理
 *
 * @author LiShang
 * @time 2019/8/29 15:24
 */
public class StatusLayout extends FrameLayout {

    private static final String TAG = "StatusLayout";

    /**
     * 没有数据
     */
    private StatusView emptyView;
    /**
     * 加载失败
     */
    private StatusView errorView;
    /**
     * 加载中
     */
    private StatusView loadingView;
    /**
     * 显示数据
     */
    private StatusView dataView;
    /**
     * 当前正在显示的View
     */
    private StatusView currentView;


    /**
     * 状态类型
     * EMPTY      空布局-没有数据
     * ERROR      加载异常
     * LOADING    加载中
     * DATA       数据加载完成-有数据显示
     */
    public enum Status {
        EMPTY, ERROR, LOADING, DATA
    }

    /**
     * xml 布局中默认显示的View
     */
    private int default_show;

    public StatusLayout(Context context) {
        this(context, null);
    }

    public StatusLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StatusLayout);

        int emptyId = array.getResourceId(R.styleable.StatusLayout_status_layout_empty, -1);
        if (emptyId != -1) {
            setEmptyView(emptyId);
        }

        int errorId = array.getResourceId(R.styleable.StatusLayout_status_layout_error, -1);
        if (errorId != -1) {
            setErrorView(errorId);
        }

        int loadingId = array.getResourceId(R.styleable.StatusLayout_status_layout_loading, -1);
        if (loadingId != -1) {
            setLoadingView(loadingId);
        }

        int dataId = array.getResourceId(R.styleable.StatusLayout_status_layout_data, -1);
        if (dataId != -1) {
            setDataView(dataId);
        }

        default_show = array.getInteger(R.styleable.StatusLayout_status_show, -1);

        array.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        switch (default_show) {
            case 1:
                showStatus(Status.EMPTY);
                break;
            case 2:
                showStatus(Status.ERROR);
                break;
            case 3:
                showStatus(Status.LOADING);
                break;
            case 4:
                showStatus(Status.DATA);
                break;
        }
        default_show = -1;

    }

    /**
     * 获取空数据时的布局
     *
     * @return
     */
    public View getEmptyView() {
        return getStatusView(emptyView);
    }

    /**
     * 设置空数据时的布局
     *
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        if (this.emptyView == null) {
            this.emptyView = new StatusView();
        }
        createStatusView(this.emptyView, emptyView);
    }

    /**
     * 设置空数据时的布局
     *
     * @param layoutId
     */
    public void setEmptyView(int layoutId) {
        View view = LayoutInflater.from(getContext()).inflate(layoutId, this, false);
        setEmptyView(view);

    }

    /**
     * 数据加载异常布局
     *
     * @return
     */
    public View getErrorView() {
        return getStatusView(errorView);
    }

    /**
     * 设置数据加载异常布局
     *
     * @param errorView
     */
    public void setErrorView(View errorView) {
        if (this.errorView == null) {
            this.errorView = new StatusView();
        }
        createStatusView(this.errorView, errorView);
    }

    /**
     * 设置数据加载异常布局
     *
     * @param layoutId
     */
    public void setErrorView(int layoutId) {
        View view = LayoutInflater.from(getContext()).inflate(layoutId, this, false);

        setErrorView(view);
    }

    /**
     * 获取加载中的布局
     *
     * @return
     */
    public View getLoadingView() {
        return getStatusView(loadingView);
    }

    /**
     * 设置加载中的布局
     *
     * @param loadingView
     */
    public void setLoadingView(View loadingView) {
        if (this.loadingView == null) {
            this.loadingView = new StatusView();
        }
        createStatusView(this.loadingView, loadingView);
    }

    /**
     * 设置加载中的布局
     *
     * @param layoutId
     */
    public void setLoadingView(int layoutId) {
        View view = LayoutInflater.from(getContext()).inflate(layoutId, this, false);
        setLoadingView(view);
    }

    /**
     * 获取有数据时的布局
     *
     * @return
     */
    public View getDataView() {
        return getStatusView(dataView);
    }

    /**
     * 设置内容布局
     *
     * @param dataView
     */
    public void setDataView(View dataView) {
        if (this.dataView == null) {
            this.dataView = new StatusView();
        }
        createStatusView(this.dataView, dataView);
    }

    /**
     * 设置内容布局
     *
     * @param layoutId
     */
    public void setDataView(int layoutId) {
        View view = LayoutInflater.from(getContext()).inflate(layoutId, this, false);
        setDataView(view);
    }

    /**
     * 绑定内容View ，用于xml中直接加载view时
     *
     * @param view
     */
    public void bindDataView(View view) {
        if (this.dataView == null) {
            this.dataView = new StatusView();
        }
        dataView.view = view;
        dataView.inflate = true;

        if (currentView != null) {
            view.setVisibility(GONE);
        } else {
            currentView = dataView;
        }

    }

    /**
     * 显示当前状态
     *
     * @param status
     */
    public void showStatus(Status status) {
        switch (status) {
            case EMPTY:
                showStatus(emptyView);
                break;
            case ERROR:
                showStatus(errorView);
                break;
            case LOADING:
                showStatus(loadingView);
                break;
            case DATA:
                showStatus(dataView);
                break;
        }
    }

    /**
     * 隐藏对应状态
     *
     * @param status
     */
    public void hidStatus(Status status) {
        switch (status) {
            case EMPTY:
                hideStatus(emptyView);
                break;
            case ERROR:
                hideStatus(errorView);
                break;
            case LOADING:
                hideStatus(loadingView);
                break;
            case DATA:
                hideStatus(dataView);
                break;
        }
    }

    /**
     * 对应状态是否显示中
     *
     * @param status
     * @return
     */
    public boolean isShowing(Status status) {
        switch (status) {
            case EMPTY:
                return emptyView.inflate && emptyView.isShowing();
            case ERROR:
                return errorView.inflate && errorView.isShowing();
            case LOADING:
                return loadingView.inflate && loadingView.isShowing();
            case DATA:
                return dataView.inflate && dataView.isShowing();
            default:
                return false;
        }
    }


    /**
     * 绑定点击事件
     *
     * @param id
     * @param listener
     */
    public void bindClick(Status status, int id, OnClickListener listener) {
        switch (status) {
            case EMPTY:
                if (emptyView != null) {
                    emptyView.view.findViewById(id).setOnClickListener(listener);
                }
                break;
            case ERROR:
                if (errorView != null) {
                    errorView.view.findViewById(id).setOnClickListener(listener);
                }
                break;
            case LOADING:
                if (loadingView != null) {
                    loadingView.view.findViewById(id).setOnClickListener(listener);
                }
            case DATA:
                if (dataView != null) {
                    dataView.view.findViewById(id).setOnClickListener(listener);
                }
            default:
                break;
        }
    }

    /**
     * 设置文本
     *
     * @param status
     * @param id
     * @param text
     */
    public void setText(Status status, int id, CharSequence text) {
        TextView textView = getTextView(status, id);
        if (textView != null) {
            textView.setText(text);
        }
    }

    /**
     * 设置文字颜色
     *
     * @param status
     * @param id
     * @param color
     */
    public void setTextColor(Status status, int id, int color) {
        TextView textView = getTextView(status, id);
        if (textView != null) {
            textView.setTextColor(color);
        }
    }

    /**
     * 设置文字大小
     *
     * @param status
     * @param id
     * @param size
     */
    public void setTextSize(Status status, int id, int size) {
        TextView textView = getTextView(status, id);
        if (textView != null) {
            textView.setTextSize(size);
        }
    }

    public void setImageRes(Status status, int id, int res) {
        ImageView imageView = getImageView(status, id);
        if (imageView != null) {
            imageView.setImageResource(res);
        }
    }

    public void setImageDrawable(Status status, int id, Drawable drawable) {
        ImageView imageView = getImageView(status, id);
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    private ImageView getImageView(Status status, int id) {
        ImageView imageView = null;
        switch (status) {
            case EMPTY:
                if (emptyView != null) {
                    imageView = emptyView.view.findViewById(id);
                }
                break;
            case ERROR:
                if (errorView != null) {
                    imageView = errorView.view.findViewById(id);
                }
                break;
            case LOADING:
                if (loadingView != null) {
                    imageView = loadingView.view.findViewById(id);
                }
            case DATA:
                if (dataView != null) {
                    imageView = dataView.view.findViewById(id);
                }
            default:
                break;
        }
        return imageView;
    }

    private TextView getTextView(Status status, int id) {
        TextView textView = null;
        switch (status) {
            case EMPTY:
                if (emptyView != null) {
                    textView = emptyView.view.findViewById(id);
                }
                break;
            case ERROR:
                if (errorView != null) {
                    textView = errorView.view.findViewById(id);
                }
                break;
            case LOADING:
                if (loadingView != null) {
                    textView = loadingView.view.findViewById(id);
                }
            case DATA:
                if (dataView != null) {
                    textView = dataView.view.findViewById(id);
                }
            default:
                break;
        }
        return textView;
    }

    private void createStatusView(StatusView status, View view) {
        status.view = view;
        status.inflate = false;
    }

    private View getStatusView(StatusView status) {
        if (status == null) return null;
        return status.view;
    }

    /**
     * 按需加载--将使用到的状态布局加载到界面
     *
     * @param status
     */
    private void showStatus(StatusView status) {
        if (status != null) {
            if (!status.inflate) {
                //说明 当前View 还未添加到布局中
                addView(status.view);
                status.inflate = true;
            } else {
                if (!status.isShowing()) {
                    status.view.setVisibility(VISIBLE);
                }
            }
            if (status == currentView) return;
            hideStatus(currentView);
            currentView = status;
        }
    }


    private void hideStatus(StatusView status) {
        if (status != null) {
            if (status.inflate) {
                status.view.setVisibility(GONE);
            }
        }
    }


    private class StatusView {
        private View view;
        private boolean inflate;


        public boolean isShowing() {
            if (view.getVisibility() == GONE) return false;
            return true;
        }

    }

}
