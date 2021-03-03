package com.licheedev.serialtool.base;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by 枫叶🍁 on 2019/1/21 17:04
 * Email：3507801049@qq.com
 * Role:
 */
public class CommonPresent<T> extends BasePresenter<IBaseView<T>,CommnModel> {

    public CommonPresent(IBaseView<T> tiBaseView) {
        super(tiBaseView);
    }

    //Post请求
    public <E> void doPost(String url, Map<String, Object> params, E t){
        getmModel().doPost(url, params, new HttpCallBack() {
            @Override
            public void onRequest() {
                getMiView().showLoading();
            }

            @Override
            public void onDataSuccess(Object data) {
                T result= (T) data;
                getMiView().onDataSuccess(result);
                getMiView().hideLoading();
            }

            @Override
            public void onFailer(String msg) {
                getMiView().hideLoading();
                getMiView().onDataFailer(msg);
            }

            @Override
            public void onDataEmpty() {
                getMiView().hideLoading();
            }

            @Override
            public void onListSuccess(List list) {
                getMiView().onDataList(list);
                getMiView().hideLoading();
            }
        },t);
    }

    //Post请求
    public <E> void doPostImage(String url, File file, E t){
        getmModel().doPostImage(url, file, new HttpCallBack() {
            @Override
            public void onRequest() {
                getMiView().showLoading();
            }

            @Override
            public void onDataSuccess(Object data) {
                T result= (T) data;
                getMiView().onDataSuccess(result);
                getMiView().hideLoading();
            }

            @Override
            public void onFailer(String msg) {
                getMiView().hideLoading();
                getMiView().onDataFailer(msg);
            }

            @Override
            public void onDataEmpty() {
                getMiView().hideLoading();
            }

            @Override
            public void onListSuccess(List list) {
                getMiView().onDataList(list);
                getMiView().hideLoading();
            }
        },t);
    }
    public <E> void doPostTwo(String url, Map<String, Object> params, E t){
        getmModel().doPost(url, params, new HttpCallBack() {
            @Override
            public void onRequest() {
                getMiView().showLoading();
            }

            @Override
            public void onDataSuccess(Object data) {
                T result= (T) data;
                getMiView().onDataSuccess(result);
                getMiView().hideLoading();
            }

            @Override
            public void onFailer(String msg) {
                getMiView().hideLoading();
                getMiView().onDataFailer(msg);
            }

            @Override
            public void onDataEmpty() {
                getMiView().hideLoading();
            }

            @Override
            public void onListSuccess(List list) {
                getMiView().onDataList(list);
                getMiView().hideLoading();
            }
        },t);
    }
    //Get请求
    public <E> void doGet(String url, Map<String, Object> params, E t){
        getmModel().doGet(url, params, new HttpCallBack() {
            @Override
            public void onRequest() {
                getMiView().showLoading();
            }

            @Override
            public void onDataSuccess(Object data) {
                T result= (T) data;
                getMiView().onDataSuccess(result);
                getMiView().hideLoading();
            }

            @Override
            public void onFailer(String msg) {
                getMiView().hideLoading();
                getMiView().onDataFailer(msg);
            }

            @Override
            public void onDataEmpty() {
                getMiView().hideLoading();
            }

            @Override
            public void onListSuccess(List list) {
                getMiView().onDataList(list);
                getMiView().hideLoading();
            }
        },t);
    }
    //Get请求不用添加参数
    public <E> void doGetTwo(String url, E t){
        getmModel().doGettwo(url,new HttpCallBack() {
            @Override
            public void onRequest() {
                getMiView().showLoading();
            }

            @Override
            public void onDataSuccess(Object data) {
                T result= (T) data;
                getMiView().onDataSuccess(result);
                getMiView().hideLoading();
            }

            @Override
            public void onFailer(String msg) {
                getMiView().hideLoading();
                getMiView().onDataFailer(msg);
            }

            @Override
            public void onDataEmpty() {
                getMiView().hideLoading();
            }

            @Override
            public void onListSuccess(List list) {
                getMiView().onDataList(list);
                getMiView().hideLoading();
            }
        },t);
    }
    //Delete请求
    public <E> void doDelete(String url, Map<String, Object> params, E t){
        getmModel().doDelete(url, params, new HttpCallBack() {
            @Override
            public void onRequest() {
                getMiView().showLoading();
            }

            @Override
            public void onDataSuccess(Object data) {
                T result= (T) data;
                getMiView().onDataSuccess(result);
                getMiView().hideLoading();
            }

            @Override
            public void onFailer(String msg) {
                getMiView().hideLoading();
                getMiView().onDataFailer(msg);
            }

            @Override
            public void onDataEmpty() {
                getMiView().hideLoading();
            }

            @Override
            public void onListSuccess(List list) {
                getMiView().onDataList(list);
                getMiView().hideLoading();
            }
        },t);
    }

    //Put请求
    public <E> void doPut(String url, Map<String, Object> params, E t){
        getmModel().doPut(url, params, new HttpCallBack() {
            @Override
            public void onRequest() {
                getMiView().showLoading();
            }

            @Override
            public void onDataSuccess(Object data) {
                T result= (T) data;
                getMiView().modifySuccess((BaseEntity) result);
                getMiView().hideLoading();
            }

            @Override
            public void onFailer(String msg) {
                getMiView().hideLoading();
                getMiView().onDataFailer(msg);
            }

            @Override
            public void onDataEmpty() {
                getMiView().hideLoading();
            }

            @Override
            public void onListSuccess(List list) {
                getMiView().onDataList(list);
                getMiView().hideLoading();
            }
        },t);
    }

    @Override
    protected CommnModel initModel() {
        return new CommnModel();
    }
}
