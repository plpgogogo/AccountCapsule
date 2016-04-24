package com.acapsule.accountcapsule.DB;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by 大舅哥 on 2016/4/24.
 */
public class DBDataDao {

    private DBHelper dbHelper;
    private Dao<DBData, Integer> dataDao;

    public DBDataDao(Context context){
        try{
            dbHelper = DBHelper.getInstance(context);
            dataDao = dbHelper.getDao(DBData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(DBData data){
        try {
            dataDao.create(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DBData getDBDataByID(int id){
        DBData dbData = null;
        try{
            dbData = dataDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbData;
    }

    public List<DBData> getAllDBDataByUserID(int userid){
        try{
            return dataDao.queryBuilder().where().eq("userid", userid).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
