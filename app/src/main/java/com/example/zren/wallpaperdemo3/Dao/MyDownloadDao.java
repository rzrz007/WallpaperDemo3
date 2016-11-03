package com.example.zren.wallpaperdemo3.dao;

import android.content.Context;

import com.example.zren.wallpaperdemo3.db.DBHelper;
import com.example.zren.wallpaperdemo3.db.Download_Img;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysy on 2016/11/1.
 */
public class MyDownloadDao {
    private Context context;
    private Dao<Download_Img, Integer> myDownloadDaoOperate;
    private DBHelper dbHelper;

    public MyDownloadDao(Context context){
        this.context=context;
        dbHelper=DBHelper.getHelper(context);
        try {
            myDownloadDaoOperate=dbHelper.getDao(Download_Img.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一行数据
     * @param download_img
     */
    public void add(Download_Img download_img){
        try {
            myDownloadDaoOperate.create(download_img);
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
            DeleteBuilder deleteBuilder=myDownloadDaoOperate.deleteBuilder();
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
    public Download_Img query(String imgID){
        Download_Img download_img= null;
        List<Download_Img> download_imgs=new ArrayList<>();
        try {
            QueryBuilder builder=myDownloadDaoOperate.queryBuilder();
            download_imgs=builder.where().eq("imgID",imgID).query();
            download_img=download_imgs.get(0);
            return download_img;
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
            List<Download_Img> download_imgs=myDownloadDaoOperate.queryForAll();
            return download_imgs;
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
            myDownloadDaoOperate.queryRaw("delete from MyDownload");
            myDownloadDaoOperate.queryRaw("update sqlite_sequence SET seq = 0 where name ='MyDownload'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
