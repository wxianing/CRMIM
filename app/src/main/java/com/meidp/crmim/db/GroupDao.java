package com.meidp.crmim.db;

/**
 * Package： com.meidp.crmim.db
 * Author： wxianing
 * 作  用：
 * 时  间： 2016/8/23
 */
public class GroupDao {
//    private Context mContext;
//    private Dao<GroupEntity, Integer> groupEntitiesOpen;
//    private DatabaseHelper mHelper;
//    public GroupDao(Context mContext) {
//        this.mContext = mContext;
//        mHelper = DatabaseHelper.getHelper(mContext);
//        try {
//            groupEntitiesOpen = mHelper.getDao(GroupEntity.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //添加数据
//    public void add(GroupEntity group) {
//        try {
//            groupEntitiesOpen.create(group);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //查询整个表的数据
//    public List<GroupEntity> queryAll() {
//        List<GroupEntity> list = null;
//        try {
//            list = groupEntitiesOpen.queryForAll();
//            return list;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public GroupEntity queryById(String discussionId) {
//        QueryBuilder builder = groupEntitiesOpen.queryBuilder();
//        try {
//            builder.where().eq("discussionId", discussionId);
//            GroupEntity entity = (GroupEntity) builder.query();
//            return entity;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public GroupEntity queryByName(String discussionName) {
//        QueryBuilder builder = groupEntitiesOpen.queryBuilder();
//        try {
//            builder.where().eq("discussionName", discussionName);
//            GroupEntity entity = (GroupEntity) builder.query();
//            return entity;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
