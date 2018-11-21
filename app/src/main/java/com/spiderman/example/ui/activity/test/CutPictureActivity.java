package com.spiderman.example.ui.activity.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.spiderman.example.ui.activity.BaseActivity;
import com.spiderman.example.util.ToastUtils;
import com.spiderman.example.util.zb.CommonUtil;
import com.spiderman.example.util.zb.DataKeeper;
import com.spiderman.example.util.zb.StringUtil;

import java.io.File;

/**
 * Created by HP on 2018/5/17.
 */

public class CutPictureActivity extends BaseActivity {
    private static final String TAG = "CutPictureActivity";
    public static final String INTENT_ORIGINAL_PICTURE_PATH = "INTENT_ORIGINAL_PICTURE_PATH";
    public static final String INTENT_CUTTED_PICTURE_PATH = "INTENT_CUTTED_PICTURE_PATH";
    public static final String INTENT_CUTTED_PICTURE_NAME = "INTENT_CUTTED_PICTURE_NAME";

    public static final String INTENT_CUT_WIDTH = "INTENT_CUT_WIDTH";
    public static final String INTENT_CUT_HEIGHT = "INTENT_CUT_HEIGHT";

    @Override
    protected void loadLayout(Bundle savedInstanceState) {

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

    /**启动这个Activity的Intent
     * @param context
     * @param originalPath
     * @param cuttedPath
     * @param cuttedName
     * @param cuttedSize
     * @return
     */
    public static Intent createIntent(Context context, String originalPath
            , String cuttedPath, String cuttedName, int cuttedSize) {
        return createIntent(context, originalPath, cuttedPath, cuttedName, cuttedSize, cuttedSize);
    }
    /**启动这个Activity的Intent
     * @param context
     * @param originalPath
     * @param cuttedPath
     * @param cuttedName
     * @param cuttedWidth
     * @param cuttedHeight
     * @return
     */
    public static Intent createIntent(Context context, String originalPath
            , String cuttedPath, String cuttedName, int cuttedWidth, int cuttedHeight) {
        Intent intent = new Intent(context, CutPictureActivity.class);
        intent.putExtra(INTENT_ORIGINAL_PICTURE_PATH, originalPath);
        intent.putExtra(INTENT_CUTTED_PICTURE_PATH, cuttedPath);
        intent.putExtra(INTENT_CUTTED_PICTURE_NAME, cuttedName);
        intent.putExtra(INTENT_CUT_WIDTH, cuttedWidth);
        intent.putExtra(INTENT_CUT_HEIGHT, cuttedHeight);
        return intent;
    }
    private String originalPicturePath;
    private String cuttedPicturePath;
    private String cuttedPictureName;
    private int cuttedWidth;
    private int cuttedHeight;
    Intent intent=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        intent = getIntent();

        originalPicturePath = intent.getStringExtra(INTENT_ORIGINAL_PICTURE_PATH);
        cuttedWidth = intent.getIntExtra(INTENT_CUT_WIDTH, 0);
        cuttedHeight = intent.getIntExtra(INTENT_CUT_HEIGHT, 0);
        if (cuttedWidth <= 0) {
            cuttedWidth = cuttedHeight;
        }
        if (cuttedHeight <= 0) {
            cuttedHeight = cuttedWidth;
        }

        if (StringUtil.isNotEmpty(originalPicturePath, true) == false || cuttedWidth <= 0) {
            Log.e(TAG, "onCreate  StringUtil.isNotEmpty(originalPicturePath, true)" +
                    " == false || cuttedWidth <= 0 >> finish(); return;");
            ToastUtils.getInstance().showToast(this,"图片不存在，请先选择图片");
            finish();
            return;
        }
        super.onCreate(savedInstanceState);
        startPhotoZoom(originalPicturePath, cuttedWidth, cuttedHeight);
    }
    /**照片裁剪
     * @param path
     * @param width
     * @param height
     */
    public void startPhotoZoom(String path, int width, int height) {
        startPhotoZoom(Uri.fromFile(new File(path)), width, height);
    }
    /**照片裁剪
     * @param fileUri
     * @param width
     * @param height
     */
    public void startPhotoZoom(Uri fileUri, int width, int height) {

        intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(fileUri, "image/*");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);

        if (Build.VERSION.SDK_INT >= 23) {
            File outputImage = new File(DataKeeper.imagePath, "output_image" + System.currentTimeMillis() + ".jpg");
            cuttedPicturePath = outputImage.getAbsolutePath();
            intent.putExtra("scale", true);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputImage));
        } else {
            intent.putExtra("crop", "true");// crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("return-data", true);
        }
        Log.i(TAG, "startPhotoZoom  fileUri = "+ fileUri);
        startActivityForResult(intent,REQUEST_CUT_PHOTO);
    }

    public static final int REQUEST_CODE_CAMERA = 18;
    public static final int REQUEST_CODE_LOCAL = 19;
    public static final int REQUEST_CUT_PHOTO = 20;

    public static final String RESULT_PICTURE_PATH = "RESULT_PICTURE_PATH";
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CUT_PHOTO: //发送本地图片
                    if (data != null) {
                        if (Build.VERSION.SDK_INT < 23 || new File(cuttedPicturePath).exists() == false) {
                            Bundle bundle = data.getExtras();
                            if (bundle != null) {
                                Bitmap photo = bundle.getParcelable("data");
                                //photo.
                                if (photo != null) {
                                    //照片的路径
                                    setCuttedPicturePath();
                                    cuttedPicturePath = CommonUtil.savePhotoToSDCard(cuttedPicturePath, cuttedPictureName, "jpg", photo);
                                }
                            }
                        }
                        setResult(RESULT_OK, new Intent().putExtra(RESULT_PICTURE_PATH, cuttedPicturePath));
                    }
                    break;
                default:
                    break;
            }
        }

        finish();
    }

    private String setCuttedPicturePath() {
        //oringlePicturePath 不对
        cuttedPicturePath = intent.getStringExtra(INTENT_CUTTED_PICTURE_PATH);
        if (StringUtil.isFilePath(cuttedPicturePath) == false) {
            cuttedPicturePath = DataKeeper.fileRootPath + DataKeeper.imagePath;
        }
        cuttedPictureName = intent.getStringExtra(INTENT_CUTTED_PICTURE_NAME);
        if (StringUtil.isFilePath(cuttedPictureName) == false) {
            cuttedPictureName = "photo" + System.currentTimeMillis();
        }
        return cuttedPicturePath;
    }

    @Override
    public void onClick(View v) {

    }
}
