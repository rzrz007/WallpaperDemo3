package com.example.zren.wallpaperdemo3.dao;

import android.content.Context;

import com.example.zren.wallpaperdemo3.db.DBHelper;
import com.example.zren.wallpaperdemo3.db.HotWord;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysy on 2016/11/2.
 *
 */
public class HotWordDao {

    private Context context;
    private Dao<HotWord, Integer> hotWordDaoOperate;
    private DBHelper dbHelper;

    public HotWordDao(Context context){
        this.context=context;
        dbHelper=DBHelper.getHelper(context);
        try {
            hotWordDaoOperate=dbHelper.getDao(HotWord.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一行数据
     * @param hotWord
     */
    public void add(HotWord hotWord){
        try {
            hotWordDaoOperate.create(hotWord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一行数据
     * @param name
     */
    public void delete(String name){
        try {
            DeleteBuilder deleteBuilder=hotWordDaoOperate.deleteBuilder();
            deleteBuilder.where().eq("name",name);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按 name 查询
     * @param name
     * @return
     */
    public HotWord query(String name){
        HotWord hotWord= null;
        List<HotWord> hotWords=new ArrayList<>();
        try {
            QueryBuilder builder=hotWordDaoOperate.queryBuilder();
            hotWords=builder.where().eq("name",name).query();
            hotWord=hotWords.get(0);
            return hotWord;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 查询全部
     */
    public List queryAll(){
        try {
            List<HotWord> hotWords=hotWordDaoOperate.queryForAll();
            return hotWords;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除全部
     */
    public void deleteAll(){
        try {
            hotWordDaoOperate.queryRaw("delete from HotWord");
            hotWordDaoOperate.queryRaw("update sqlite_sequence SET seq = 0 where name ='HotWord'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
