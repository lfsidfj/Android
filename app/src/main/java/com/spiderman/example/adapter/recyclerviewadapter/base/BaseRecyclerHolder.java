package com.spiderman.example.adapter.recyclerviewadapter.base;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.spiderman.example.R;

/**
 * Created by ${wangjiasheng} on 2017/12/22 0022.
 *  * 万能的RecyclerView的ViewHolder
 */

public class BaseRecyclerHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private Context context;

    private BaseRecyclerHolder(Context context, View itemView,boolean hasContextMenu) {
        super(itemView);
        this.context = context;
        //指定一个初始为8
        views = new SparseArray<>(8);
        if(hasContextMenu){
        }
    }

    /**
     * 取得一个RecyclerHolder对象
     *
     * @param context  上下文
     * @param itemView 子项
     * @return 返回一个RecyclerHolder对象
     */
    public static BaseRecyclerHolder getRecyclerHolder(Context context, View itemView,boolean hasContextMenu) {
        return new BaseRecyclerHolder(context, itemView, hasContextMenu);
    }

    public SparseArray<View> getViews() {
        return this.views;
    }

    /**
     * 通过view的id获取对应的控件，如果没有则加入views中
     *
     * @param viewId 控件的id
     * @return 返回一个控件
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置字符串
     */
    public BaseRecyclerHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置图片
     */
    public BaseRecyclerHolder setImageResource(int viewId, int drawableId) {
        ImageView iv = getView(viewId);
//        iv.setImageResource(drawableId);
//        iv.setImageDrawable(context.getResources().getDrawable(drawableId));
        Glide.with(context).load(drawableId).into((ImageView)getView(viewId));
        return this;
    }

    /**
     * 设置图片
     */
    public BaseRecyclerHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置图片
     */
    public BaseRecyclerHolder setImageByUrl(int viewId, String url) {
        Glide.with(context).load(url)
                .apply(new RequestOptions().placeholder(R.drawable.default_icon).priority(Priority.HIGH))
                .into((ImageView) getView(viewId));
        return this;
    }


    /**
     * 为ImageView设置网络图片
     *
     * @param context
     * @param imageView
     * @param url
     * @return
     */
    public void setImageWithGlide(Context context, ImageView imageView, String url)
    {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().placeholder(R.drawable.icon_return).priority(Priority.HIGH))
                .into(imageView);
    }

    /**
     * 为ImageView设置本地
     *
     * @param context
     * @param imageView
     * @param url
     * @return
     */
    public void setImageWithGlide(Context context, ImageView imageView, int url)
    {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().placeholder(R.drawable.icon_return).priority(Priority.HIGH))
                .into(imageView);
    }


    public void setThumbWithGlide(Context context, int width,int height, int placeHolder, ImageView imageView, String url) {

        RequestOptions options = new RequestOptions()
                .override(width, height)
                .placeholder(placeHolder)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(url)
                .thumbnail(0.1f)
                .apply(options)
                .into(imageView);
    }
}
