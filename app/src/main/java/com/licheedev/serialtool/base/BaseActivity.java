package com.licheedev.serialtool.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

import com.licheedev.serialtool.App;
import com.licheedev.serialtool.AppManager;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.fragment.LogFragment;
import com.licheedev.serialtool.comn.message.IMessage;
import com.licheedev.serialtool.comn.message.LogManager;
import com.licheedev.serialtool.receiver.NetReceiver;
import com.licheedev.serialtool.util.LanguageUtils;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.constant.Money;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity  {

    protected ActionBar mActionBar;
    private LogFragment mLogFragment;
    protected T miBasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            ButterKnife.bind(this);
            initView();
            initVariable();
            miBasePresenter = initPresenter();
            initData();
            initListener();
//            AppManager.getAppManager().addActivity(this);

        } else {
            finish();
        }
        if (hasActionBar()) {
            mActionBar = getSupportActionBar();
        }
        initFragment();
    }

    protected void initView() {

    }

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getLayoutId();

    public abstract void initListener();
    //初始化变量
    public abstract void initVariable();

    public abstract T initPresenter();

    public abstract void initData();
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshLogList();
//        Resources resource = getResources();
//        Configuration c = resource.getConfiguration() ;
//        c.fontScale = 1.0f;
//        resource.updateConfiguration(c, resource.getDisplayMetrics());
    }


    /**
     * 刷新日志列表
     */
    protected void refreshLogList() {
        if (mLogFragment != null) {
            mLogFragment.updateList();
        }
    }


    //切换英语
    @Override
    protected void attachBaseContext(Context newBase) {
        boolean language = SpzUtils.getBoolean("language",false);
        super.attachBaseContext(LanguageUtils.attachBaseContext(newBase,language));
    }

    /**
     * 初始化日志Fragment
     */
    protected void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        mLogFragment = (LogFragment) fragmentManager.findFragmentById(R.id.log_fragment);
    }

    /**
     * 添加日志
     *
     * @param message
     */
    protected void addLog(IMessage message) {
        LogManager.instance().add(message);
        refreshLogList();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    protected boolean hasActionBar() {
        return true;
    }

    protected void setActionBar(boolean showUp, String title) {
        mActionBar.setHomeButtonEnabled(showUp);
        mActionBar.setDisplayHomeAsUpEnabled(showUp);
        mActionBar.setTitle(title);
    }

    protected void setActionBar(boolean showUp, int stringResId) {
        mActionBar.setHomeButtonEnabled(showUp);
        mActionBar.setDisplayHomeAsUpEnabled(showUp);
        mActionBar.setTitle(stringResId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(IMessage message) {
        // 收到时间，刷新界面

//        ToastUtil.show(App.instance(), "recever " +(message == null? "messge = null" : message.getMessage()));
        if (mLogFragment != null) {
            mLogFragment.add(message);
        }
    }

//    @Override
//    public void onNetChange(boolean netStatus) {
//    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (miBasePresenter != null) {
            miBasePresenter.onDestory();
        }
    }
}
