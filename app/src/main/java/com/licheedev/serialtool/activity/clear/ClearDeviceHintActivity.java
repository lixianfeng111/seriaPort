package com.licheedev.serialtool.activity.clear;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;

public class ClearDeviceHintActivity extends BaseActivity {

    private ImageView open_door;
    private int i=0;
    private int red;

    int color;
    private ImageView start_clear;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clear_hint;
    }

    @Override
    protected void initView() {
        super.initView();
        open_door = findViewById(R.id.open_door);
        start_clear = findViewById(R.id.start_clear);
        red = Color.parseColor("#FF0404");
        color=red;
    }

    @Override
    public void initListener() {

        start_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i==0){
                    handler.sendEmptyMessage(1);
                    i++;
                }else {
                    handler.removeMessages(1);
                    open_door.setColorFilter(Color.parseColor("#00FF06"));
                    i=0;
                }

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

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    if (color==Color.WHITE){
                        open_door.setColorFilter(red);
                        color=red;
                    }else if (color==red){
                        open_door.setColorFilter(Color.WHITE);
                        color=Color.WHITE;
                    }
                    handler.sendEmptyMessageDelayed(1,750);
                    break;
            }

        }
    };
}