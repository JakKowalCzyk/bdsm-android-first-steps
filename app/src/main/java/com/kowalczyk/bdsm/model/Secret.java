package com.kowalczyk.bdsm.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by JKowalczyk on 2017-11-08.
 */
@Entity
public class Secret {

    @Id(autoincrement = true)
    private Long id;

    private String secret;
    private String hashedPassword;

    public Secret() {
    }

    @Generated(hash = 1504742441)
    public Secret(Long id, String secret, String hashedPassword) {
        this.id = id;
        this.secret = secret;
        this.hashedPassword = hashedPassword;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

}
