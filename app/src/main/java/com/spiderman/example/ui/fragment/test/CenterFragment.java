package com.spiderman.example.ui.fragment.test;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.spiderman.example.R;
import com.spiderman.example.ui.activity.test.BannerActivity;
import com.spiderman.example.ui.activity.test.CutPictureActivity;
import com.spiderman.example.ui.activity.test.DownLoadActivity;
import com.spiderman.example.ui.activity.test.DrawerLayoutActivity;
import com.spiderman.example.ui.activity.test.GenerateActivity;
import com.spiderman.example.ui.activity.test.GoodsListViewActivity;
import com.spiderman.example.ui.activity.test.GoodsRecyclerViewActivity;
import com.spiderman.example.ui.activity.test.GoodsXRecyclerViewActivity;
import com.spiderman.example.ui.activity.test.JpushNoNotitifyActivity;
import com.spiderman.example.ui.activity.test.LetterActivity;
import com.spiderman.example.ui.activity.test.PhotoViewActivity;
import com.spiderman.example.ui.activity.test.PickerViewActivity;
import com.spiderman.example.ui.activity.test.ScanActivity;
import com.spiderman.example.ui.activity.test.SelectPictureActivity;
import com.spiderman.example.ui.activity.test.UpdateVersionActivity;
import com.spiderman.example.ui.activity.test.WebViewActivity;
import com.spiderman.example.ui.dialog.AlertDialog;
import com.spiderman.example.ui.dialog.ItemDialog;
import com.spiderman.example.ui.dialog.ListPickerDialog;
import com.spiderman.example.ui.dialog.ShareDialog;
import com.spiderman.example.ui.fragment.HttpBaseFragment;
import com.spiderman.example.ui.popuwindows.ItemPopWindow;
import com.spiderman.example.ui.popuwindows.MorePopWindow;
import com.spiderman.example.ui.popuwindows.SharePopWindow;
import com.spiderman.example.util.ToastUtils;
import com.spiderman.example.util.http.OkGoEngine;
import com.spiderman.example.util.http.RequestCallBack;
import com.spiderman.example.util.zb.DataKeeper;
import com.spiderman.example.util.zb.StringUtil;

import java.io.File;

/**
 * Created by HP on 2018/4/24.
 */

