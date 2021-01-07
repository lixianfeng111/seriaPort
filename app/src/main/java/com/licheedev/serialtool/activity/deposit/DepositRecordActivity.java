package com.licheedev.serialtool.activity.deposit;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.clear.TestFunction;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.activity.dapter.BaseRecyclerAdapter;
import com.licheedev.serialtool.activity.dapter.RecyclerViewHolder;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.TimeFormartUtils;
import com.licheedev.serialtool.util.constant.Constant;
import com.sun.jna.Pointer;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * 存款记录
 */
public class DepositRecordActivity extends BaseActivity implements BaseRecyclerAdapter.Delegate {


    private BaseRecyclerAdapter adapter;
    private List<DepositRecordBean> list = new ArrayList<>();
    private Button btnUpload;
    private Button btLogout;
    private TextView tvTitle;
    private RecyclerView recyclerview;
    private Pointer h=Pointer.NULL;
    private int currency_record;
    private String KIND;
    private int money_record;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_deposit_record;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initVariable() {

    }

    @Override
    protected void initView() {
        super.initView();
        btnUpload = findViewById(R.id.btnUpload);
        btLogout = findViewById(R.id.btLogout);
        tvTitle = findViewById(R.id.textView);
        recyclerview = findViewById(R.id.recyclerview);
        btnUpload.setText(getResources().getString(R.string.record_allprint));
        btLogout.setText(getResources().getString(R.string.record_updaloddata));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            btLogout.setAutoSizeTextTypeUniformWithConfiguration(4,26,2, TypedValue.COMPLEX_UNIT_SP);
        }

        boolean iscurrent = getIntent().getBooleanExtra(Constant.IS_CURRENT, false);
        currency_record = SpzUtils.getInt(Constant.CURRENCY_RECORD,0);
        money_record = SpzUtils.getInt(Constant.MONEY_RECORD, 0);
        if (iscurrent) {
            tvTitle.setText(getResources().getString(R.string.current_deposit));
        } else {
            tvTitle.setText(getResources().getString(R.string.print_deposit_record));
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        adapter = new BaseRecyclerAdapter(this, this);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        OpenPort();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initData() {
        if (currency_record==0){
            KIND=getResources().getString(R.string.coin);
        }else if (currency_record==1){
            KIND=getResources().getString(R.string.check);
        }else if (currency_record==2){
            KIND=getResources().getString(R.string.bill);
        }else if (currency_record==3){
            KIND=getResources().getString(R.string.other);
        }else {
            KIND=getResources().getString(R.string.rmb);
        }
        DepositRecordBean depositRecordBean=new DepositRecordBean(TimeFormartUtils.getTimeDay(),SpzUtils.getString("user"),KIND,money_record,"1234522331133","2813","hhh" );
        list.add(depositRecordBean);
    }


    @OnClick({R.id.btnBack, R.id.btnUpload, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnUpload: //汇总打印
                TestFunction.record_deposit_Print_SampleTicket(this,currency_record,money_record,h);
                break;
            case R.id.btLogout: //上传数据

                break;
        }
    }

    @Override
    public List<DepositRecordBean> getData() {
        return list;
    }

    @Override
    public int getView(ViewGroup parent, int viewType) {
        return R.layout.item_deposit_record;
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

        if (position == list.size() - 1) {
            viewBottom.setVisibility(View.VISIBLE);
        } else {
            viewBottom.setVisibility(View.GONE);
        }
        DepositRecordBean recordBean= (DepositRecordBean) item;
        TextView userName = (TextView) holder.getView(R.id.userName);
        TextView bankId = (TextView) holder.getView(R.id.bankId);
        TextView category = (TextView) holder.getView(R.id.category);
        TextView sum = (TextView) holder.getView(R.id.sum);
        TextView remark = (TextView) holder.getView(R.id.remark);
        TextView coding = (TextView) holder.getView(R.id.coding);
        TextView time = (TextView) holder.getView(R.id.time);
        userName.setText(recordBean.userName);
        bankId.setText(recordBean.bankId);
        sum.setText(recordBean.sum+"");
        category.setText(recordBean.category);
        remark.setText(recordBean.remark);
        coding.setText(recordBean.coding);
        time.setText(recordBean.time);
    }

    private void OpenPort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom(Constant.PORT, Constant.BAUD_RATE, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, AutoReplyPrint.CP_ComFlowControl_XonXoff, 0);
            }
        }).start();
    }
    private void ClosePort() {
        if (h != Pointer.NULL) {
            AutoReplyPrint.INSTANCE.CP_Port_Close(h);
            h = Pointer.NULL;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ClosePort();
    }

    private static class DepositRecordBean{
        private String time;
        private String userName;
        private String category;
        private int sum;
        private String bankId;
        private String coding;
        private String remark;


        public DepositRecordBean(String time, String userName, String category, int sum, String bankId, String coding, String remark) {
            this.time = time;
            this.userName = userName;
            this.category = category;
            this.sum = sum;
            this.bankId = bankId;
            this.coding = coding;
            this.remark = remark;
        }
    }
}