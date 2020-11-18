package com.licheedev.serialtool.base;

import java.util.List;

public interface IBaseView<T> {
    void showLoading();

    void hideLoading();

    void onDataSuccess(T data);

    void onDataFailer(String msg);

    void onDataList(List<T> list);

    void modifySuccess(BaseEntity baseEntity);


}
