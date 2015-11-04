package com.acd21.staromoon.desertmaker.busevent;

import com.acd21.staromoon.desertmaker.dao.DessertItemDao;

/**
 * Created by staromoon on 10/30/2015 AD.
 */
public class DessertBusEvent {

    DessertItemDao dao;

    public DessertBusEvent(DessertItemDao dao) {
        this.dao = dao;
    }

    public DessertItemDao getDao() {
        return dao;
    }

    public void setDao(DessertItemDao dao) {
        this.dao = dao;
    }
}
