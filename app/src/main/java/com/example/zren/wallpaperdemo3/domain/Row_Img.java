package com.example.zren.wallpaperdemo3.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ysy on 2016/11/2.
 */
@DatabaseTable(tableName = "RowImage")
public class Row_Img {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private int imgID;

    @DatabaseField
    private String imgUrl;

    @DatabaseField
    private String thumbnailUrl;

    @DatabaseField
    private String type;

    @DatabaseField
    private String bigID;

    public Row_Img(String bigID, int imgID, String imgUrl, String thumbnailUrl, String type) {
        this.bigID = bigID;
        this.imgID = imgID;
        this.imgUrl = imgUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.type = type;
    }

    public Row_Img(String bigID, int id, int imgID, String imgUrl, String thumbnailUrl, String type) {
        this.bigID = bigID;
        this.id = id;
        this.imgID = imgID;
        this.imgUrl = imgUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.type = type;
    }

    public String getBigID() {
        return bigID;
    }

    public void setBigID(String bigID) {
        this.bigID = bigID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Row_Img{" +
                "bigID='" + bigID + '\'' +
                ", id=" + id +
                ", imgID=" + imgID +
                ", imgUrl='" + imgUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Row_Img() {
    }
}
