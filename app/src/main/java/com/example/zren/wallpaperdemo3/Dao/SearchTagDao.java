package com.example.zren.wallpaperdemo3.dao;

import android.content.Context;

import com.example.zren.wallpaperdemo3.db.DBHelper;
import com.example.zren.wallpaperdemo3.db.SearchTag;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysy on 2016/11/2.
 */
public class SearchTagDao {
    private Context context;
    private Dao<SearchTag, Integer> searchTagDaoOperate;
    private DBHelper dbHelper;

    public SearchTagDao(Context context){
        this.context=context;
        dbHelper= DBHelper.getHelper(context);
        try {
            searchTagDaoOperate=dbHelper.getDao(SearchTag.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一行数据
     * @param searchTag
     */
    public void add(SearchTag searchTag){
        try {
            searchTagDaoOperate.create(searchTag);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一行数据
     * @param imgID
     */
    public void delete(String imgID){
        try {
            DeleteBuilder deleteBuilder=searchTagDaoOperate.deleteBuilder();
            deleteBuilder.where().eq("imgID",imgID);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按 imgID 查询
     * @param imgID
     * @return
     */
    public SearchTag query(String imgID){
        SearchTag searchTag= null;
        List<SearchTag> searchTags=new ArrayList<>();
        try {
            QueryBuilder builder=searchTagDaoOperate.queryBuilder();
            searchTags=builder.where().eq("imgID",imgID).query();
            searchTag=searchTags.get(0);
            return searchTag;
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
            List<SearchTag> searchTags=searchTagDaoOperate.queryForAll();
            return searchTags;
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
            searchTagDaoOperate.queryRaw("delete from SearchTag");
            searchTagDaoOperate.queryRaw("update sqlite_sequence SET seq = 0 where name ='SearchTag'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
