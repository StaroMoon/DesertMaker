package com.acd21.staromoon.desertmaker.manager.http;

import com.acd21.staromoon.desertmaker.dao.DessertItemCollectionDao;

import retrofit.Call;
import retrofit.http.POST;

public interface APIService {   //Interface ตาม guide api STEP 1
    @POST("list")
    Call<DessertItemCollectionDao> loadDesserList();
}
