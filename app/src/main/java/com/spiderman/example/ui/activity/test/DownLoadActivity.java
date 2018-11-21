package com.spiderman.example.ui.activity.test;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.model.Progress;
import com.spiderman.example.R;
import com.spiderman.example.presentation.presenter.CommonPresenter;
import com.spiderman.example.ui.activity.HttpBaseActivity;
import com.spiderman.example.util.ToastUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DownLoadActivity extends HttpBaseActivity {
    @BindView(R.id.filePath)
    EditText filePath;
    @BindView(R.id.isExist)
    Button isExist;
    @BindView(R.id.downLoad)
    Button downLoad;
    @BindView(R.id.install)
    Button install;
    @BindView(R.id.downLoadAndInstall)
    Button downLoadAndInstall;
    @BindView(R.id.persent)
    TextView persent;
    Button picture;
    CommonPresenter commonPresenter;
    boolean autoInstall= false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void loadLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        filePath=findViewById(R.id.filePath);
        isExist=findViewById(R.id.isExist);
        downLoad=findViewById(R.id.downLoad);
        install=findViewById(R.id.install);
        downLoadAndInstall=findViewById(R.id.downLoadAndInstall);
        persent=findViewById(R.id.persent);
        picture=findViewById(R.id.picture);
        downLoad.setOnClickListener(this);
        isExist.setOnClickListener(this);
        install.setOnClickListener(this);
        picture.setOnClickListener(this);
        downLoadAndInstall.setOnClickListener(this);
        commonPresenter=new CommonPresenter(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.downLoad:
                autoInstall=false;
                if(checkPermission(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},201)){
                    commonPresenter.downLoad("http://39.108.149.220/apk/pic.apk","http://39.108.149.220/apk/pic.apk");
                }
                break;
            case R.id.isExist:
                File file=new File(filePath.getText().toString());
                if(file.exists()){
                    ToastUtils.getInstance().showToast(getContext(),"文件存在");
                }else{
                    ToastUtils.getInstance().showToast(getContext(),"文件不存在");
                }
                break;
            case R.id.install:
                checkIsAndroidO();
                install(filePath.getText().toString());
                break;
            case R.id.downLoadAndInstall:
                autoInstall=true;
                commonPresenter.downLoad("http://39.108.149.220/apk/pic.apk","http://39.108.149.220/apk/pic.apk");
                break;
            case R.id.picture:
                if(checkPermission(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},203)){
                    ToastUtils.getInstance().showToast(this,"已经都获取了");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onProgress(Progress progress, String dataType) {
        super.onProgress(progress, dataType);
        persent.setText(progress.fraction*100+"%");
    }

    @Override
    public void onDownloadSuccess(File file, String dataType) {
        super.onDownloadSuccess(file, dataType);
        if("http://39.108.149.220/apk/pic.apk".equals(dataType)){
            filePath.setText(file.getAbsolutePath());
            if(autoInstall){
                install(file.getAbsolutePath());
            }
        }
    }
    private void install(String filePath) {
        File apkFile = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(
                    getContext()
                    , "com.spiderman.example.fileprovider"
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }
    public static boolean checkPermission(Activity activity,String [] ps,int requestCode) {
        boolean isGranted = true;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            for(String p:ps){
                if (activity.checkSelfPermission(p) != PERMISSION_GRANTED) {
                    isGranted = false;
                }
            }
            if (!isGranted) {
                activity.requestPermissions(ps,
                        requestCode);
            }
        }
        return isGranted;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<String> notPassPermissions=new ArrayList<>();//没有通过的权限
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PERMISSION_GRANTED) {
                Toast.makeText(this, "" + "权限" + permissions[i] + "申请成功", Toast.LENGTH_SHORT).show();
            } else {
                notPassPermissions.add(permissions[i]);
                Toast.makeText(this, "" + "权限" + permissions[i] + "申请失败", Toast.LENGTH_SHORT).show();
            }
        }
        if(notPassPermissions.isEmpty()){
            //做应用的事
            switch (requestCode){
                case 201:
                    commonPresenter.downLoad("http://39.108.149.220/apk/pic.apk","http://39.108.149.220/apk/pic.apk");
                    break;
                case 202:
                    install(filePath.getText().toString());
                    break;
                case 203:
                    ToastUtils.getInstance().showToast(this,"已经都获取了");
                    break;
                default:
                    break;
            }
        }else if(!shouldGoToSetting((String[]) notPassPermissions.toArray())){
            checkPermission(this, (String[]) notPassPermissions.toArray(),201);
        }
    }
    /*
     *
     * 判断是否是8.0,8.0需要处理未知应用来源权限问题,否则直接安装
     */
    private void checkIsAndroidO() {
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = getPackageManager().canRequestPackageInstalls();
            if (b) {
                install(filePath.getText().toString());
            } else {
                //请求安装未知应用来源的权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES},202);
            }
        } else {
            install(filePath.getText().toString());
        }
    }

    /**
     * 如果有被永久禁止的权限就会跳到设置页面并返回true
     * @param permissions
     * @return
     */
    private boolean shouldGoToSetting(String[] permissions){
        boolean neverAlert=false;//是否有以后不再提醒的权限
        List<String> neverAlertPermissions=new ArrayList<>();//以后不再提醒的权限
        for(String p:permissions){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,p)){
                neverAlert=true;
                neverAlertPermissions.add(p);
            }
        }
        if (neverAlert) {
            StringBuffer nap=new StringBuffer();
            for(String p:neverAlertPermissions){
                nap.append(p+",");
            }
            ToastUtils.getInstance().showToast(this,"不再弹出的权限有："+nap.toString());
            Uri packageURI = Uri.parse("package:" + getPackageName());
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
            this.startActivity(intent);
            return true;
        }else{
            return false;
        }
    }
}
