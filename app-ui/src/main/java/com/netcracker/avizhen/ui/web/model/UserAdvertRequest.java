package com.netcracker.avizhen.ui.web.model;

import com.netcracker.avizhen.persistence.entity.Advert;

/**
 * Created by Александр on 13.12.2016.
 */
public class UserAdvertRequest {
    private int userId;
    private Advert advert;

    public UserAdvertRequest() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }
}
