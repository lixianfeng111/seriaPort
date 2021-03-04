package com.licheedev.serialtool.activity.manage.print;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.licheedev.serialtool.AppManager;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.base.BaseActivity;
import com.licheedev.serialtool.base.BasePresenter;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.constant.Constant;

import butterknife.BindView;

public class BagActivity extends BaseActivity {

    //张数
    @BindView(R.id.piece_100)
    TextView piece_100;
    @BindView(R.id.piece_50)
    TextView piece_50;
    @BindView(R.id.piece_20)
    TextView piece_20;
    @BindView(R.id.piece_10)
    TextView piece_10;
    @BindView(R.id.piece_5)
    TextView piece_5;
    @BindView(R.id.piece_1)
    TextView piece_1;

    //金额
    @BindView(R.id.piece100_sum)
    TextView piece100_sum;
    @BindView(R.id.piece50_sum)
    TextView piece50_sum;
    @BindView(R.id.piece20_sum)
    TextView piece20_sum;
    @BindView(R.id.piece10_sum)
    TextView piece10_sum;
    @BindView(R.id.piece5_sum)
    TextView piece5_sum;
    @BindView(R.id.piece1_sum)
    TextView piece1_sum;

    //其他存款
    @BindView(R.id.coin_sum)
    TextView coin_sum;
    @BindView(R.id.check_sum)
    TextView check_sum;
    @BindView(R.id.banknote_sum)
    TextView banknote_sum;
    @BindView(R.id.other_sum)
    TextView other_sum;

    //返回按钮
    @BindView(R.id.btnBack)
    Button btnBack;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bag;
    }

    @Override
    public void initListener() {

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void initVariable() {
        setData();
    }

    private void setData() {
        int num=0,total=0,num_mxn=0,total_mxn=0,totalAll=0,totalAll_mxn=0;
        //获取人民币各个面值的张数
        int num100 = SpzUtils.getInt(Constant.NUM100, 0);
        int num50 = SpzUtils.getInt(Constant.NUM50, 0);
        int num20 = SpzUtils.getInt(Constant.NUM20, 0);
        int num10 = SpzUtils.getInt(Constant.NUM10, 0);
        int num5 = SpzUtils.getInt(Constant.NUM5, 0);
        int num1 = SpzUtils.getInt(Constant.NUM1, 0);
        //获取墨西哥各个面值的张数
        int num_mxn1000 = SpzUtils.getInt(Constant.NUM_MXN1000, 0);
        int num_mxn500 = SpzUtils.getInt(Constant.NUM_MXN500, 0);
        int num_mxn200 = SpzUtils.getInt(Constant.NUM_MXN200, 0);
        int num_mxn100 = SpzUtils.getInt(Constant.NUM_MXN100, 0);
        int num_mxn50 = SpzUtils.getInt(Constant.NUM_MXN50, 0);
        int num_mxn20 = SpzUtils.getInt(Constant.NUM_MXN20, 0);
        //获取各面值总金额
        int money100 = SpzUtils.getInt(Constant.MONEY100, 0);
        int money50 = SpzUtils.getInt(Constant.MONEY50, 0);
        int money20 = SpzUtils.getInt(Constant.MONEY20, 0);
        int money10 = SpzUtils.getInt(Constant.MONEY10, 0);
        int money5 = SpzUtils.getInt(Constant.MONEY5, 0);
        int money1 = SpzUtils.getInt(Constant.MONEY1, 0);
        //获取各面值总金额
        int money_mxn1000 = SpzUtils.getInt(Constant.MONEY_MXN1000, 0);
        int money_mxn500 = SpzUtils.getInt(Constant.MONEY_MXN500, 0);
        int money_mxn200 = SpzUtils.getInt(Constant.MONEY_MXN200, 0);
        int money_mxn100 = SpzUtils.getInt(Constant.MONEY_MXN100, 0);
        int money_mxn50 = SpzUtils.getInt(Constant.MONEY_MXN50, 0);
        int money_mxn20 = SpzUtils.getInt(Constant.MONEY_MXN20, 0);
        //获取其他存款
        int coin2 = SpzUtils.getInt(Constant.COIN2, 0);
        int cheque2 = SpzUtils.getInt(Constant.CHEQUE2, 0);
        int paper_money2 = SpzUtils.getInt(Constant.PAPER_MONEY2, 0);
        int other2 = SpzUtils.getInt(Constant.OTHER2, 0);
        //获取墨西哥币其他存款
        int coin2_mxn = SpzUtils.getInt(Constant.COIN2_MXN, 0);
        int cheque2_mxn = SpzUtils.getInt(Constant.CHEQUE2_MXN, 0);
        int paper_money2_mxn = SpzUtils.getInt(Constant.PAPER_MONEY2_MXN, 0);
        int other2_mxn = SpzUtils.getInt(Constant.OTHER2_MXN, 0);
        //得到钞票总张数
        num=num1+num5+num10+num20+num50+num100;
        //得到钞票总金额
        total=money1+money5+money10+money20+money50+money100;
        //总计
        totalAll=total+coin2+cheque2+paper_money2+other2;
        //得到墨西哥币钞票总张数
        num_mxn=num_mxn20+num_mxn50+num_mxn100+num_mxn200+num_mxn500+num_mxn1000;
        //得到墨西哥币钞票总金额
        total_mxn=money_mxn20+money_mxn50+money_mxn100+money_mxn200+money_mxn500+money_mxn1000;
        //得到墨西哥币总计
        totalAll_mxn=total_mxn+coin2_mxn+cheque2_mxn+paper_money2_mxn+other2_mxn;

        piece_100.setText(num100+"");
        piece_50.setText(num50+"");
        piece_20.setText(num20+"");
        piece_10.setText(num10+"");
        piece_5.setText(num5+"");
        piece_1.setText(num1+"");
        piece100_sum.setText(money100+"");
        piece50_sum.setText(money50+"");
        piece20_sum.setText(money20+"");
        piece10_sum.setText(money10+"");
        piece5_sum.setText(money5+"");
        piece1_sum.setText(money1+"");

        coin_sum.setText(coin2+"");
        check_sum.setText(cheque2+"");
        banknote_sum.setText(paper_money2+"");
        other_sum.setText(other2+"");
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initData() {

    }
}
