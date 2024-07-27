package me.goost.goostserver.SQLiteDB;

import java.util.Date;

public class PlayerStats {

    private String uuid;
    private Boolean player;
    private String class_;
    private Integer bank;
    private Integer cash;
    private Integer level;
    private Double experience;
    private Date lastLogin;
    private Date lastLogout;

    public PlayerStats(String uuid, Boolean player, String class_, Integer bank, Integer cash, Integer level, Double experience, Date lastLogin, Date lastLogout) {
        this.uuid = uuid;
        this.player = player;
        this.class_ = class_;
        // the reason the 'class' is 'class_' is because this class will call something else
        this.bank = bank;
        this.cash = cash;
        this.level = level;
        this.experience = experience;
        this.lastLogin = lastLogin;
        this.lastLogout = lastLogout;
    }



    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getPlayer() {
        return player;
    }
    public void setPlayer(Boolean player) {
        this.player = player;
    }

    public String getClass_() {
        return class_;
    }
    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public Integer getBank() {
        return bank;
    }
    public void setBank(Integer bank) {
        this.bank = bank;
    }

    public Integer getCash() {
        return cash;
    }
    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {this.level = level;}

    public Double getExperience() {
        return experience;
    }
    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Date getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastLogout() {
        return lastLogout;
    }
    public void setLastLogout(Date lastLogout) {
        this.lastLogout = lastLogout;
    }
}
