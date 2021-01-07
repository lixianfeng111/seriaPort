package com.licheedev.serialtool.activity.deposit;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.deposit.bean.ListBean;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.activity.dapter.BaseRecyclerAdapter;
import com.licheedev.serialtool.activity.dapter.RecyclerViewHolder;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.comn.message.LogManager;
import com.licheedev.serialtool.dialog.CurrenySelectUtil;
import com.licheedev.serialtool.util.LogPlus;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.licheedev.serialtool.util.constant.Constant;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.licheedev.serialtool.activity.deposit.PaperCurrencyDepositActivity.REQUEST_CODE_DEPOSIT;
import static com.licheedev.serialtool.activity.deposit.PaperCurrencyDepositActivity.RESULT_CODE_DEPOSIT;
import static com.licheedev.serialtool.comn.message.LogManager.SAVE_SUCCESS_COMMAND;
import static com.licheedev.serialtool.util.constant.Money.Denomination_100_CNY;
import static com.licheedev.serialtool.util.constant.Money.Denomination_10_CNY;
import static com.licheedev.serialtool.util.constant.Money.Denomination_1_CNY;
import static com.licheedev.serialtool.util.constant.Money.Denomination_20_CNY;
import static com.licheedev.serialtool.util.constant.Money.Denomination_50_CNY;
import static com.licheedev.serialtool.util.constant.Money.Denomination_5_CNY;

/**
 * 存款明细
 */
public class DepositDetailsActivity extends BaseActivity implements BaseRecyclerAdapter.Delegate {

    @BindView(R.id.textView)
    TextView tvTitle;
    @BindView(R.id.tvPresentCount)
    TextView tvPresentCount;
    @BindView(R.id.tvPresentSum)
    TextView tvPresentSum;
    @BindView(R.id.tvAlreadyCount)
    TextView tvAlreadyCount;
    @BindView(R.id.tvAlreadySum)
    TextView tvAlreadySum;
    private Dialog overDepositDialogdialog;
    private BaseRecyclerAdapter adapter;
    private List<DepositDetailBean> list=new ArrayList<>();
    private ListBean listBean=new ListBean(list);
    private List<DepositDetailBean> list2;
    private int num;
    private int counts;
    private int n=0;

    @Override
    protected void initView() {
        super.initView();
        RecyclerView recyclerview_detail = findViewById(R.id.recyclerview_detail);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerview_detail.setLayoutManager(manager);
        manager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new BaseRecyclerAdapter(this, this);
        recyclerview_detail.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_deposit_details;
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
        list2 = SpzUtils.getDataList(this, Constant.LIST);
        num = SpzUtils.getInt(Constant.NUM, 0);
        counts = SpzUtils.getInt(Constant.COUNTS, 0);
    }

