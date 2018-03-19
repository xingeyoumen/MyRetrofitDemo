package com.kuaishangche.myretrofitdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by Sugar on 2018/3/19/0019.
 */

public class MyWebView extends WebView {
    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (myScrollChangeLinstener != null) {
            myScrollChangeLinstener.onScrollShow(l, t, oldl, oldt);
        }
    }

    //添加回调接口
    public MyScrollChangeLinstener myScrollChangeLinstener;

    //首先定义一个接口
    public interface MyScrollChangeLinstener {
        public void onScrollShow(int l, int t, int r, int b);
    }


    public void setMyScrollChangeLinstener(MyScrollChangeLinstener myScrollChangeLinstener) {
        this.myScrollChangeLinstener = myScrollChangeLinstener;
    }

}