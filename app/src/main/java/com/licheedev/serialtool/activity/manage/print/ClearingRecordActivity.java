package com.licheedev.serialtool.activity.manage.print;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.LoginActivity;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.activity.dapter.BaseRecyclerAdapter;
import com.licheedev.serialtool.activity.dapter.RecyclerViewHolder;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.LogOutUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 清机记录
 */
public class ClearingRecordActivity extends BaseActivity implements BaseRecyclerAdapter.Delegate {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.btLogout)
    Button btnUpload;


    private BaseRecyclerAdapter adapter;
    private List<Integer> list = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clearing_record;
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

        btnUpload.setText(getResources().getString(R.string.print));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        adapter = new BaseRecyclerAdapter(this, this);
        mRecyclerView.setAdapter(adapter);

        list.add(1);
        list.add(1);
        list.add(1);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.btnBack,  R.id.btLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;

            case R.id.btLogout:
                LogOutUtil.LogOut(this, LoginActivity.class);
                finish();
                break;
        }
    }

    @Override
    public List<Integer> getData() {
        return list;
    }

    @Override
    public int getView(ViewGroup parent, int viewType) {
        return R.layout.item_clearing_record;
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
}
