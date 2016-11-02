package com.example.zren.wallpaperdemo3.dao;

import android.content.Context;

import com.example.zren.wallpaperdemo3.db.DBHelper;
import com.example.zren.wallpaperdemo3.domain.Row_Img;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysy on 2016/11/2.
 */
public class Row_img_Dao {
    private Context context;
    private Dao<Row_Img, Integer> rowImgDaoOperate;
    private DBHelper dbHelper;

    public Row_img_Dao(Context context){
        this.context=context;
        dbHelper= DBHelper.getHelper(context);
        try {
            rowImgDaoOperate=dbHelper.getDao(Row_Img.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一行数据
     * @param row_Img
     */
    public void add(Row_Img row_Img){
        try {
            rowImgDaoOperate.create(row_Img);
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
            DeleteBuilder deleteBuilder=rowImgDaoOperate.deleteBuilder();
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
    public Row_Img query(String imgID){
        Row_Img row_Img= null;
        List<Row_Img> row_Imgs=new ArrayList<>();
        try {
            QueryBuilder builder=rowImgDaoOperate.queryBuilder();
            row_Imgs=builder.where().eq("imgID",imgID).query();
            row_Img=row_Imgs.get(0);
            return row_Img;
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
            List<Row_Img> row_Imgs=rowImgDaoOperate.queryForAll();
            return row_Imgs;
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
            rowImgDaoOperate.queryRaw("delete from RowImage");
            rowImgDaoOperate.queryRaw("update sqlite_sequence SET seq = 0 where name ='RowImage'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
