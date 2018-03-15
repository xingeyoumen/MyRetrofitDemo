package com.kuaishangche.myretrofitdemo.retrofit;

import com.kuaishangche.myretrofitdemo.retrofit.base.BaseCallModel;

/**
 * Created by Sugar on 2018/3/14/0014.
 */

public class Translation extends BaseCallModel {
    private int status;

    private Content content;


    public class Content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;

        @Override
        public String toString() {
            return "Content{" +
                    "from='" + from + '\'' +
                    ", to='" + to + '\'' +
                    ", vendor='" + vendor + '\'' +
                    ", out='" + out + '\'' +
                    ", errNo=" + errNo +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Translation{" +
                "status=" + status +
                ", content====" + content.toString() +
                '}';
    }

    //定义 输出返回数据 的方法
    public void show() {
        System.out.println(status);

        System.out.println(content.from);
        System.out.println(content.to);
        System.out.println(content.vendor);
        System.out.println(content.out);
        System.out.println(content.errNo);
    }
}
