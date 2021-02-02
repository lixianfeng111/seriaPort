package com.licheedev.serialtool.activity.clear;


import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.caysn.autoreplyprint.AutoReplyPrint;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.SerialPortManager2;
import com.licheedev.serialtool.comn.event.StatusEvent;
import com.licheedev.serialtool.dialog.CurrenySelectUtil;
import com.licheedev.serialtool.util.DepositRecordUtil;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.TimeFormartUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.licheedev.serialtool.util.constant.Constant;
import com.sun.jna.Pointer;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;


public class ClearDeviceHintActivity extends BaseActivity {

    private ClearDeviceHintActivity clearDeviceHintActivity;
    private ImageView open_door;
    private int n=5;
    private int red;
    private int blue;
    int color;
    private ImageButton clear_back;
    private Button start_clear;
    private ImageView take_out;
    private ImageView put_in;
    private ImageView close_door;
    private boolean isOpen = false;
    private boolean isTakeOut=false;
    private boolean isPutIn=false;
    private boolean isClose=false;
    private Pointer h;
    private boolean isCleared=false;
    private TextView first;
    private TextView second;
    private TextView third;
    private TextView fourth;
    private boolean isChange;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_clear_hint;
    }

    @Override
    protected void initView() {
        super.initView();
        clearDeviceHintActivity=this;
        //打开门
        open_door = findViewById(R.id.open_door);
        //返回
        clear_back = findViewById(R.id.clear_back);
        //取出钞袋
        take_out = findViewById(R.id.take_out);
        //放入钞袋
        put_in = findViewById(R.id.put_in);
        //关闭保险柜门
        close_door = findViewById(R.id.close_door);
        //获取对应的文本 第一步……第四步
        first = findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);
        fourth = findViewById(R.id.fourth);
        start_clear = findViewById(R.id.start_clear);
        red = getResources().getColor(R.color.clear_red);
        blue = getResources().getColor(R.color.clear_green);
        color=red;
        handler.sendEmptyMessage(1);
        OpenPort();
    }

    private AdapterView.OnItemClickListener onTestFunctionClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            final String functionName = ((TextView) view).getText().toString();
            if ((functionName == null) || (functionName.isEmpty()))
                return;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TestFunction fun = new TestFunction();
                        fun.activity2 = clearDeviceHintActivity;
                        Method m = TestFunction.class.getDeclaredMethod(functionName, Pointer.class);
                        m.invoke(fun, h);
                    } catch (Throwable tr) {
                        tr.printStackTrace();
                    }
                }
            }).start();
        }
    };

    @Override
    public void initListener() {
        //打印凭证
        start_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCleared){//是否更换了钞袋
                    if (n>0){//是否到最多打印次数
                        isChange=true;
                        n--;
                        TestFunction.Test_Pos_SampleTicket_80MM(ClearDeviceHintActivity.this,h);
                    }else {
                        DepositRecordUtil.saveDepositRecord();
                        isCleared=false;
                        n=5;
                    }
                }
            }
        });
        //返回
        clear_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOpen){
                    initQuit();
                }
            }
        });
    }

    private void initQuit() {
        CurrenySelectUtil.showQuitDialog(ClearDeviceHintActivity.this, new Callback() {
            @Override
            public void onQuitDialogClick() {
                if (isCleared){//是否更换了钞袋
                    if (!isChange){//更换钞袋后没有打印则退出时打印
                        print();
                    }
                }else {//没有更换钞袋
                    String string = SpzUtils.getString(Constant.LEAD_SEAL2);//获取上次封铅号
                    SpzUtils.putString(Constant.OLD_BAG_ID,SpzUtils.getString(Constant.BAG_ID2));
                    SpzUtils.putString(Constant.NEW_LEAD_SEAL,string);//新钞袋号还是上次的
                    SpzUtils.putString(Constant.OLD_LEAD_SEAL,string);
                    SpzUtils.putString(Constant.LEAD_SEAL,string);
                }
                finish();
            }
        });
    }

    public interface Callback {
        void onQuitDialogClick();
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initData() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStatusEvent(StatusEvent statusEvent){
        int statu = statusEvent.getStatu();
        switch (statu){
            case 1://保险柜门的状态
                if (!isOpen){//打开了保险柜门
                    handler.removeMessages(1);
                    open_door.setColorFilter(blue);
                    first.setTextColor(blue);
                    handler.sendEmptyMessage(2);
                    isOpen=true;
                }
                if (isPutIn){//重新放入钞箱
                    handler.removeMessages(3);
                    put_in.setColorFilter(blue);
                    third.setTextColor(blue);
                    handler.sendEmptyMessage(4);
                    isPutIn=false;

                    boolean start = SpzUtils.getBoolean(Constant.START, false);
                    if (start){//判断是不是第一次清机
                        SpzUtils.putString(Constant.old_timeDay,SpzUtils.getString(Constant.timeDay2));
                        SpzUtils.putString(Constant.OLD_TIME,SpzUtils.getString(Constant.TIME2));
                        SpzUtils.putBoolean(Constant.START,false);
                    }else {//存上次放入朝箱时间
                        SpzUtils.putString(Constant.old_timeDay,SpzUtils.getString(Constant.timeDay));
                        SpzUtils.putString(Constant.OLD_TIME,SpzUtils.getString(Constant.TIME));
                    }
                    //保存放入新钞箱的时间
                    SpzUtils.putString(Constant.timeDay,TimeFormartUtils.getTimeDay());
                    SpzUtils.putString(Constant.TIME,TimeFormartUtils.getTime());
                }
                break;
            case 2:
                if (!isTakeOut){//取出钞箱
                    handler.removeMessages(2);
                    take_out.setColorFilter(blue);
                    second.setTextColor(blue);
                    isPutIn=true;
                    handler.sendEmptyMessage(3);
                    isTakeOut=true;
                    isClose=true;
                }
                break;
            case 4:
                if (isClose){//关闭保险柜门
                    handler.removeMessages(4);
                    close_door.setColorFilter(blue);
                    fourth.setTextColor(blue);
                    SpzUtils.putBoolean(Constant.IS_CHANGE,true);
                    isPutIn=false;
                    isTakeOut=false;
                    isCleared=true;
                }
                isOpen=false;
                break;
        }
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1://打开保险柜门闪烁
                    if (color==Color.WHITE){
                        open_door.setColorFilter(color);
                        first.setTextColor(color);
                        color=red;
                    }else if (color==red){
                        open_door.setColorFilter(color);
                        first.setTextColor(color);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(1,1000);
                    break;
                case 2://取出钞袋闪烁
                    if (color==Color.WHITE){
                        take_out.setColorFilter(color);
                        second.setTextColor(color);
                        color=red;
                    }else if (color==red){
                        take_out.setColorFilter(color);
                        second.setTextColor(color);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(2,1000);
                    break;
                case 3://放入新钞袋闪烁
                    if (color==Color.WHITE){
                        put_in.setColorFilter(color);
                        third.setTextColor(color);
                        color=red;
                    }else if (color==color){
                        put_in.setColorFilter(color);
                        third.setTextColor(color);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(3,1000);
                    break;
                case 4://关闭保险柜门闪烁
                    if (color==Color.WHITE){
                        close_door.setColorFilter(color);
                        fourth.setTextColor(color);
                        color=red;
                    }else if (color==red){
                        close_door.setColorFilter(color);
                        fourth.setTextColor(color);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(4,1000);
                    break;
            }

        }
    };

    private void ClosePort() {
        AutoReplyPrint.INSTANCE.CP_Port_Close(h);
//        if (h != Pointer.NULL) {
//            AutoReplyPrint.INSTANCE.CP_Port_Close(h);
//        }
    }

    private void OpenPort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom(Constant.PORT, Constant.BAUD_RATE, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, AutoReplyPrint.CP_ComFlowControl_XonXoff, 0);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        if (n>0&&isCleared){
            DepositRecordUtil.saveDepositRecord();
            SpzUtils.putBoolean(Constant.LAST_CHANGE,false);
        }
        ClosePort();
        isCleared=false;
        n=0;
        SerialPortManager2.instance().close();
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
    private void print(){
        TestFunction.Test_Pos_SampleTicket_80MM(ClearDeviceHintActivity.this,h);
        DepositRecordUtil.saveDepositRecord();
        SpzUtils.putBoolean(Constant.LAST_CHANGE,false);
    }
}