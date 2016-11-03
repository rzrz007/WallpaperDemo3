package com.example.zren.wallpaperdemo3.dao;

import android.content.Context;

import com.example.zren.wallpaperdemo3.db.DBHelper;
import com.example.zren.wallpaperdemo3.db.Topic;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysy on 2016/11/2.
 */
public class TopicDao {

    private Context context;
    private Dao<Topic, Integer> topicDaoOperate;
    private DBHelper dbHelper;

    public TopicDao(Context context){
        this.context=context;
        dbHelper=DBHelper.getHelper(context);
        try {
            topicDaoOperate=dbHelper.getDao(Topic.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一行数据
     * @param topic
     */
    public void add(Topic topic){
        try {
            topicDaoOperate.create(topic);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除一行数据
     * @param topicID
     */
    public void delete(String topicID){
        try {
            DeleteBuilder deleteBuilder=topicDaoOperate.deleteBuilder();
            deleteBuilder.where().eq("topicID",topicID);
            deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按 topicID 查询
     * @param topicID
     * @return
     */
    public Topic query(String topicID){
        Topic topic= null;
        List<Topic> topics=new ArrayList<>();
        try {
            QueryBuilder builder=topicDaoOperate.queryBuilder();
            topics=builder.where().eq("topicID",topicID).query();
            topic=topics.get(0);
            return topic;
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
            List<Topic> topics=topicDaoOperate.queryForAll();
            return topics;
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
            topicDaoOperate.queryRaw("delete from Topic");
            topicDaoOperate.queryRaw("update sqlite_sequence SET seq = 0 where name ='Topic'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
