package com.licheedev.serialtool.activity.clear;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.deposit.PaperCurrencyDepositActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.event.StatusEvent;
import com.licheedev.serialtool.dialog.CurrenySelectUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.licheedev.serialtool.activity.deposit.PaperCurrencyDepositActivity.RESULT_CODE_DEPOSIT;

public class ClearDeviceHintActivity extends BaseActivity {

    private ImageView open_door;
    private int i=0;
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
    private Dialog overDepositDialogdialog;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_clear_hint;
    }

    @Override
    protected void initView() {
        super.initView();
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
        red = Color.parseColor("#FF0404");
        color=red;
        handler.sendEmptyMessage(1);
    }

    @Override
    public void initListener() {

        clear_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrenySelectUtil.showQuitDialog(ClearDeviceHintActivity.this);
            }
        });
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
//            case 3:
//                if (!isPutIn){
//                    handler.removeMessages(3);
//                    take_out.setColorFilter(Color.parseColor("#00FF06"));
//                    handler.sendEmptyMessage(4);
//                    isPutIn=true;
//                }
//                break;
            case 4:
                if (isClose){//关闭保险柜门
                    handler.removeMessages(4);
                    close_door.setColorFilter(Color.parseColor("#00FF06"));
                    isPutIn=false;
                    isTakeOut=false;
                    isOpen=false;
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
                    handler.sendEmptyMessageDelayed(1,750);
                    break;
                case 2://取出钞袋闪烁
                    if (color==Color.WHITE){
                        take_out.setColorFilter(red);
                        color=red;
                    }else if (color==red){
                        take_out.setColorFilter(Color.WHITE);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(2,750);
                    break;
                case 3://放入新钞袋闪烁
                    if (color==Color.WHITE){
                        put_in.setColorFilter(red);
                        color=red;
                    }else if (color==red){
                        put_in.setColorFilter(Color.WHITE);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(3,750);
                    break;
                case 4://关闭保险柜门闪烁
                    if (color==Color.WHITE){
                        close_door.setColorFilter(red);
                        color=red;
                    }else if (color==red){
                        close_door.setColorFilter(Color.WHITE);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(4,750);
                    break;
            }

        }
    };
}