public class CenterFragment extends HttpBaseFragment implements AlertDialog.OnDialogButtonClickListener,ItemDialog.OnDialogItemClickListener,ItemPopWindow.OnPopupWindowItemClickListener,View.OnLongClickListener {
    LinearLayout showAsDropDown;
    LinearLayout showAtLocation;
    LinearLayout showDialog;
    LinearLayout showAlertDialog;
    LinearLayout showItemDialog;
    LinearLayout showListPickerDialog;
    LinearLayout showMoreDialog;
    LinearLayout scanQRCode;
    LinearLayout generateQRCode;
    LinearLayout ipwshowAsDropDown;
    LinearLayout ipwshowAtLocation;
    LinearLayout selectPicture;
    LinearLayout cutPicture;
    LinearLayout webView;
    LinearLayout drawerLayout;
    LinearLayout banner;
    LinearLayout picker_selector;
    LinearLayout recyclerView;
    LinearLayout xRecyclerView;
    LinearLayout listView;
    LinearLayout jpushNoNotifyActivity;
    LinearLayout updateVersionActivity;
    LinearLayout letterActivity;
    LinearLayout phtoViewActivity;
    SharePopWindow addPopWindow;
    ImageView picture;
    String picturePath;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(R.layout.fragment_center,null);
        }
        initView(rootView);
        initData();
        return rootView;
    }

    private void initView(View rootView) {
        showAsDropDown= (LinearLayout) rootView.findViewById(R.id.showAsDropDown);
        showAtLocation= (LinearLayout) rootView.findViewById(R.id.showAtLocation);
        showDialog= (LinearLayout) rootView.findViewById(R.id.showDialog);
        showAlertDialog= (LinearLayout) rootView.findViewById(R.id.showAlertDialog);
        showItemDialog= (LinearLayout) rootView.findViewById(R.id.showItemDialog);
        showListPickerDialog= (LinearLayout) rootView.findViewById(R.id.showListPickerDialog);
        showMoreDialog= (LinearLayout) rootView.findViewById(R.id.showMoreDialog);
        scanQRCode= (LinearLayout) rootView.findViewById(R.id.scanQRCode);
        generateQRCode= (LinearLayout) rootView.findViewById(R.id.generateQRCode);
        ipwshowAsDropDown= (LinearLayout) rootView.findViewById(R.id.ipwshowAsDropDown);
        ipwshowAtLocation= (LinearLayout) rootView.findViewById(R.id.ipwshowAtLocation);
        selectPicture= (LinearLayout) rootView.findViewById(R.id.selectPicture);
        cutPicture= (LinearLayout) rootView.findViewById(R.id.cutPicture);
        picture= (ImageView) rootView.findViewById(R.id.picture);
        webView= (LinearLayout) rootView.findViewById(R.id.webView);
        drawerLayout= (LinearLayout) rootView.findViewById(R.id.drawerLayout);
        banner= (LinearLayout) rootView.findViewById(R.id.banner);
        picker_selector= (LinearLayout) rootView.findViewById(R.id.picker_selector);
        recyclerView= (LinearLayout) rootView.findViewById(R.id.recyclerView);
        xRecyclerView= (LinearLayout) rootView.findViewById(R.id.xRecyclerView);
        listView= (LinearLayout) rootView.findViewById(R.id.listView);
        jpushNoNotifyActivity= (LinearLayout) rootView.findViewById(R.id.jpushNoNotifyActivity);
        updateVersionActivity= (LinearLayout) rootView.findViewById(R.id.updateVersionActivity);
        letterActivity= (LinearLayout) rootView.findViewById(R.id.letterActivity);
        phtoViewActivity= (LinearLayout) rootView.findViewById(R.id.phtoViewActivity);
        showAsDropDown.setOnClickListener(this);
        showAtLocation.setOnClickListener(this);
        showDialog.setOnClickListener(this);
        showAlertDialog.setOnClickListener(this);
        showItemDialog.setOnClickListener(this);
        showListPickerDialog.setOnClickListener(this);
        showMoreDialog.setOnClickListener(this);
        showMoreDialog.setOnLongClickListener(this);
        scanQRCode.setOnClickListener(this);
        scanQRCode.setOnLongClickListener(this);
        generateQRCode.setOnClickListener(this);
        ipwshowAsDropDown.setOnClickListener(this);
        ipwshowAtLocation.setOnClickListener(this);
        selectPicture.setOnClickListener(this);
        cutPicture.setOnClickListener(this);
        picture.setOnClickListener(this);
        webView.setOnClickListener(this);
        drawerLayout.setOnClickListener(this);
        banner.setOnClickListener(this);
        picker_selector.setOnClickListener(this);
        recyclerView.setOnClickListener(this);
        xRecyclerView.setOnClickListener(this);
        listView.setOnClickListener(this);
        jpushNoNotifyActivity.setOnClickListener(this);
        updateVersionActivity.setOnClickListener(this);
        letterActivity.setOnClickListener(this);
        phtoViewActivity.setOnClickListener(this);
    }
    private void initData(){

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.showAsDropDown:
                addPopWindow=new SharePopWindow((Activity) getContext());
                addPopWindow.showPopupWindowAsDropDown(rootView);
                break;
            case R.id.showAtLocation:
                addPopWindow=new SharePopWindow((Activity) getContext());
                addPopWindow.showPopupWindowInBottom(rootView);
                break;
            case R.id.showDialog:
                ShareDialog.showLoadingDialog(getContext(),"");
                break;
            case R.id.showAlertDialog:
                showAlertDialog();
                break;
            case R.id.showItemDialog:
                showItemDialog();
                break;
            case R.id.showListPickerDialog:
                showListPickerDialog();
                break;
            case R.id.showMoreDialog:
                showMoreDialog();
                break;
            case R.id.scanQRCode:
                Intent intent=new Intent(getContext(), ScanActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.generateQRCode:
                Intent gqintent=new Intent(getContext(), GenerateActivity.class);
                startActivity(gqintent);
                break;
            case R.id.ipwshowAsDropDown:
                showItemPopupWindow(0);
                break;
            case R.id.ipwshowAtLocation:
                showItemPopupWindow(1);
                break;
            case R.id.selectPicture:
                Intent sp_intent=new Intent(getContext(), SelectPictureActivity.class);
                startActivityForResult(sp_intent,SELECT_PICTURE);
                break;
            case R.id.picture:
                Intent sp_intent1=new Intent(getContext(), SelectPictureActivity.class);
                startActivityForResult(sp_intent1,SELECT_PICTURE);
                break;
            case R.id.cutPicture:
                if(picturePath!=null){
                    Intent intent1= CutPictureActivity.createIntent(getContext(), picturePath
                            , DataKeeper.imagePath, "photo" + System.currentTimeMillis(), 200);
                    startActivityForResult(intent1,REQUEST_TO_CUT_PICTURE);
                }
                break;
            case R.id.webView:
                Intent wv_intent=new Intent(getContext(), WebViewActivity.class);
                startActivity(wv_intent);
                break;
            case R.id.drawerLayout:
                Intent dl_intent=new Intent(getContext(), DrawerLayoutActivity.class);
                startActivity(dl_intent);
                break;
            case R.id.banner:
                Intent bn_intent=new Intent(getContext(), BannerActivity.class);
                startActivity(bn_intent);
                break;
            case R.id.picker_selector:
                Intent ps_intent=new Intent(getContext(), PickerViewActivity.class);
                startActivity(ps_intent);
                break;
            case R.id.recyclerView:
                Intent rv_intent=new Intent(getContext(), GoodsRecyclerViewActivity.class);
                startActivity(rv_intent);
                break;
            case R.id.xRecyclerView:
                Intent xrv_intent=new Intent(getContext(), GoodsXRecyclerViewActivity.class);
                startActivity(xrv_intent);
                break;
            case R.id.listView:
                Intent lv_intent=new Intent(getContext(), GoodsListViewActivity.class);
                startActivity(lv_intent);
                break;
            case R.id.jpushNoNotifyActivity:
               Intent jp_intent=new Intent(getContext(), JpushNoNotitifyActivity.class);
                startActivity(jp_intent);
                break;
            case R.id.updateVersionActivity:
                /*Intent uv_intent=new Intent(getContext(), UpdateVersionActivity.class);
                startActivity(uv_intent);*/
                Intent uv_intent=new Intent(getContext(), DownLoadActivity.class);
                startActivity(uv_intent);
                break;
            case R.id.letterActivity:
                Intent l_intent=new Intent(getContext(), LetterActivity.class);
                startActivity(l_intent);
                break;
            case R.id.phtoViewActivity:
                Intent pv_intent=new Intent(getContext(), PhotoViewActivity.class);
                startActivity(pv_intent);
                break;
        }
    }
    /**显示普通弹窗
     */
    private void showAlertDialog() {
        new AlertDialog(getContext(), "更改颜色", "确定将导航栏颜色改为红色？", true, 0, this).show();
    }
    private static final String[] TOPBAR_COLOR_NAMES = {"灰色", "蓝色", "黄色"};
    /**显示列表选择弹窗
     */
    private void showItemDialog() {
        new ItemDialog(getContext(), TOPBAR_COLOR_NAMES, "选择颜色", 0, this).show();
    }
    private void showListPickerDialog() {
        new ListPickerDialog().show(TOPBAR_COLOR_NAMES, getFragmentManager(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, final int which) {
                ToastUtils.getInstance().showToast(getContext(),TOPBAR_COLOR_NAMES[which]);
            }
        });
    }
    private void showItemPopupWindow(int type){
        ItemPopWindow ipw=new ItemPopWindow((Activity) getContext(),TOPBAR_COLOR_NAMES,"选择颜色",100,this);
        if(0==type){
            ipw.showPopupWindowAsDropDown(rootView);
        }else{
            ipw.showPopupWindowInBottom(rootView);
        }
    }
    private void showMoreDialog() {
        final Dialog inviteDialog = new Dialog(getActivity(), R.style.dialog);
        inviteDialog.setContentView(R.layout.alert_more);
        TextView addFriend = (TextView) inviteDialog.findViewById(R.id.add_friend);
        TextView managerGroup = (TextView) inviteDialog.findViewById(R.id.manager_group);
        TextView addGroup = (TextView) inviteDialog.findViewById(R.id.add_group);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(getActivity(), SearchFriendActivity.class);
                getActivity().startActivity(intent);*/
                inviteDialog.dismiss();
            }
        });
        managerGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(getActivity(), ManageFriendGroupActivity.class);
                getActivity().startActivity(intent);*/
                inviteDialog.dismiss();
            }
        });
        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getActivity(), SearchGroupActivity.class);
                getActivity().startActivity(intent);*/
                MorePopWindow morePopWindow=new MorePopWindow((Activity) getContext());
                morePopWindow.showPopupWindowAsDropDown(showMoreDialog);
                inviteDialog.dismiss();
            }
        });
        Window window = inviteDialog.getWindow();
        window.setGravity(Gravity.TOP | Gravity.RIGHT);
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setAttributes(lp);
        inviteDialog.show();
    }
    private void showMorePop() {

    }
    @Override
    public void OnPopupWindowItemClick(int requestCode, int position, String item) {
        ToastUtils.getInstance().showToast(getContext(),item);
    }
    public static final int SELECT_PICTURE = 10;
    private static final int REQUEST_TO_SELECT_PICTURE = 20;
    private static final int REQUEST_TO_CUT_PICTURE = 21;
    public static final int REQUEST_TO_CAMERA_SCAN = 22;
    private static final int REQUEST_TO_EDIT_TEXT_INFO = 23;
    private static final int REQUEST_TO_SERVER_SETTING = 24;
    private static final int REQUEST_TO_DEMO_BOTTOM_WINDOW = 25;

    private static final int REQUEST_TO_TOP_MENU = 30;
    private static final int REQUEST_TO_BOTTOM_MENU = 31;
    private static final int REQUEST_TO_PLACE_PICKER = 32;
    private static final int REQUEST_TO_DATE_PICKER = 33;
    private static final int REQUEST_TO_TIME_PICKER = 34;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                /*if(data!=null){
                    String result = data.getStringExtra(ScanActivity.RESULT_QRCODE_STRING);
                    ToastUtils.getInstance().showToast(getContext(),"扫到的内容为:"+result);
                }else{
                    ToastUtils.getInstance().showToast(getContext(),"没有扫到内容。");
                }*/
            case SELECT_PICTURE:
                if (data != null) {
                    ToastUtils.getInstance().showToast(getContext(),data.getStringExtra(SelectPictureActivity.RESULT_PICTURE_PATH));
                    Intent intent=CutPictureActivity.createIntent(getContext(), data.getStringExtra(SelectPictureActivity.RESULT_PICTURE_PATH)
                            , DataKeeper.imagePath, "photo" + System.currentTimeMillis(), 200);
                    picturePath=data.getStringExtra(SelectPictureActivity.RESULT_PICTURE_PATH);
                    startActivityForResult(intent,REQUEST_TO_CUT_PICTURE);
                }else{
                    ToastUtils.getInstance().showToast(getContext(),"没有选择到任何照片");
                }
            case REQUEST_TO_CUT_PICTURE:
                if (data != null) {
                    ToastUtils.getInstance().showToast(getContext(),data.getStringExtra(CutPictureActivity.RESULT_PICTURE_PATH));
                    //picturePath=data.getStringExtra(CutPictureActivity.RESULT_PICTURE_PATH);
                    setPicture(data.getStringExtra(CutPictureActivity.RESULT_PICTURE_PATH));
                }
                break;
        }
    }
    /**显示图片
     * @param path
     */
    private void setPicture(String path) {
        if (StringUtil.isFilePath(path) == false) {
            //Log.e(TAG, "setPicture  StringUtil.isFilePath(path) == false >> showShortToast(找不到图片);return;");
            ToastUtils.getInstance().showToast(getContext(),"找不到图片");
            return;
        }
        //this.picturePath = path;

        //svDemoMain.smoothScrollTo(0, 0);
        Glide.with(getContext()).load(path).into(picture);
    }

    @Override
    public void complete(String dataType) {

    }

    @Override
    public void onDialogButtonClick(int requestCode, boolean isPositive) {
        ToastUtils.getInstance().showToast(getContext(),isPositive?"你点了确定":"你点了取消");
    }

    @Override
    public void onDialogItemClick(int requestCode, int position, String item) {
        ToastUtils.getInstance().showToast(getContext(),TOPBAR_COLOR_NAMES[position]);
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.showMoreDialog:
                PictureSelector.create((Activity) getContext()).openGallery(PictureMimeType.ofAll())
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
                break;
            default:
                break;
        }
        MorePopWindow morePopWindow=new MorePopWindow((Activity) getContext());
        morePopWindow.showPopupWindowAsDropDown(v);
        return false;
    }
}
