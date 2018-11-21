package com.spiderman.example.ui.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.spiderman.example.R;


/**
 * Created by admin on 2016/6/20.
 */
public class ShareDialog extends Dialog {

    private String mTitle;
    private static Context mContext;
    private static ShareDialog mLoadingDialog;

    public ShareDialog(Context context, String title) {
        super(context, R.style.loadingDialogStyle);
        mTitle = title;
        mContext =context;
    }

    public static ShareDialog getInstance(Context context, String title){
        if(mLoadingDialog == null && context != null){
            mLoadingDialog = new ShareDialog(context, title);
        }
        return mLoadingDialog;
    }

    public static void showLoadingDialog(Context context, String title){
        if(context == null && mContext != null){
            return;
        }
        // 产生背景变暗效果
        WindowManager.LayoutParams lp = ((Activity)context).getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity)context).getWindow().setAttributes(lp);
        if(mLoadingDialog == null){
            mLoadingDialog = new ShareDialog(context, title);
            mLoadingDialog.show();
        }else{
            if(!mLoadingDialog.isShowing()){
                mLoadingDialog.show();
            }
        }
    }

    public static void hideLoadingDialog(){
        if(mContext != null && mLoadingDialog != null && mLoadingDialog.isShowing()){
            WindowManager.LayoutParams lp = ((Activity)mContext).getWindow()
                    .getAttributes();
            lp.alpha = 1f;
            ((Activity)mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            ((Activity)mContext).getWindow().setAttributes(lp);
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popuwindow_share);
        //LinearLayout linearLayout = (LinearLayout)this.findViewById(R.id.LinearLayout);
        //linearLayout.getBackground().setAlpha(0);
        findViewById(R.id.share_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideLoadingDialog();
            }
        });
        this.setCanceledOnTouchOutside(false);
        this.setCancelable(false);
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                //hideLoadingDialog();
                //break;
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }
}
