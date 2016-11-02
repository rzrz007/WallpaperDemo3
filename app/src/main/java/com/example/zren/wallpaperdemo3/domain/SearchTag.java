package com.example.zren.wallpaperdemo3.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ysy on 2016/11/2.
 */
@DatabaseTable(tableName = "SearchTag")
public class SearchTag {

    /**
     * 主键
     */
    @DatabaseField(generatedId = true)
    private int id;

    /**
     * 图片ID
     */
    @DatabaseField
    private int imgID;

    /**
     * 关键字
     */
    @DatabaseField
    private String keyWord;

    /**
     * 图片集
     */
    @DatabaseField
    private String imgs;

    /**
     * 类型
     */
    @DatabaseField
    private String type;

    public SearchTag() {
    }

    public SearchTag(int imgID, String keyWord, String imgs, String type) {
        this.imgID = imgID;
        this.keyWord = keyWord;
        this.imgs = imgs;
        this.type = type;
    }

    public SearchTag(int id, int imgID, String imgs, String keyWord, String type) {
        this.id = id;
        this.imgID = imgID;
        this.imgs = imgs;
        this.keyWord = keyWord;
        this.type = type;
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

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SearchTag{" +
                "id=" + id +
                ", imgID=" + imgID +
                ", keyWord='" + keyWord + '\'' +
                ", imgs='" + imgs + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
