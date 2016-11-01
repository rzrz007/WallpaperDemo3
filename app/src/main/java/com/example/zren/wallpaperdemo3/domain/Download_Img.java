package com.example.zren.wallpaperdemo3.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ysy on 2016/11/1.
 */
@DatabaseTable(tableName = "MyDownload")
public class Download_Img {

    @DatabaseField(generatedId = true)
    private int id;

    /**
     * 图片id
     */
    @DatabaseField(unique = true)
    private int imgID;

    /**
     * 本地路径
     */
    @DatabaseField
    private String localPath;

    public Download_Img(int imgID, String localPath) {
        this.imgID = imgID;
        this.localPath = localPath;
    }

    public Download_Img(int id, int imgID, String localPath) {
        this.id = id;
        this.imgID = imgID;
        this.localPath = localPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public Download_Img() {
    }

    @Override
    public String toString() {
        return "Download_Img{" +
                "id=" + id +
                ", imgID=" + imgID +
                ", localPath='" + localPath + '\'' +
                '}';
    }
}
