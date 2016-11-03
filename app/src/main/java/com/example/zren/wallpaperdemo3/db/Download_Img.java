package com.example.zren.wallpaperdemo3.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ysy on 2016/11/1.
 */
@DatabaseTable(tableName = "MyDownload")
public class Download_Img {

    /**
     * 主键
     */
    @DatabaseField(generatedId = true)
    private int id;

    /**
     * 图片id
     */
    @DatabaseField(unique = true)
    private String imgID;

    /**
     * 本地路径
     */
    @DatabaseField
    private String localPath;

    public Download_Img() {
    }

    public Download_Img(String imgID, String localPath) {
        this.imgID = imgID;
        this.localPath = localPath;
    }

    public Download_Img(int id, String imgID, String localPath) {
        this.id = id;
        this.imgID = imgID;
        this.localPath = localPath;
    }

    @Override
    public String toString() {
        return "Download_Img{" +
                "id=" + id +
                ", imgID='" + imgID + '\'' +
                ", localPath='" + localPath + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgID() {
        return imgID;
    }

    public void setImgID(String imgID) {
        this.imgID = imgID;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
