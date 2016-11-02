package com.example.zren.wallpaperdemo3.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by ysy on 2016/11/2.
 */
@DatabaseTable(tableName = "Topic")
public class Topic {

    /**
     * 主键
     */
    @DatabaseField(generatedId = true)
    private int id;

    /**
     * 主题ID
     */
    @DatabaseField(unique = true)
    private String topicID;

    /**
     * 主题名称
     */
    @DatabaseField
    private String topicName;

    /**
     * 隐藏路径
     */
    @DatabaseField
    private String coverPath;

    /**
     * 集中路径
     */
    @DatabaseField
    private String focusPicturePath;

    /**
     * 描述
     */
    @DatabaseField
    private String description;

    /**
     * 类型
     */
    @DatabaseField
    private String type;

    /**
     * 状态
     */
    @DatabaseField
    private String status;

    public Topic() {
    }

    public Topic(String coverPath, String focusPicturePath, String description, String status, String topicID, String topicName, String type) {
        this.coverPath = coverPath;
        this.focusPicturePath = focusPicturePath;
        this.description = description;
        this.status = status;
        this.topicID = topicID;
        this.topicName = topicName;
        this.type = type;
    }

    public Topic(String topicID, String coverPath, String description, String focusPicturePath, int id, String status, String topicName, String type) {
        this.topicID = topicID;
        this.coverPath = coverPath;
        this.description = description;
        this.focusPicturePath = focusPicturePath;
        this.id = id;
        this.status = status;
        this.topicName = topicName;
        this.type = type;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFocusPicturePath() {
        return focusPicturePath;
    }

    public void setFocusPicturePath(String focusPicturePath) {
        this.focusPicturePath = focusPicturePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "coverPath='" + coverPath + '\'' +
                ", id=" + id +
                ", topicID='" + topicID + '\'' +
                ", topicName='" + topicName + '\'' +
                ", focusPicturePath='" + focusPicturePath + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
