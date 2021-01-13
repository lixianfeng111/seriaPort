package com.licheedev.serialtool.activity.manage.maintain;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.Device;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.SerialPortManager2;
import com.licheedev.serialtool.comn.event.BootEvent;
import com.licheedev.serialtool.comn.event.SystemInfoEvent;
import com.licheedev.serialtool.comn.event.ZPKEvent;
import com.licheedev.serialtool.util.LogPlus;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.licheedev.serialtool.util.constant.Constant;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 系统详细信息
 */
public class SysDetailsInfoActivity extends BaseActivity {

    @BindView(R.id.btnUpload)
    Button btnUpload;
    @BindView(R.id.editIp)
    TextView editIp;
    @BindView(R.id.device_num)
    TextView device_num;
    @BindView(R.id.software_num)
    TextView software_num;
    @BindView(R.id.mainBoard_No)
    TextView mainBoard_No;
    @BindView(R.id.image_num)
    TextView image_num;
    private Device mDevice;
    int[] command8=new  int[]{0xA1,0xA2,0xA3,0xA4,0x04,0x00,0x90,0xBB,0xBB,0x90};
    private String s;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_systemdetails_info;
    }

    @Override
    public void initListener() {

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
        SerialPortManager.instance().sendCommand(SerialPortManager.byteArrayToHexString(command8));
    }

    //系列号
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void systemInfo(SystemInfoEvent systemInfoEvent){
        String system_info = systemInfoEvent.getSystem_info();
        editIp.setText(system_info);
        SpzUtils.putString(Constant.SN,system_info);
    }

    //图像
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void zpkEvent(ZPKEvent zpkEvent){
        image_num.setText(zpkEvent.getZpk());
    }

    //主板
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void bootEvent(BootEvent bootEvent){
        mainBoard_No.setText(bootEvent.getBoot());
    }

    @Override
    protected void initView() {
        super.initView();
        SerialPortManager2.instance().close();
        mDevice = new Device(Constant.PORT_MONEY, Constant.BAUD_RATE_MONEY);
        SerialPortManager.instance().open(mDevice);
        btnUpload.setText(getResources().getString(R.string.set));
        software_num.setText(getAppVersion());
        device_num.setText("DE3000");
    }

    @OnClick({R.id.btnBack, R.id.btnUpload, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnUpload:
                break;
            case R.id.btLogout:
                break;

        }
    }
    /**

     * 获取版本号

     * @return 当前应用的版本号

     */

    public String getAppVersion() {

        try {

            PackageManager manager = this.getPackageManager();

            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);

            String version = info.versionName;

//            return "版本：" + version;
            return version;

        } catch (Exception e) {

            e.printStackTrace();

//            return "找不到版本号";
            return "";
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SerialPortManager.instance().close();
    }
}
