package com.acapsule.accountcapsule.DB;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 大舅哥 on 2016/4/24.
 */
@DatabaseTable(tableName = "data")
public class DBData {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "dataid")
    private int dataid;
    @DatabaseField(foreign = true, columnName = "userid")
    private DBUser dbUser;
    @DatabaseField(columnName = "money")
    private double money;
    @DatabaseField(columnName = "mtype")
    private int mtype;
    @DatabaseField(columnName = "patten")
    private int patten;
    @DatabaseField(columnName = "picture")
    private String picture;
    @DatabaseField(columnName = "tape")
    private String tape;
    @DatabaseField(columnName = "tame")
    private String tame;


    public DBUser getDbUser() {
        return dbUser;
    }

    public void setDbUser(DBUser dbUser) {
        this.dbUser = dbUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDataid() {
        return dataid;
    }

    public void setDataid(int dataid) {
        this.dataid = dataid;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getMtype() {
        return mtype;
    }

    public void setMtype(int mtype) {
        this.mtype = mtype;
    }

    public int getPatten() {
        return patten;
    }

    public void setPatten(int patten) {
        this.patten = patten;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTape() {
        return tape;
    }

    public void setTape(String tape) {
        this.tape = tape;
    }

    public String getTame() {
        return tame;
    }

    public void setTame(String tame) {
        this.tame = tame;
    }


}
