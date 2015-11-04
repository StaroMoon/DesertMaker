package com.acd21.staromoon.desertmaker.view;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.acd21.staromoon.desertmaker.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class DessertListItem extends FrameLayout {

    private TextView tvName;
    private ImageView imgBg;
    private TextView tvDesc;

    public DessertListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public DessertListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs);
    }

    public DessertListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs);
    }

    private void initInflate() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_dessert, this);
    }

    private void initInstances() {
        imgBg = (ImageView) findViewById(R.id.imgBg);
        tvName = (TextView) findViewById(R.id.tvName);
        tvDesc = (TextView) findViewById(R.id.tvDesc);
    }

    private void initWithAttrs(AttributeSet attrs) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {     //หลอกขนาดตัวview ลูกๆ
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec); //รับค่ามาเพื่อใช้หลอกตัวเอง
        setMeasuredDimension(width, width); // หลอกตัวเอง
    }

    public void setNameText(String text) {
        tvName.setText(text);
    }

    public void setDescriptionText(String text) {
        tvDesc.setText(text);
    }

    public void setImageUrl(String url){
        Glide.with(getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgBg);
    }
}
