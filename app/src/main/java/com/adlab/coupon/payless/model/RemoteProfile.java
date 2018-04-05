package com.adlab.coupon.payless.model;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by renarosantos on 21/02/17.
 */
public class RemoteProfile {

    public final Integer id;
    public final String name;
    public final String age;
    public final String cover;

    public RemoteProfile(final Integer id, final String name, final String age, final String cover) {
        this.id = id;


        this.name = Long.toHexString(Double.doubleToLongBits(Math.random()));
        this.age = age;
        this.cover = cover;
    }


}
