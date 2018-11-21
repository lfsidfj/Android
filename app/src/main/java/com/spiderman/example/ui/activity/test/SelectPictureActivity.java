package com.spiderman.example.ui.activity.test;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.spiderman.example.R;
import com.spiderman.example.ui.activity.BaseActivity;
import com.spiderman.example.ui.popuwindows.ItemPopWindow;
import com.spiderman.example.util.ToastUtils;
import com.spiderman.example.util.zb.CommonUtil;
import com.spiderman.example.util.zb.DataKeeper;

import java.io.File;

/**
 * Created by HP on 2018/5/16.
 */

public class SelectPictureActivity extends BaseActivity implements ItemPopWindow.OnPopupWindowItemClickListener,View.OnClickListener {
    boolean first=true;
    @Override
    protected void loadLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_select_picture);
        findViewById(R.id.select_picture_bg).setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus&&first){
            String[] TOPBAR_COLOR_NAMES = {"拍照", "图库"};
            ItemPopWindow ipw=new ItemPopWindow(this,TOPBAR_COLOR_NAMES,"选择图片",100,this);
            LayoutInflater inflate = LayoutInflater.from(this);
            View rootView=inflate.inflate(R.layout.activity_select_picture,null);
            ipw.showPopupWindowInBottom(rootView);
            first=false;
        }
    }
    private String picturePath = "";
    private File cameraFile;
    /**
     * 照相获取图片
     */
    public void selectPicFromCamera() {
        if (!CommonUtil.isExitsSdcard()) {
            ToastUtils.getInstance().showToast(this,"SD卡不存在，不能拍照");
            //showShortToast("SD卡不存在，不能拍照");
            return;
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 指定调用相机拍照后照片的储存路径
        cameraFile = new File(DataKeeper.imagePath, "photo" + System.currentTimeMillis() + ".jpg");
        cameraFile.getParentFile().mkdirs();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile));
        startActivityForResult(intent,REQUEST_CODE_CAMERA);
    }


    /**
     * 从图库获取图片
     */
    public void selectPicFromLocal() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent,REQUEST_CODE_LOCAL);
    }

    public static final String RESULT_PICTURE_PATH = "RESULT_PICTURE_PATH";
    /**根据图库图片uri发送图片
     * @param selectedImage
     */
    private void sendPicByUri(Uri selectedImage) {
        Cursor cursor = getContentResolver().query(selectedImage, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex("_data");
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            cursor = null;

            if (picturePath == null || picturePath.equals("null")) {
                Toast toast = Toast.makeText(this, "找不到图片", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;
            }
        } else {
            File file = new File(selectedImage.getPath());
            if (!file.exists()) {
                Toast toast = Toast.makeText(this, "找不到图片", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return;

            }
            picturePath = file.getAbsolutePath();
        }
        setResult(RESULT_OK, new Intent().putExtra(RESULT_PICTURE_PATH, picturePath));
    }
    public static final int REQUEST_TO_BOTTOM_MENU = 10;
    public static final int REQUEST_CODE_CAMERA = 18;
    public static final int REQUEST_CODE_LOCAL = 19;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CAMERA: //发送照片
                    if (cameraFile != null && cameraFile.exists()) {
                        picturePath = cameraFile.getAbsolutePath();
                        setResult(RESULT_OK, new Intent().putExtra(RESULT_PICTURE_PATH, picturePath));
                    }
                case REQUEST_CODE_LOCAL: //发送本地图片
                    if (data != null) {
                        Uri selectedImage = data.getData();
                        if (selectedImage != null) {
                            sendPicByUri(selectedImage);
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        finish();
    }

    @Override
    public void OnPopupWindowItemClick(int requestCode, int position, String item) {
        switch (position){
            case 0:
                selectPicFromCamera();//照相
                return;
            case 1:
                selectPicFromLocal();//从图库筛选
                return;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.select_picture_bg) {
            finish();
        }
    }
}
