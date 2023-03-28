package me.goost.goostserver.SQLDB;

import java.util.Date;

public class PlayerStats {

    private String uuid;
    private Boolean player;
    private String class_;
    private int bank;
    private int cash;
    private double level;
    private String storyLine;
    private Date lastLogin;
    private Date lastLogout;

    public PlayerStats(String uuid, Boolean player, String class_, int bank, int cash, double level, String storyLine, Date lastLogin, Date lastLogout) {
        this.uuid = uuid;
        this.player = player;
        this.class_ = class_;
        this.bank = bank;
        this.cash = cash;
        this.level = level;
        this.storyLine = storyLine;
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

    public int getBank() {
        return bank;
    }
    public void setBank(int bank) {
        this.bank = bank;
    }

    public int getCash() {
        return cash;
    }
    public void setCash(int cash) {
        this.cash = cash;
    }

    public double getLevel() {
        return level;
    }
    public void setLevel(double level) {
        this.level = level;
    }

    public String getStoryLine() {
        return storyLine;
    }
    public void setStoryLine(String storyLine) {
        this.storyLine = storyLine;
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
