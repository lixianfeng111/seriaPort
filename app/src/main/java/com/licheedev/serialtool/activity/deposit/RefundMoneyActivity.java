package com.licheedev.serialtool.activity.deposit;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
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
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.licheedev.serialtool.util.constant.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RefundMoneyActivity extends BaseActivity implements BaseRecyclerAdapter.Delegate {
   private RecyclerView recyclerview;
   private BaseRecyclerAdapter adapter;
    private Button btnBack;
    private List<RefundMoneyBean> list=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.acticity_refund_money;
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerview = findViewById(R.id.recyclerview_refund);
        btnBack = findViewById(R.id.btnBack);
        list = SpzUtils.getErrorReasonList(this, Constant.LIST_ERROR);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(manager);
        manager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new BaseRecyclerAdapter(this, this);
        recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                list.clear();
                finish();
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

    @Override
    public List<RefundMoneyBean> getData() {
        return list;
    }

    @Override
    public int getView(ViewGroup parent, int viewType) {
        return R.layout.refund_money_item;
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
        RefundMoneyBean refundMoneyBean = (RefundMoneyBean) item;
        TextView user_number = (TextView) holder.getView(R.id.user_number2);
        TextView refund_money_because = (TextView) holder.getView(R.id.refund_money_because2);
        user_number.setText(refundMoneyBean.count+"");
        refund_money_because.setText(refundMoneyBean.reason);
        if (position == list.size() - 1) {
            viewBottom.setVisibility(View.VISIBLE);
        } else {
            viewBottom.setVisibility(View.GONE);
        }

    }

    public static class RefundMoneyBean implements Serializable {
        public int count;
        public String reason;

        public  RefundMoneyBean(int count, String reason) {
            this.count = count;
            this.reason = reason;
        }
    }
}