    @OnClick({R.id.btnOverDeposit, R.id.btnContiniu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnOverDeposit: {
                overDepositDialogdialog = CurrenySelectUtil.showOverDepositDialog(this, new PaperCurrencyDepositActivity.Callback() {
                    @Override
                    public void onDialogClick(int which, Dialog dialog) {
                        if (which == 1) {
                            if (list2.size()!=0){
                                list.addAll(list2);
                            }
                            SpzUtils.setDataList(DepositDetailsActivity.this,Constant.LIST, list);
                            SerialPortManager.instance().sendSaveAck();
                            setResult(RESULT_CODE_DEPOSIT);
                            finish();
                        }
                    }
                });
            }
            break;
            case R.id.btnContiniu: {
                if (list2.size()!=0){
                    list.addAll(list2);
                }
                SpzUtils.setDataList(DepositDetailsActivity.this,Constant.LIST, list);
                SerialPortManager.instance().sendSaveAck();
//                SpzUtils.putBoolean("is_continue",true);
                finish();
            }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CODE_DEPOSIT) {
            finish();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LogManager.ReceiveData data) {
        byte[] received = data.data;
        switch (data.what) {
            case SAVE_SUCCESS_COMMAND: {
                amountReceiveMoney(data.data);
                amountSaveMoney(data.data);
            }
            break;
        }
    }


    private void amountSaveMoney(byte[] received) {
        int CNY_100 = received[29] + (received[30] << 8);
        int CNY_50 = received[31] + (received[32] << 8);
        int CNY_20 = received[33] + (received[34] << 8);
        int CNY_10 = received[35] + (received[36] << 8);
        int CNY_5 = received[37] + (received[38] << 8);
        int CNY_1 = received[39] + (received[40] << 8);
        final String amount = " 共存  100x" + CNY_100 + " 50x" +
                CNY_50 + " 20x" + CNY_20 + " 10x" + CNY_10 + " 5x" + CNY_5 + " 1x" + CNY_1;

        LogPlus.e("read_thread", amount);
        long sum = CNY_100 * Denomination_100_CNY +
                CNY_50 * Denomination_50_CNY +
                CNY_20 * Denomination_20_CNY +
                CNY_10 * Denomination_10_CNY +
                CNY_5 * Denomination_5_CNY +
                CNY_1 * Denomination_1_CNY;
        long count = CNY_100 +
                CNY_50 +
                CNY_20 +
                CNY_10 +
                CNY_5 +
                CNY_1;

//        tvAlreadySum.setText(sum + "");
//        tvAlreadyCount.setText(count + "");
    }

    private void amountReceiveMoney(byte[] received) {
        if (list != null) {
            list.clear();
        }

        int CNY_100 = received[9] + (received[10] << 8);
        int CNY_50 = received[11] + (received[12] << 8);
        int CNY_20 = received[13] + (received[14] << 8);
        int CNY_10 = received[15] + (received[16] << 8);
        int CNY_5 = received[17] + (received[18] << 8);
        int CNY_1 = received[19] + (received[20] << 8);

        final String amount = " 收到  100x" + CNY_100 + " 50x" +
                CNY_50 + " 20x" + CNY_20 + " 10x" + CNY_10 + " 5x" + CNY_5 + " 1x" + CNY_1;
        LogPlus.e("read_thread", amount);

        if (CNY_100 > 0) {
            DepositDetailBean depositDetailBean = new DepositDetailBean(Denomination_100_CNY, CNY_100);
            listBean.getList().add(depositDetailBean);
        }
        if (CNY_50 > 0) {
            DepositDetailBean depositDetailBean = new DepositDetailBean(Denomination_50_CNY, CNY_50);
            listBean.getList().add(depositDetailBean);

        }
        if (CNY_20 > 0) {
            DepositDetailBean depositDetailBean = new DepositDetailBean(Denomination_20_CNY, CNY_20);
            listBean.getList().add(depositDetailBean);

        }
        if (CNY_10 > 0) {
            DepositDetailBean depositDetailBean = new DepositDetailBean(Denomination_10_CNY, CNY_10);
            listBean.getList().add(depositDetailBean);

        }
        if (CNY_5 > 0) {
            DepositDetailBean depositDetailBean = new DepositDetailBean(Denomination_5_CNY, CNY_5);
            listBean.getList().add(depositDetailBean);

        }
        if (CNY_1 > 0) {
            DepositDetailBean depositDetailBean = new DepositDetailBean(Denomination_1_CNY, CNY_1);
            listBean.getList().add(depositDetailBean);
        }
        long sum = CNY_100 * Denomination_100_CNY +
                CNY_50 * Denomination_50_CNY +
                CNY_20 * Denomination_20_CNY +
                CNY_10 * Denomination_10_CNY +
                CNY_5 * Denomination_5_CNY +
                CNY_1 * Denomination_1_CNY;
        long count = CNY_100 +
                CNY_50 +
                CNY_20 +
                CNY_10 +
                CNY_5 +
                CNY_1;

        tvPresentSum.setText(sum + "");
        tvPresentCount.setText(count + "");

        if (n==0){
            num+=sum;
            counts+=count;
            if (sum>0){
                SpzUtils.putBoolean(Constant.IS_PRINT, true);
            }
            tvAlreadySum.setText(num + "");
            tvAlreadyCount.setText(counts + "");
            n++;
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

//        if (sum>0){
//            SpzUtils.putBoolean("isPrint", true);
//        }
        //保存已存累计
        SpzUtils.putInt(Constant.NUM,num);
        SpzUtils.putInt(Constant.COUNTS,counts);
        //记录
        SpzUtils.putInt(Constant.MONEY_RECORD,num);//金额
        SpzUtils.putInt(Constant.CURRENCY_RECORD,2);//币种 2是钞票
        SpzUtils.putBoolean(Constant.IS_SAVED,false);
    }


    @Override
    public List<DepositDetailBean> getData() {
        return list;
    }

    @Override
    public int getView(ViewGroup parent, int viewType) {
        return R.layout.item_deposit_details;
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

        DepositDetailBean data = (DepositDetailBean) item;
        TextView tvDeposit = (TextView) holder.getView(R.id.tvDeposit);
        TextView tvCount = (TextView) holder.getView(R.id.tvCount);
        TextView tvSum = (TextView) holder.getView(R.id.tvSum);
        tvDeposit.setText(data.deposit + "");
        tvCount.setText(data.count + "");
        tvSum.setText(data.sum + "");

        if (position == list.size() - 1) {
            viewBottom.setVisibility(View.VISIBLE);
        } else {
            viewBottom.setVisibility(View.GONE);
        }

    }



    public static class DepositDetailBean implements Serializable{
        public int deposit;
        public int count;
        public int sum;

        public DepositDetailBean(int deposit, int count) {
            this.deposit = deposit;
            this.count = count;
            this.sum = deposit * count;
        }
    }

}
