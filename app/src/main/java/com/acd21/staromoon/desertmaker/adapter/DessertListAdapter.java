package com.acd21.staromoon.desertmaker.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

import com.acd21.staromoon.desertmaker.R;
import com.acd21.staromoon.desertmaker.dao.DessertItemDao;
import com.acd21.staromoon.desertmaker.manager.DessertListManager;
import com.acd21.staromoon.desertmaker.view.DessertListItem;

public class DessertListAdapter extends BaseAdapter {

    int lastPosition = -1;
    @Override
    public int getCount() {
        if(DessertListManager.getInstance().getDao() == null)       // กันการรันครั้งแรกยังไม่มี DAO
            return 0;
        if(DessertListManager.getInstance().getDao().getData() == null) //  กันการรันครั้งแรกยังไม่มีข้อมูล
            return 0;
        return DessertListManager.getInstance().getDao().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*@Override
    public int getViewTypeCount() {     //จำนวน view ที่แตกต่างกันใน listview
        return 2;
    }

    @Override
    public int getItemViewType(int position) {    // กำหนดค่าให้ view ต่างๆด้วย Integer
        return (position % 2 ==0)? 0 : 1;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //if (position % 2 == 0) {
        /*if(getItemViewType(position) == 0){
            DessertListItem item;       // การ reuse ขั้นที่ 1
            if (convertView != null) {
                item = (DessertListItem) convertView;
            } else {
                item = new DessertListItem(parent.getContext());
            }
            return item;
        } else {
            TextView item;
            if (convertView != null) {
                item = (TextView) convertView;
            } else {
                item = new TextView(parent.getContext());
            }
            item.setText("Position: " + position);
            return item;

        }*/
        DessertListItem item;       // การ reuse ขั้นที่ 1
        if (convertView != null) {
            item = (DessertListItem) convertView;
        } else {
            item = new DessertListItem(parent.getContext());
        }
        DessertItemDao dao = DessertListManager.getInstance().getDao().getData().get(position);
        item.setNameText(dao.getName());
        item.setDescriptionText(dao.getDescription());
        item.setImageUrl(dao.getImageUrl());

        if(position > lastPosition) {
            Animation anim = AnimationUtils.loadAnimation(parent.getContext(),
                    R.anim.up_down_bottom);
            item.startAnimation(anim);
            lastPosition = position;
        }
        return item;
    }
}
