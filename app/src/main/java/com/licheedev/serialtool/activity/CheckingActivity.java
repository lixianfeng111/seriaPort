package com.licheedev.serialtool.activity;


import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.dapter.BaseRecyclerAdapter;
import com.licheedev.serialtool.activity.dapter.RecyclerViewHolder;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.message.LogManager;
import com.licheedev.serialtool.util.LogPlus;
import com.licheedev.serialtool.util.SystemErrorsUtil;
import com.licheedev.serialtool.util.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CheckingActivity extends BaseActivity implements BaseRecyclerAdapter.Delegate{

    private ArrayList<String> errorList;
    private ArrayList<String> errorList2;
    private String sendok = "A1A2A3A4040011BBBB11";
//    @BindView(R.id.text_check)
//    TextView text_check;
    private int isDialog=0;
    int[] commandWorkMode = new int[]{0xA1, 0xA2, 0xA3, 0xA4,/*STX 4byte*/
            0x12, 0x00,/*size 2byte*/
            0x21,/*CMD 1byte*/
            0x02, 0x00, 0x01, 0x08, 0x00, 0x04, 0x02, 0x06, 0x01, 0x00, 0x01, 0x01, 0x01, 0x05,/*DATA1 14byte*/
            0xBB, 0xBB, 0x39}; //进入工作模式
    private SystemErrorsUtil systemErrorsUtil=null;
    private RecyclerView recyclerview;
    private  BaseRecyclerAdapter adapter;
    private static List<CheckBean> list=new ArrayList<>();
    private Button btnBack;
    private Button btLogout;
    private static String hexstr1=null;
    private static String check_pocket_sensor_right;
    private static String check_pocket_sensor_left;
    private static String check_pocket_sensor_middle;
    private static String check_hopper;
    private static String check_safe_door_closing;
    private static String check_vibration_alarm;
    private static String check_pocket_closing;
    private static String check_temporary_pocket_opening;
    private static String check_box_in_position;
    private static String check_pocket_opening;
    private static String check_reject_pocket;
    private static String check_temporary_pocket_closing;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_checking;
    }

    public static void getCheckState(byte[] received) {
        if (((char) (received[9] & 0xff) & 0x01) == 0x00) {
            hexstr1 = "PSO1 left";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PSO1 left";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[9] & 0xff) & 0x02) == 0x00) {
            hexstr1 = "PS01 right";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS01 right";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[9] & 0xff) & 0x04) == 0x00) {
            hexstr1 = "PS02 left";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS02 left";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[9] & 0xff) & 0x08) == 0x00) {
            hexstr1 = "PS02 right";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS02 right";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[9] & 0xff) & 0x10) == 0x00) {
            hexstr1 = "PS03 left1";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS03 left1";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[9] & 0xff) & 0x20) == 0x00) {
            hexstr1 = "PS03 left2";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS03 left2";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[9] & 0xff) & 0x40) == 0x00) {
            hexstr1 = "PS03 right2";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS03 right2";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[9] & 0xff) & 0x80) == 0x00) {
            hexstr1 = "PS03 right1";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS03 right1";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }

        if (((char) (received[10] & 0xff) & 0x01) == 0x00) {
            hexstr1 = "PS04";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS04";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[10] & 0xff) & 0x02) == 0x00) {
            hexstr1 = check_reject_pocket;
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = check_reject_pocket;
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[10] & 0xff) & 0x04) == 0x04) {
            hexstr1 = "PS06";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS06";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[10] & 0xff) & 0x08) == 0x01) {//非正常
            hexstr1 = check_pocket_opening;
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[10] & 0xff) & 0x10) == 0x00) {
            hexstr1 = "PS08 left";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS08 left";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[10] & 0xff) & 0x20) == 0x00) {
            hexstr1 = "PS08 right";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS08 right";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }

        if (((char) (received[11] & 0xff) & 0x01) == 0x01) {
            hexstr1 = check_box_in_position;
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = check_box_in_position;
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[11] & 0xff) & 0x02) == 0x01) {//非正常
            hexstr1 = check_temporary_pocket_opening;
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[11] & 0xff) & 0x04) == 0x01) {//正常
            hexstr1 = check_temporary_pocket_closing;
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }
        if (((char) (received[11] & 0xff) & 0x08) == 0x01) {
            hexstr1 = check_pocket_closing;
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }
        if (((char) (received[11] & 0xff) & 0x10) == 0x10) {
            hexstr1 = check_vibration_alarm;
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = check_vibration_alarm;
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[11] & 0xff) & 0x20) == 0x20) {
            hexstr1 = check_safe_door_closing;
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = check_safe_door_closing;
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[11] & 0xff) & 0x40) == 0x00) {
            hexstr1 = "PS09 left";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS09 left";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[11] & 0xff) & 0x80) == 0x00) {
            hexstr1 = "PS09 right";
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS09 right";
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }

        if (((char) (received[12] & 0xff) & 0x01) == 0x01) {//非正常
            hexstr1 = check_hopper;
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }else {
            hexstr1 = check_hopper;
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }
        if (((char) (received[12] & 0xff) & 0x02) == 0x00) {//非正常
            hexstr1 = "PS05 "+check_pocket_sensor_middle;
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS05 "+check_pocket_sensor_middle;
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[12] & 0xff) & 0x04) == 0x00) {//非正常
            hexstr1 = "PS05 "+check_pocket_sensor_left;
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS05 "+check_pocket_sensor_left;
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        if (((char) (received[12] & 0xff) & 0x08) == 0x00) {//非正常
            hexstr1 = "PS05 "+check_pocket_sensor_right;
            CheckBean checkBean = new CheckBean(hexstr1, 1);
            list.add(checkBean);
        }else {
            hexstr1 = "PS05 "+check_pocket_sensor_right;
            CheckBean checkBean = new CheckBean(hexstr1, 0);
            list.add(checkBean);
        }
        LogPlus.d("wwwww",list.size()+"");
    }
    private void getContent(){
//        check_pocket_sensor_right = content(R.string.check_pocket_sensor_right);
//        check_pocket_sensor_left = content(R.string.check_pocket_sensor_left);
//        check_pocket_sensor_middle = content(R.string.check_pocket_sensor_middle);
//        check_hopper = content(R.string.check_hopper);
//        check_safe_door_closing = content(R.string.check_safe_door_closing);
//        check_vibration_alarm = content(R.string.check_vibration_alarm);
//        check_pocket_closing = content(R.string.check_pocket_closing);
//        check_temporary_pocket_opening = content(R.string.check_temporary_pocket_opening);
//        check_box_in_position = content(R.string.check_box_in_position);
//        check_pocket_opening = content(R.string.check_pocket_opening);
//        check_reject_pocket = content(R.string.check_reject_pocket);
//        check_temporary_pocket_closing = content(R.string.check_temporary_pocket_closing);
    }


    @Override
    protected void initView() {
        super.initView();
        check_pocket_sensor_right = content(R.string.check_pocket_sensor_right);
        check_pocket_sensor_left = content(R.string.check_pocket_sensor_left);
        check_pocket_sensor_middle = content(R.string.check_pocket_sensor_middle);
        check_hopper = content(R.string.check_hopper);
        check_safe_door_closing = content(R.string.check_safe_door_closing);
        check_vibration_alarm = content(R.string.check_vibration_alarm);
        check_pocket_closing = content(R.string.check_pocket_closing);
        check_temporary_pocket_opening = content(R.string.check_temporary_pocket_opening);
        check_box_in_position = content(R.string.check_box_in_position);
        check_pocket_opening = content(R.string.check_pocket_opening);
        check_reject_pocket = content(R.string.check_reject_pocket);
        check_temporary_pocket_closing = content(R.string.check_temporary_pocket_closing);
        if (systemErrorsUtil==null){
            systemErrorsUtil = new SystemErrorsUtil(this);
        }
        recyclerview = findViewById(R.id.recyclerview);
//        btnBack = findViewById(R.id.btnBack);
        btLogout = findViewById(R.id.btLogout);
//        btnBack.setText(getResources().getString(R.string.retry));
        btLogout.setText(getResources().getString(R.string.skip));
        SerialPortManager.instance().initDevice();
    }

    @Override
    public void initListener() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        adapter = new BaseRecyclerAdapter(this, this);
        recyclerview.setAdapter(adapter);

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckingActivity.this,LoginActivity.class));
            }
        });
