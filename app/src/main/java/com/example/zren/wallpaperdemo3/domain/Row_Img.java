package com.example.zren.wallpaperdemo3.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ysy on 2016/11/2.
 */
@DatabaseTable(tableName = "RowImage")
public class Row_Img {

    /**
     * 主键
     */
    @DatabaseField(generatedId = true)
    private int id;

    /**
     * 图片ID
     */
    @DatabaseField
    private String imgID;

    /**
     * 图片链接
     */
    @DatabaseField
    private String imgUrl;

    /**
     * 缩略图链接
     */
    @DatabaseField
    private String thumbnailUrl;

    /**
     * 类型
     */
    @DatabaseField
    private String type;

    /**
     * 大图ID
     */
    @DatabaseField
    private String bigID;

    public Row_Img() {
    }

    public Row_Img(String bigID, String imgID, String imgUrl, String thumbnailUrl, String type) {
        this.bigID = bigID;
        this.imgID = imgID;
        this.imgUrl = imgUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.type = type;
    }

    public Row_Img(String bigID, int id, String imgID, String imgUrl, String thumbnailUrl, String type) {
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

    public String getImgID() {
        return imgID;
    }

    public void setImgID(String imgID) {
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
                ", imgID='" + imgID + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
