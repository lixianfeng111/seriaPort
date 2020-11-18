package com.licheedev.serialtool.base;

import java.util.List;

/**
 * author: 张豪宽
 * time: 2019.01.21
 * email: z2893_122@163.com
 */
public interface HttpCallBack<T> {
    void onRequest();
    void onDataSuccess(T data);
    void onFailer(String msg);
    void onDataEmpty();
    void onListSuccess(List<T> list);
}
