package com.spiderman.example.ui.popuwindows;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.spiderman.example.R;
import com.spiderman.example.util.DisplayUtil;


/**
 * Created by HP on 2018/5/2.
 */

public class MorePopWindow extends PopupWindow {
    private View conentView;
    int h;
    int w;

    public MorePopWindow(final Activity context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.alert_more, null);
        h = context.getWindowManager().getDefaultDisplay().getHeight();
        w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(DisplayUtil.dp2px(context,138));
        //this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
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
       // this.setAnimationStyle(R.style.AnimationPreview);

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

        /*TextView addTaskLayout = (TextView) conentView
                .findViewById(R.id.share_cancel);
        addTaskLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MorePopWindow.this.dismiss();
            }
        });*/
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindowAsDropDown(View parent) {
        if (!this.isShowing()) {
            this.setWidth((int)(w/1.2));
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, (w-this.getWidth())/2, 0);
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
