package com.spiderman.example.ui.activity.test;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.spiderman.example.R;
import com.spiderman.example.ui.activity.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by HP on 2018/5/23.
 */

public class PickerViewActivity extends BaseActivity implements View.OnClickListener {
    private Button time_selector;
    private Button case_selector;
    private TimePickerView pvTime;
    @Override
    protected void loadLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_picker_view);
    }

    @Override
    protected void initView() {
        time_selector= (Button) findViewById(R.id.time_selector);
        case_selector= (Button) findViewById(R.id.case_selector);
        time_selector.setOnClickListener(this);
        case_selector.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.time_selector:
                //时间选择器
                TimePickerView pvTime = new TimePickerBuilder(PickerViewActivity.this, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Toast.makeText(PickerViewActivity.this, sdf.format(date), Toast.LENGTH_SHORT).show();
                    }
                }).setType(new boolean[]{true, true, true, true, true, true}).build();
                pvTime.show();
                break;
            case R.id.case_selector:
                //条件选择器
                final List<String> options1Items=new ArrayList<String>();
                options1Items.add("男");
                options1Items.add("女");
                OptionsPickerView pvOptions = new OptionsPickerBuilder(PickerViewActivity.this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                        //返回的分别是三个级别的选中位置
                        /*String tx = options1Items.get(options1).getPickerViewText()
                                + options2Items.get(options1).get(option2)
                                + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
                        tvOptions.setText(tx);*/
                        Toast.makeText(PickerViewActivity.this, options1Items.get(options1), Toast.LENGTH_SHORT).show();
                    }
                }).build();
                pvOptions.setPicker(options1Items, null, null);
                pvOptions.show();
                break;
        }
    }
    private void initTimePicker() {//Dialog 模式下，在底部弹出

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                Toast.makeText(PickerViewActivity.this, sdf.format(date), Toast.LENGTH_SHORT).show();
                Log.i("pvTime", "onTimeSelect");

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
            pvTime.show();
        }
    }
}
