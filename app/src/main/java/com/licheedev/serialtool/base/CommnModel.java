package com.licheedev.serialtool.base;

import com.licheedev.serialtool.net.RetrofitUtil;
import java.io.File;
import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class CommnModel {

    private BaseService mBaseService;

    public <T> void doGet(String url, Map<String, Object> params, HttpCallBack httpCallBack, T t) {
        mBaseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        CommonObserver commonObserver = new CommonObserver(httpCallBack);
        commonObserver.getDataType(t);
        Observable<BaseEntity> mObservable = mBaseService.doGet(url, params);
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commonObserver);
    }

    public <T> void doGettwo(String url, HttpCallBack httpCallBack, T t) {
        mBaseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        CommonObserver commonObserver = new CommonObserver(httpCallBack);
        commonObserver.getDataType(t);
        Observable<BaseEntity> mObservable = mBaseService.doGettwo(url);
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commonObserver);
    }

    public <T> void doPost(String url, Map<String, Object> params, HttpCallBack httpCallBack, T t) {
        mBaseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        CommonObserver commonObserver = new CommonObserver(httpCallBack);
        commonObserver.getDataType(t);
        Observable<BaseEntity> mObservable = mBaseService.doPost(url, params);
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commonObserver);
    }

    //上传头像
    public <T> void doPostImage(String url, File file, HttpCallBack httpCallBack, T t) {
        mBaseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        //设置Content-Type:multipart/form-data
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //设置Content-Disposition:form-data; name="photo"; filename="xuezhiqian.png"
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        CommonObserver commonObserver = new CommonObserver(httpCallBack);
        commonObserver.getDataType(t);
        Observable<BaseEntity> mObservable = mBaseService.upImage(url, body);
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commonObserver);
    }

    public <T> void doDelete(String url, Map<String, Object> params, HttpCallBack httpCallBack, T t) {
        mBaseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        CommonObserver commonObserver = new CommonObserver(httpCallBack);
        commonObserver.getDataType(t);
        Observable<BaseEntity> mObservable = mBaseService.doDelete(url, params);
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commonObserver);
    }

    public <T> void doPut(String url, Map<String, Object> params, HttpCallBack httpCallBack, T t) {
        mBaseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        CommonObserver commonObserver = new CommonObserver(httpCallBack);
        commonObserver.getDataType(t);
        Observable<BaseEntity> mObservable = mBaseService.doPut(url, params);
        mObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(commonObserver);
    }

    /*public <T> void doPosttwo(String url, Map<String, Object> params, T t, HttpCallBack httpCallBack,HashMap<String,Object> headMap) {

        mBaseService = RetrofitUtil.getInstance().createApi(BaseService.class);

        CommnObservertwo baseObserver = new CommnObservertwo(httpCallBack);
        baseObserver.getDataType(t);

        Observable<BaseEntity> baseEntityObservable = null;

        if (headMap != null) {
            baseEntityObservable  = mBaseService.doPost(url, params,headMap);
        }else {
            baseEntityObservable=mBaseService.doPost(url,params);
        }

        baseEntityObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseObserver);

    }*/

    public void onDestory() {

    }

}
