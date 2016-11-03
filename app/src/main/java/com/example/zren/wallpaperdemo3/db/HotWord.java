package com.example.zren.wallpaperdemo3.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ysy on 2016/11/2.
 */
@DatabaseTable(tableName = "HotWord")
public class HotWord {

    /**
     * 主键
     */
    @DatabaseField(generatedId = true)
    private int id;

    /**
     * 名称
     */
    @DatabaseField
    private String name;

    /**
     * 热度
     */
    @DatabaseField
    private String hot;

    public HotWord() {
    }

    public HotWord(String name, String hot) {
        this.name = name;
        this.hot = hot;
    }

    public HotWord(String hot, int id, String name) {
        this.hot = hot;
        this.id = id;
        this.name = name;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HotWord{" +
                "hot='" + hot + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
