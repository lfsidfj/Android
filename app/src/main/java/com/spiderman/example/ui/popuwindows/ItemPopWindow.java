package com.spiderman.example.ui.popuwindows;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.spiderman.example.R;
import com.spiderman.example.util.zb.StringUtil;


/**
 * Created by HP on 2018/5/2.
 */

public class ItemPopWindow extends PopupWindow implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
            listener.OnPopupWindowItemClick(requestCode, position, adapter == null ? null : adapter.getItem(position));
        }
        this.dismiss();
    }

    public interface OnPopupWindowItemClickListener {

        /**点击item事件的回调方法
         * @param requestCode 传入的用于区分某种情况下的showDialog
         * @param position
         * @param item
         */
        void OnPopupWindowItemClick(int requestCode, int position, String item);
    }

    private Context context;
    private String[] items;
    private String title;

    private int requestCode;
    private OnPopupWindowItemClickListener listener;

    private TextView tvItemDialogTitle;
    private ListView lvItemDialog;
    private ArrayAdapter<String> adapter;

    private View conentView;
    int h;
    int w;

    public ItemPopWindow(final Activity context,String[] items,String title,int requestCode,OnPopupWindowItemClickListener listener) {
        this.items=items;
        this.title=title;
        this.requestCode=requestCode;
        this.listener=listener;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.item_dialog, null);
        h = context.getWindowManager().getDefaultDisplay().getHeight();
        w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //this.setHeight(h);
        // 产生背景变暗效果
        WindowManager.LayoutParams lp = context.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
        this.setTouchable(true);
        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.anim.bottom_push_in);

        this.setOnDismissListener(new OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = context.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                context.getWindow().setAttributes(lp);
            }
        });

        tvItemDialogTitle = (TextView) conentView.findViewById(R.id.tvItemDialogTitle);
        lvItemDialog = (ListView) conentView.findViewById(R.id.lvItemDialog);

        tvItemDialogTitle.setVisibility(StringUtil.isNotEmpty(title, true) ? View.VISIBLE : View.GONE);
        tvItemDialogTitle.setText("" + StringUtil.getCurrentString());

        adapter = new ArrayAdapter<String>(context, R.layout.item_dialog_item, items);
        lvItemDialog.setAdapter(adapter);
        lvItemDialog.setOnItemClickListener(this);
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindowAsDropDown(View parent) {
        if (!this.isShowing()) {
            this.setWidth((int)(w/1.2));
            //this.setHeight(h/2);
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, (w-this.getWidth())/2, -h/2);
            //this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        } else {
            this.dismiss();
        }
    }
    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindowInBottom(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            //this.showAsDropDown(parent, 80, 80);
            this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        } else {
            this.dismiss();
        }
    }
}
