package com.acd21.staromoon.desertmaker.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.acd21.staromoon.desertmaker.dao.DessertItemCollectionDao;
import com.acd21.staromoon.desertmaker.dao.DessertItemDao;
import com.google.gson.Gson;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class DessertListManager {

    private static DessertListManager instance;
    public static DessertListManager getInstance() {
        if (instance == null)
            instance = new DessertListManager();
        return instance;
    }

    private Context mContext;
    private DessertItemCollectionDao dao;
    private DessertItemDao selectedDao;

    private DessertListManager() {
        mContext = Contextor.getInstance().getContext();
        loadCache();
    }

    public DessertItemCollectionDao getDao() {
        return dao;
    }

    public void setDao(DessertItemCollectionDao dao) {
        this.dao = dao;
        saveCache();
    }

    public DessertItemDao getSelectedDao() {
        return selectedDao;
    }

    public void setSelectedDao(DessertItemDao selectedDao) {
        this.selectedDao = selectedDao;
    }

    private void saveCache() {
        String json = new Gson().toJson(dao);       // แปลง gson เป็น json

        SharedPreferences prefs = mContext.getSharedPreferences("dessert", Context.MODE_PRIVATE);       // เรียก shar
        SharedPreferences.Editor editor = prefs.edit();     //  เขียนลง shared
        editor.putString("json",json);
        editor.apply();
    }

    private void loadCache() {
        SharedPreferences prefs = mContext.getSharedPreferences("dessert", Context.MODE_PRIVATE);
        String json = prefs.getString("json",null);
        if(json == null)
            return ;
        dao = new Gson().fromJson(json, DessertItemCollectionDao.class);
    }
}
