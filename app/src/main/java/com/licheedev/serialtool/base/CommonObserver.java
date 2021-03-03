package com.licheedev.serialtool.base;

import com.licheedev.serialtool.App;
import com.licheedev.serialtool.net.GsonUtil;
import com.licheedev.serialtool.net.NetUtils;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class CommonObserver<T> implements Observer<BaseEntity> {
    public HttpCallBack mHttpCallBack;
    //传泛型
    Class<?> dataType = null;
    private T t;

    public CommonObserver(HttpCallBack httpCallBack) {
        mHttpCallBack = httpCallBack;
    }

    @Override
    public void onSubscribe(Disposable d) {
        //请求网络前
        boolean networkConnected = NetUtils.isConnected(App.mContext);
        if (networkConnected) {
            mHttpCallBack.onRequest();
        }else {
            //取消订阅
            d.dispose();
            mHttpCallBack.onFailer("网络不给力");
        }
    }

    @Override
    public void onNext(BaseEntity baseEntity) {
        //如果status为0000，则为正常请求成功状态，否则返回失败信息message
        if (baseEntity.getStatus().equals("0000")) {
            //如果有result,则回调result,否则直接返回baseEntity(前提是请求没有result的接口，所传得泛型就是BaseEntity)
            if (baseEntity.result != null) {
//                if (baseEntity.message.equals(Constant.UP_HEAD_PIC)) {
//                    EventBus.getDefault().post(new UpImageEvent(baseEntity.message, (String) baseEntity.result));
//                    return;
//                }
                String json = GsonUtil.getInstance().mGson.toJson(baseEntity.result);
                T result = null;
                List<T> list = null;
                if (json.startsWith("{")) {
                    result = (T) GsonUtil.getInstance().mGson.fromJson(json, dataType);
                } else {
                    ListWithElements<T> tListWithElements = new ListWithElements<>((Class<T>) dataType);
                    list = GsonUtil.mGson.fromJson(json, tListWithElements);
                    result = null;
                }
                if (result != null) {
                    mHttpCallBack.onDataSuccess(result);
                } else if (list != null) {
                    mHttpCallBack.onListSuccess(list);
                } else {
                    mHttpCallBack.onFailer("数据结构异常");
                }
            } else {
                //只适用于判断创建群
                if (baseEntity.message.equals("群名称已存在")) {
                    mHttpCallBack.onFailer(baseEntity.message);
                } else if (baseEntity.message.equals("关注成功")) {
                    mHttpCallBack.onDataSuccess(baseEntity);
                } else if (baseEntity.message.equals("取消成功")) {
                    mHttpCallBack.onDataSuccess(baseEntity);
                } else if (baseEntity.message.equals("点赞成功")) {
                    mHttpCallBack.onDataSuccess(baseEntity);
                } else if (baseEntity.message.equals("查询成功")){
                    mHttpCallBack.onFailer(baseEntity.message);
                } else {
                    mHttpCallBack.onDataSuccess(baseEntity);
                }
                mHttpCallBack.onFailer(baseEntity.message);
            }
        } else {
            mHttpCallBack.onFailer(baseEntity.message);
        }
    }

    @Override
    public void onError(Throwable e) {
        //请求异常
        mHttpCallBack.onFailer(e.getMessage());
    }

    @Override
    public void onComplete() {
        // 请求完成
    }

    public <T> void getDataType(T t){
        dataType=t.getClass();
    }

    private class ListWithElements<T> implements ParameterizedType {

        private Class<T> elementsClass;

        public ListWithElements(Class<T> elementsClass) {
            this.elementsClass = elementsClass;
        }

        public Type[] getActualTypeArguments() {
            return new Type[] {elementsClass};
        }

        public Type getRawType() {
            return List.class;
        }

        public Type getOwnerType() {
            return null;
        }
    }
}
