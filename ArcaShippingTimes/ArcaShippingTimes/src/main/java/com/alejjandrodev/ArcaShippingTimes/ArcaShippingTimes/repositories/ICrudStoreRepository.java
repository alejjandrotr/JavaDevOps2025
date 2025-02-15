package com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.repositories;

import com.alejjandrodev.ArcaShippingTimes.ArcaShippingTimes.entities.Store;

import java.awt.*;

public interface ICrudStoreRepository {
    Store save();
    Store update();
    Store findOne(Long id);
    Store findOneInTheCity(String cityCode);
}

class DBCustomAdaptada  implements ICrudStoreRepository {

    DBCustom dbCustom;

    @Override
    public Store save() {
        Store store = dbCustom.save();
        dbCustom.confirm();
        return store;
    }

    @Override
    public Store update() {
        Store store = dbCustom.update();
        dbCustom.confirm();
        return store;
    }

    @Override
    public Store findOne(Long id) {
        return dbCustom.findOne(id);
    }

    @Override
    public Store findOneInTheCity(String cityCode) {
        return null;
    }
}

class DBCustom {
    Store save(){
        return null;
    }

    Store update(){
        return null;
    }

    Store confirm(){
        return null;
    }


    Store findOne(Long id){
        return null;
    }
    Store findOneInTheCity(String cityCode){
        return null;
    }

}

class serviceCustom {
    ICrudStoreRepository crudStoreRepository;

    Store guardar(){
        return crudStoreRepository.save();
    }
}
