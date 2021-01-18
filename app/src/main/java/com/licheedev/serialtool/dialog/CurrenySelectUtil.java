package com.licheedev.serialtool.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.licheedev.serialtool.App;
import com.licheedev.serialtool.R;
import com.licheedev.serialtool.activity.clear.ClearDeviceHintActivity;
import com.licheedev.serialtool.activity.dapter.CurrenySelectAdapter;
import com.licheedev.serialtool.activity.deposit.PaperCurrencyDepositActivity;
import com.licheedev.serialtool.comn.SerialPortManager;
import com.licheedev.serialtool.util.GetCurrencyUtil;
import com.licheedev.serialtool.util.LanguageUtils;
import com.licheedev.serialtool.util.SpzUtils;
import com.licheedev.serialtool.util.ToastUtil;
import com.licheedev.serialtool.util.constant.Constant;
import com.licheedev.serialtool.util.constant.Money;

import java.util.Arrays;
import java.util.List;

public class CurrenySelectUtil {

    private static List<String> stringlist;

    /**
     * 币种选择弹窗
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void showCurreny(final Context context, final PaperCurrencyDepositActivity.Callback callback) {
//        final boolean isPrint = SpzUtils.getBoolean(Constant.IS_PRINT, false);
        final AlertDialog alertDialog = new AlertDialog
                .Builder(context)
                .create();
        if (!SpzUtils.getBoolean(Constant.LANGUAGE,false)){//判断是否为中文
            stringlist = Arrays.asList(Money.CURRENCY_ARRAY_en);
        }else {
            stringlist = Arrays.asList(Money.CURRENCY_ARRAY);
        }
        final CurrenySelectAdapter adapter = new CurrenySelectAdapter(context, stringlist);
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.currency_select_view, null, false);
        RecyclerView recyclerView = view.findViewById(R.id.currencyList);

        GridLayoutManager manager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setMyViewHolerClicks(new CurrenySelectAdapter.MyViewHolerClicks() {
            @Override
            public void onItemClick(int position) {
                callback.onDialogClick(position,alertDialog);
            }
        });

        alertDialog.setView(view);
        alertDialog.show();
        alertDialog.getWindow().setLayout(400, 210);
//        alertDialog.getWindow().setLayout(350, 190);
    }

    public static Dialog showContinueDepositDialog(Context context, final PaperCurrencyDepositActivity.Callback callback) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.deposit_continue_view, null, false);
        ImageButton btConfirm = view.findViewById(R.id.btConfirm);
        ImageButton btCancel = view.findViewById(R.id.btCancel);

        final AlertDialog alertDialog = new AlertDialog
                .Builder(context)
                .create();
        alertDialog.setView(view);
        alertDialog.show();
        alertDialog.getWindow().setLayout(400, 210);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onDialogClick(0, alertDialog);
            }
        });
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                callback.onDialogClick(1, alertDialog);
            }
        });
        return alertDialog;
    }

    /**
     * 是否结束存款
     * @param context
     * @param callback
     * @return
     */
    public static Dialog showOverDepositDialog(Context context, final PaperCurrencyDepositActivity.Callback callback) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.deposit_over_view, null, false);
        ImageButton btConfirm = view.findViewById(R.id.btConfirm);
        ImageButton btCancel = view.findViewById(R.id.btCancel);

        final AlertDialog alertDialog = new AlertDialog
                .Builder(context)
                .create();
        alertDialog.setView(view);
        alertDialog.show();
        alertDialog.getWindow().setLayout(400, 210);

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                callback.onDialogClick(0, alertDialog);
            }
        });
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                callback.onDialogClick(1, alertDialog);
            }
        });
        return alertDialog;
    }

    public static Dialog showExitFailDialog(Context context, final PaperCurrencyDepositActivity.Callback callback, String message) {

        ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.store_money_tip_view, null, false);
        ImageButton btConfirm = view.findViewById(R.id.btConfirm);
        TextView tvMessage = view.findViewById(R.id.tvMessage);

        final AlertDialog alertDialog = new AlertDialog
                .Builder(context)
                .create();
        alertDialog.setView(view);
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setLayout(400, 210);
        tvMessage.setText(message);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                alertDialog.dismiss();
                callback.onDialogClick(1, alertDialog);
            }
        });
        return alertDialog;

    }


    /**
     * 确认退出
     * @param context
     * @param
     * @return
     */
    public static Dialog showQuitDialog(final Context context, final ClearDeviceHintActivity.Callback callback) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.clear_hint_quit, null, false);
        ImageButton btConfirm = view.findViewById(R.id.btConfirm);
        ImageButton btCancel = view.findViewById(R.id.btCancel);

        final AlertDialog alertDialog = new AlertDialog
                .Builder(context)
                .create();
        alertDialog.setView(view);
        alertDialog.show();
        alertDialog.getWindow().setLayout(400, 210);
//        alertDialog.getWindow().setLayout(300, 170);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                callback.onQuitDialogClick();
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        return alertDialog;
    }




    public static class DialogItemAdapter extends BaseAdapter {
        //这里可以传递个对象，用来控制不同的item的效果
        //比如每个item的背景资源，选中样式等
        public List<String> list;
        LayoutInflater inflater;

        public DialogItemAdapter(Context context, List<String> list) {
            this.list = list;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int i) {
            if (i == getCount() || list == null) {
                return null;
            }
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.dialog_item, null);
                holder.typeTextview = (TextView) convertView.findViewById(R.id.typeTextview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (holder.typeTextview != null) {
                holder.typeTextview.setText(getItem(position));
            }
            return convertView;
        }

        public static class ViewHolder {
            public TextView typeTextview;
        }
    }
}
