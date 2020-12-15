package com.licheedev.serialtool.activity.deposit;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.caysn.autoreplyprint.AutoReplyPrint;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.clear.TestFunction;
import com.licheedev.serialtool.activity.deposit.bean.ListBean;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.activity.dapter.BaseRecyclerAdapter;
import com.licheedev.serialtool.activity.dapter.RecyclerViewHolder;
import com.licheedev.serialtool.base.BasePresenter;
import com.sun.jna.Pointer;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * 存款记录
 */
public class DepositRecordActivity extends BaseActivity implements BaseRecyclerAdapter.Delegate {


    private BaseRecyclerAdapter adapter;
    private List<Integer> list = new ArrayList<>();
    private Button btnUpload;
    private Button btLogout;
    private TextView tvTitle;
    private RecyclerView recyclerview;
    private Pointer h=Pointer.NULL;
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
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initData() {

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
        boolean iscurrent = getIntent().getBooleanExtra("iscurrent", false);
        if (iscurrent) {
            tvTitle.setText(getResources().getString(R.string.current_deposit));
        } else {
            tvTitle.setText(getResources().getString(R.string.print_deposit_record));
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        adapter = new BaseRecyclerAdapter(this, this);
        recyclerview.setAdapter(adapter);

        list.add(1);
        list.add(1);
        list.add(1);
        adapter.notifyDataSetChanged();

        OpenPort();
    }

    @OnClick({R.id.btnBack, R.id.btnUpload, R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btnUpload: //汇总打印
                TestFunction.deposit_Print_SampleTicket(DepositRecordActivity.this, ListBean.getList(),h);
                break;
            case R.id.btLogout: //上传数据

                break;
        }
    }

    @Override
    public List<Integer> getData() {
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

    }
    private void OpenPort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                h = AutoReplyPrint.INSTANCE.CP_Port_OpenCom("/dev/ttyS3", 9600, AutoReplyPrint.CP_ComDataBits_8, AutoReplyPrint.CP_ComParity_NoParity, AutoReplyPrint.CP_ComStopBits_One, AutoReplyPrint.CP_ComFlowControl_XonXoff, 0);

            }
        }).start();
    }
}
