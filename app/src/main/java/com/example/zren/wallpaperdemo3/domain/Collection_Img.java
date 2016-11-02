package com.example.zren.wallpaperdemo3.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ysy on 2016/11/1.
 */
//表明这是数据库中的一张表，表名为 MyCollection
@DatabaseTable(tableName = "MyCollection")
public class Collection_Img {

    /**
     * 主键
     */
    @DatabaseField(generatedId = true)
    private int id;
    /**
     * 图片ID
     */
    @DatabaseField(unique = true)
    private String imgID;

    /**
     * 小图链接
     */
    @DatabaseField
    private String middleImgUrl;

    /**
     * 大图链接
     */
    @DatabaseField
    private String bigImgUrl;

    public Collection_Img() {
    }

    public Collection_Img(String bigImgUrl, String imgID, String middleImgUrl) {
        this.bigImgUrl = bigImgUrl;
        this.imgID = imgID;
        this.middleImgUrl = middleImgUrl;
    }

    public Collection_Img(String bigImgUrl, int id, String imgID, String middleImgUrl) {
        this.bigImgUrl = bigImgUrl;
        this.id = id;
        this.imgID = imgID;
        this.middleImgUrl = middleImgUrl;
    }

    public String getBigImgUrl() {
        return bigImgUrl;
    }

    public void setBigImgUrl(String bigImgUrl) {
        this.bigImgUrl = bigImgUrl;
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

    public String getMiddleImgUrl() {
        return middleImgUrl;
    }

    public void setMiddleImgUrl(String middleImgUrl) {
        this.middleImgUrl = middleImgUrl;
    }

    @Override
    public String toString() {
        return "Collection_Img{" +
                "bigImgUrl='" + bigImgUrl + '\'' +
                ", id=" + id +
                ", imgID='" + imgID + '\'' +
                ", middleImgUrl='" + middleImgUrl + '\'' +
                '}';
    }
}
