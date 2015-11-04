package com.acd21.staromoon.desertmaker.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by staromoon on 10/29/2015 AD.
 */
public class DessertItemCollectionDao {

    @SerializedName("success")
    private boolean success;
    @SerializedName("data")
    private List<DessertItemDao> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DessertItemDao> getData() {
        return data;
    }

    public void setData(List<DessertItemDao> data) {
        this.data = data;
    }
}
