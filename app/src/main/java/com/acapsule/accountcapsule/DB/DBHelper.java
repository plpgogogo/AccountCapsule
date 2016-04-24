package com.acapsule.accountcapsule.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 大舅哥 on 2016/4/24.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_Name = "acapsule.db";
    private Map<String, Dao> daos = new HashMap<>();

    public DBHelper(Context context) {
        super(context, DB_Name, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.clearTable(connectionSource, DBUser.class);
            TableUtils.clearTable(connectionSource, DBData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            TableUtils.dropTable(connectionSource, DBData.class, true);
            TableUtils.dropTable(connectionSource, DBData.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DBHelper instance;
    public static synchronized DBHelper getInstance(Context context){
        if(instance == null){
            synchronized (DBHelper.class){
                if(instance == null){
                    instance = new DBHelper(context);
                }
            }
        }
        return instance;
    }


    public  synchronized  Dao getDao(Class clazz) throws SQLException{
        Dao dao = null;
        String className = clazz.getSimpleName();
        if(daos.containsKey(className)){
            dao = daos.get(className);
        }
        if(dao == null){
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        for(String key : daos.keySet()){
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