//        btnBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SerialPortManager.instance().sendCommand(sendok);
//                list.clear();
//            }
//        });
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
        SerialPortManager.instance().sendCommand(sendok);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SerialPortManager.instance().close();
    }

    @Override
    public List<CheckBean> getData() {
        return list;
    }

    @Override
    public int getView(ViewGroup parent, int viewType) {
        return R.layout.checking_dialog;
    }

    @Override
    public <T> void bindView(RecyclerViewHolder holder, int position, T item) {
        View viewTop = holder.getView(R.id.viewTop);
        View viewBottom = holder.getView(R.id.viewBottom);
        if (position == 0) {
            viewTop.setVisibility(View.GONE);
        } else {
            viewTop.setVisibility(View.VISIBLE);
        }
        CheckBean checkBean = (CheckBean) item;
        TextView sensor_name = (TextView) holder.getView(R.id.sensor_name);
        TextView state = (TextView) holder.getView(R.id.state);
        sensor_name.setText(checkBean.count);
        if (checkBean.state==1){
            state.setText(content(R.string.normal));
            state.setTextColor(Color.WHITE);
            sensor_name.setTextColor(Color.WHITE);
        }else {
            state.setText(content(R.string.abnormal));
            state.setTextColor(Color.RED);
            sensor_name.setTextColor(Color.RED);
        }

        if (position == list.size() - 1) {
            viewBottom.setVisibility(View.VISIBLE);
        } else {
            viewBottom.setVisibility(View.GONE);
        }
    }

    public static class CheckBean implements Serializable {
        public String count;
        public int state;

        public  CheckBean(String count, int state) {
            this.count = count;
            this.state = state;
        }
    }
    public String content(int system_error) {
        String string = getResources().getString(system_error);
        return string;
    }
}
