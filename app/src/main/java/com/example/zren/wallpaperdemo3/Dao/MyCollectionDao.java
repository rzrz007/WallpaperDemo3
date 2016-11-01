package com.example.zren.wallpaperdemo3.dao;

import android.content.Context;

import com.example.zren.wallpaperdemo3.db.DBHelper;
import com.example.zren.wallpaperdemo3.domain.Collection_Img;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysy on 2016/11/1.
 */
public class MyCollectionDao {

    private Context context;
    private Dao<Collection_Img, Integer> myCollectionDaoOperate;
    private DBHelper dbHelper;

    public MyCollectionDao(Context context){
        this.context=context;
        dbHelper=DBHelper.getHelper(context);
        try {
            myCollectionDaoOperate=dbHelper.getDao(Collection_Img.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一行数据
     * @param collection_img
     */
    public void add(Collection_Img collection_img){
        try {
            myCollectionDaoOperate.create(collection_img);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一行数据
     * @param imgID
     */
    public void delete(int imgID){
        try {
            DeleteBuilder deleteBuilder=myCollectionDaoOperate.deleteBuilder();
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
    public Collection_Img query(int imgID){
        Collection_Img collection_img= null;
        List<Collection_Img> collection_imgs=new ArrayList<>();
        try {
            QueryBuilder builder=myCollectionDaoOperate.queryBuilder();
            collection_imgs=builder.where().eq("imgID",imgID).query();
            collection_img=collection_imgs.get(0);
            return collection_img;
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
            List<Collection_Img> collection_imgs=myCollectionDaoOperate.queryForAll();
            return collection_imgs;
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
            myCollectionDaoOperate.queryRaw("delete from MyCollection");
            myCollectionDaoOperate.queryRaw("update sqlite_sequence SET seq = 0 where name ='MyCollection'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
