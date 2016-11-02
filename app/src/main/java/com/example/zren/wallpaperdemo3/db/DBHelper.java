package com.example.zren.wallpaperdemo3.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.zren.wallpaperdemo3.domain.Collection_Img;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class DBHelper extends OrmLiteSqliteOpenHelper{

    private static final String DB_NAME="wallpaper.db";

    private static DBHelper instance;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    /**
     * 实例化存放Dao类名字符串及其类对象的容器
     */
    private Map<String,Dao> daoMap=new HashMap<>();

    /**
     * 返回Dao 对象
     * @param clz
     * @return
     * @throws SQLException
     */

    public synchronized Dao getDao(Class clz) throws SQLException {
        Dao dao=null;
        String className=clz.getSimpleName();
        if(daoMap.containsKey(className)){
            dao=daoMap.get(className);
        }
        if(dao==null){
            dao=super.getDao(clz);
            daoMap.put(className,dao);
        }
        return dao;
    }

    public void close() {
        super.close();
        for(String key:daoMap.keySet()){
            Dao dao=daoMap.get(key);
            dao=null;
        }
    }

    public static synchronized DBHelper getHelper(Context context)
    {
        if (instance == null)
        {
            synchronized (DBHelper.class)
            {
                if (instance == null)
                    instance = new DBHelper(context);
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Collection_Img.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource,Collection_Img.class,true/*是否忽略错误*/);
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
