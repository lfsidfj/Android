package com.spiderman.example.ui.activity.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.spiderman.example.R;
import com.spiderman.example.ui.activity.BaseActivity;
import com.spiderman.example.util.ToastUtils;
import com.spiderman.example.util.zb.DataKeeper;

import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

import static com.spiderman.example.ui.fragment.test.CenterFragment.SELECT_PICTURE;

/**
 * Created by Administrator on 2018/6/27.
 */

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {
    private static final String TAG = ScanActivity.class.getSimpleName();
    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;

    private ZXingView mZXingView;

    private Button beginScan;
    private Button stopScan;

    @Override
    protected void loadLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_scan);
    }

    @Override
    protected void initView() {
        mZXingView = (ZXingView) findViewById(R.id.zxingview);/*
        beginScan= (Button) findViewById(R.id.beginScan);
        stopScan= (Button) findViewById(R.id.stopScan);*/
        mZXingView.setDelegate(this);
        //beginScan.setOnClickListener(this);
        //stopScan.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public Context getContext() {
        return ScanActivity.this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_preview:
                mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
                break;
            case R.id.stop_preview:
                mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
                break;
            case R.id.start_spot:
                mZXingView.startSpot(); // 延迟0.5秒后开始识别
                break;
            case R.id.stop_spot:
                mZXingView.stopSpot(); // 停止识别
                break;
            case R.id.start_spot_showrect:
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
                break;
            case R.id.stop_spot_hiddenrect:
                mZXingView.stopSpotAndHiddenRect(); // 停止识别，并且隐藏扫描框
                break;
            case R.id.show_scan_rect:
                mZXingView.showScanRect(); // 显示扫描框
                break;
            case R.id.hidden_scan_rect:
                mZXingView.hiddenScanRect(); // 隐藏扫描框
                break;
            /*case R.id.decode_scan_box_area:
                mZXingView.getScanBoxView().setOnlyDecodeScanBoxArea(true); // 仅识别扫描框中的码
                break;
            case R.id.decode_full_screen_area:
                mZXingView.getScanBoxView().setOnlyDecodeScanBoxArea(false); // 识别整个屏幕中的码
                break;*/
            case R.id.open_flashlight:
                mZXingView.openFlashlight(); // 打开闪光灯
                break;
            case R.id.close_flashlight:
                mZXingView.closeFlashlight(); // 关闭闪光灯
                break;
            case R.id.scan_one_dimension:
                mZXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
                mZXingView.setType(BarcodeType.ONE_DIMENSION, null); // 只识别一维条码
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
                break;
            case R.id.scan_two_dimension:
                mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
                mZXingView.setType(BarcodeType.TWO_DIMENSION, null); // 只识别二维条码
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
                break;
            /*case R.id.scan_qr_code:
                mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
                mZXingView.setType(BarcodeType.ONLY_QR_CODE, null); // 只识别 QR_CODE
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
                break;
            case R.id.scan_code128:
                mZXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
                mZXingView.setType(BarcodeType.ONLY_CODE_128, null); // 只识别 CODE_128
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
                break;
            case R.id.scan_ean13:
                mZXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
                mZXingView.setType(BarcodeType.ONLY_EAN_13, null); // 只识别 EAN_13
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
                break;
            case R.id.scan_high_frequency:
                mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
                mZXingView.setType(BarcodeType.HIGH_FREQUENCY, null); // 只识别高频率格式，包括 QR_CODE、EAN_13、CODE_128
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
                break;
            case R.id.scan_all:
                mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式
                mZXingView.setType(BarcodeType.ALL, null); // 识别所有类型的码
                mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
                break;
            case R.id.scan_custom:
                mZXingView.changeToScanQRCodeStyle(); // 切换成扫描二维码样式

                Map<DecodeHintType, Object> hintMap = new EnumMap<>(DecodeHintType.class);
                List<BarcodeFormat> formatList = new ArrayList<>();
                formatList.add(BarcodeFormat.QR_CODE);
                formatList.add(BarcodeFormat.EAN_13);
                formatList.add(BarcodeFormat.CODE_128);
                hintMap.put(DecodeHintType.POSSIBLE_FORMATS, formatList); // 可能的编码格式
                hintMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE); // 花更多的时间用于寻找图上的编码，优化准确性，但不优化速度
                hintMap.put(DecodeHintType.CHARACTER_SET, "utf-8"); // 编码字符集
                mZXingView.setType(BarcodeType.CUSTOM, hintMap); // 自定义识别的类型

                mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
                break;*/
            case R.id.choose_qrcde_from_gallery:
                Intent sp_intent1=new Intent(getContext(), SelectPictureActivity.class);
                startActivityForResult(sp_intent1,SELECT_PICTURE);
                /*
                从相册选取二维码图片，这里为了方便演示，使用的是
                https://github.com/bingoogolapple/BGAPhotoPicker-Android
                这个库来从图库中选择二维码图片，这个库不是必须的，你也可以通过自己的方式从图库中选择图片
                 */
                /*Intent photoPickerIntent = new BGAPhotoPickerActivity.IntentBuilder(this)
                        .cameraFileDir(null)
                        .maxChooseCount(1)
                        .selectedPhotos(null)
                        .pauseOnScroll(false)
                        .build();
                startActivityForResult(photoPickerIntent, REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY);*/
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mZXingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别

        mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
    }
    @Override
    protected void onStop() {
        mZXingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZXingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        ToastUtils.getInstance().showToast(getContext(),result);
        //vibrate();

        mZXingView.startSpot(); // 延迟0.5秒后开始识别
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mZXingView.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别

        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_PICTURE) {
            if (data != null) {
                ToastUtils.getInstance().showToast(getContext(),data.getStringExtra(SelectPictureActivity.RESULT_PICTURE_PATH));
                Intent intent=CutPictureActivity.createIntent(getContext(), data.getStringExtra(SelectPictureActivity.RESULT_PICTURE_PATH)
                        , DataKeeper.imagePath, "photo" + System.currentTimeMillis(), 200);
                final String picturePath=data.getStringExtra(SelectPictureActivity.RESULT_PICTURE_PATH);
                mZXingView.decodeQRCode(picturePath);
                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... params) {
                        return QRCodeDecoder.syncDecodeQRCode(picturePath);
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        if (TextUtils.isEmpty(result)) {
                            Toast.makeText(ScanActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ScanActivity.this, result, Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute();
            }else{
                ToastUtils.getInstance().showToast(getContext(),"没有选择到任何照片");
            }
            //final String picturePath = BGAPhotoPickerActivity.getSelectedPhotos(data).get(0);
            // 本来就用到 QRCodeView 时可直接调 QRCodeView 的方法，走通用的回调
            //mZXingView.decodeQRCode(picturePath);

            /*
            没有用到 QRCodeView 时可以调用 QRCodeDecoder 的 syncDecodeQRCode 方法

            这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
            请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github
            .com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
             */
//            new AsyncTask<Void, Void, String>() {
//                @Override
//                protected String doInBackground(Void... params) {
//                    return QRCodeDecoder.syncDecodeQRCode(picturePath);
//                }
//
//                @Override
//                protected void onPostExecute(String result) {
//                    if (TextUtils.isEmpty(result)) {
//                        Toast.makeText(TestScanActivity.this, "未发现二维码", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(TestScanActivity.this, result, Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }.execute();
        }
    }
}
