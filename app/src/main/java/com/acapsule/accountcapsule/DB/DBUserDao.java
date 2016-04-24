package com.acapsule.accountcapsule.DB;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by 大舅哥 on 2016/4/24.
 */
public class DBUserDao {

    private DBHelper dbHelper;
    private Dao<DBUser, Integer> userDao;

    public DBUserDao(Context context){
        try{
            dbHelper = DBHelper.getInstance(context);
            userDao = dbHelper.getDao(DBUser.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(DBUser user){
        try {
            userDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
