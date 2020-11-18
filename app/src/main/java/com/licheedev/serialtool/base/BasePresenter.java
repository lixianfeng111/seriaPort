package com.licheedev.serialtool.base;

public abstract class BasePresenter<IView extends IBaseView,Model extends CommnModel>{
    private IView mIView;
    private Model mModel;

    public BasePresenter(IView iView) {
        mIView=iView;
    }
    protected Model getmModel(){
        if (mModel==null){
            mModel = initModel();
        }
        return mModel;
    }
    public  IView getMiView(){
        return mIView;
    }
    protected abstract Model initModel();

    public  void onDestory(){
        if (mIView!=null){
            mIView=null;
        }
        if (mModel!=null){
            
        }
    }

}
