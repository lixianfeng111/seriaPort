package com.licheedev.serialtool.activity.clear;
import android.Manifest;
import android.graphics.Color;

import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;

import android.support.annotation.NonNull;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.licheedev.serialtool.App;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.event.StatusEvent;
import com.licheedev.serialtool.dialog.CurrenySelectUtil;
import com.licheedev.serialtool.util.PermissionsUtils;
import com.sun.jna.Pointer;

import com.sun.jna.ptr.IntByReference;



import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;


public class ClearDeviceHintActivity extends BaseActivity {

    private ClearDeviceHintActivity clearDeviceHintActivity;
    private ImageView open_door;
    private int n=5;
    private int red;
    int color;
    private ImageButton clear_back;
    private ImageView take_out;
    private ImageView put_in;
    private ImageView close_door;
    private boolean isOpen = false;
    private boolean isTakeOut=false;
    private boolean isPutIn=false;
    private boolean isClose=false;
    private ComboBox start_clear;
    private Pointer h=Pointer.NULL;
    private boolean isCleared=false;
    private static final int RequestCode_RequestAllPermissions = 1;
    AutoReplyPrint.CP_OnPortOpenedEvent_Callback opened_callback = new AutoReplyPrint.CP_OnPortOpenedEvent_Callback() {
        @Override
        public void CP_OnPortOpenedEvent(Pointer handle, String name, Pointer private_data) {
            clearDeviceHintActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    Toast.makeText(clearDeviceHintActivity, "Open Success", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    AutoReplyPrint.CP_OnPortOpenFailedEvent_Callback openfailed_callback = new AutoReplyPrint.CP_OnPortOpenFailedEvent_Callback() {
        @Override
        public void CP_OnPortOpenFailedEvent(Pointer handle, String name, Pointer private_data) {

            clearDeviceHintActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(clearDeviceHintActivity, "Open Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
    AutoReplyPrint.CP_OnPortClosedEvent_Callback closed_callback = new AutoReplyPrint.CP_OnPortClosedEvent_Callback() {
        @Override
        public void CP_OnPortClosedEvent(Pointer pointer, Pointer pointer1) {

            clearDeviceHintActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ClosePort();
                }
            });
        }
    };
    private void AddCallback() {
        AutoReplyPrint.INSTANCE.CP_Port_AddOnPortOpenedEvent(opened_callback, Pointer.NULL);
        AutoReplyPrint.INSTANCE.CP_Port_AddOnPortOpenFailedEvent(openfailed_callback, Pointer.NULL);
        AutoReplyPrint.INSTANCE.CP_Port_AddOnPortClosedEvent(closed_callback, Pointer.NULL);
    }
    private void RemoveCallback() {
        AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortOpenedEvent(opened_callback);
        AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortOpenFailedEvent(openfailed_callback);
        AutoReplyPrint.INSTANCE.CP_Port_RemoveOnPortClosedEvent(closed_callback);
    }

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
        start_clear = findViewById(R.id.start_clear);
        red = Color.parseColor("#FF0404");
        color=red;
        handler.sendEmptyMessage(1);
        AddCallback();
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
                        fun.ctx = clearDeviceHintActivity;
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
        start_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isCleared){
                    if (n>0){
                        n--;
                        TestFunction.Test_Pos_SampleTicket_80MM_2(ClearDeviceHintActivity.this,h);
                    }else {
                        isCleared=false;
                        n=0;
                    }

                }
            }
        });
        clear_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrenySelectUtil.showQuitDialog(ClearDeviceHintActivity.this, new Callback() {
                    @Override
                    public void onQuitDialogClick() {
                        finish();
                    }
                });
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
    //请求权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //就多一个参数this
        PermissionsUtils.getInstance().onRequestPermissionsResult(((ClearDeviceHintActivity) App.mContext), requestCode, permissions, grantResults);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStatusEvent(StatusEvent statusEvent){
        int statu = statusEvent.getStatu();
        switch (statu){
            case 1://保险柜门的状态
                if (!isOpen){//打开了保险柜门
                    handler.removeMessages(1);
                    open_door.setColorFilter(Color.parseColor("#00FF06"));
                    handler.sendEmptyMessage(2);
                    isOpen=true;
                }
                if (isPutIn){//重新放入钞箱
                    handler.removeMessages(3);
                    put_in.setColorFilter(Color.parseColor("#00FF06"));
                    handler.sendEmptyMessage(4);
                    isPutIn=false;
                }
                break;
            case 2:
                if (!isTakeOut){//取出钞箱
                    handler.removeMessages(2);
                    take_out.setColorFilter(Color.parseColor("#00FF06"));
                    isPutIn=true;
                    handler.sendEmptyMessage(3);
                    isTakeOut=true;
                    isClose=true;
                }
                break;
            case 4:
                if (isClose){//关闭保险柜门
                    handler.removeMessages(4);
                    close_door.setColorFilter(Color.parseColor("#00FF06"));
                    isPutIn=false;
                    isTakeOut=false;
                    isOpen=false;
                    isCleared=true;
                }
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
                        open_door.setColorFilter(red);
                        color=red;
                    }else if (color==red){
                        open_door.setColorFilter(Color.WHITE);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(1,1000);
                    break;
                case 2://取出钞袋闪烁
                    if (color==Color.WHITE){
                        take_out.setColorFilter(red);
                        color=red;
                    }else if (color==red){
                        take_out.setColorFilter(Color.WHITE);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(2,1000);
                    break;
                case 3://放入新钞袋闪烁
                    if (color==Color.WHITE){
                        put_in.setColorFilter(red);
                        color=red;
                    }else if (color==red){
                        put_in.setColorFilter(Color.WHITE);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(3,1000);
                    break;
                case 4://关闭保险柜门闪烁
                    if (color==Color.WHITE){
                        close_door.setColorFilter(red);
                        color=red;
                    }else if (color==red){
                        close_door.setColorFilter(Color.WHITE);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(4,1000);
                    break;
            }

        }
    };

    private void requestAllPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String permissions[] = {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
            };
            requestPermissions(permissions, RequestCode_RequestAllPermissions);
        }
    }

    private void ClosePort() {
        if (h != Pointer.NULL) {
            AutoReplyPrint.INSTANCE.CP_Port_Close(h);
            h = Pointer.NULL;
        }

    }

    private void OpenPort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom("/dev/ttyS3", 9600, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, AutoReplyPrint.CP_ComFlowControl_XonXoff, 0);
            }
        }).start();
    }
    @Override
    protected void onDestroy() {
        RemoveCallback();
        isCleared=false;
        n=0;
        super.onDestroy();
    }
}