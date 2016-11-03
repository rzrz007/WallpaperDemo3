package com.example.zren.wallpaperdemo3.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ysy on 2016/11/2.
 */
@DatabaseTable(tableName ="CategoryItem")
public class CategoryItem {
    /**
     * 主键
     */
    @DatabaseField(generatedId = true)
    private int id;

    /**
     * 条目ID
     */
    @DatabaseField
    private String itemID;

    /**
     * 种类名称
     */
    @DatabaseField
    private String picCategoryName;

    /**
     * 降序词条
     */
    @DatabaseField
    private String descWords;

    /**
     * 图片种类
     */
    @DatabaseField
    private String categoryPic;

    public CategoryItem() {
    }

    public CategoryItem(String categoryPic, String descWords, String itemID, String picCategoryName) {
        this.categoryPic = categoryPic;
        this.descWords = descWords;
        this.itemID = itemID;
        this.picCategoryName = picCategoryName;
    }

    public CategoryItem(String categoryPic, String descWords, int id, String itemID, String picCategoryName) {
        this.categoryPic = categoryPic;
        this.descWords = descWords;
        this.id = id;
        this.itemID = itemID;
        this.picCategoryName = picCategoryName;
    }

    public String getCategoryPic() {
        return categoryPic;
    }

    public void setCategoryPic(String categoryPic) {
        this.categoryPic = categoryPic;
    }

    public String getDescWords() {
        return descWords;
    }

    public void setDescWords(String descWords) {
        this.descWords = descWords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getPicCategoryName() {
        return picCategoryName;
    }

    public void setPicCategoryName(String picCategoryName) {
        this.picCategoryName = picCategoryName;
    }

    @Override
    public String toString() {
        return "CategoryItem{" +
                "categoryPic='" + categoryPic + '\'' +
                ", id=" + id +
                ", itemID='" + itemID + '\'' +
                ", picCategoryName='" + picCategoryName + '\'' +
                ", descWords='" + descWords + '\'' +
                '}';
    }
}
