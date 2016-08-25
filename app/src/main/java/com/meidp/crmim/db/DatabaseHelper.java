package com.meidp.crmim.db;

/**
 * Package： com.meidp.crmim.db
 * Author： wxianing
 * 作  用：   保存群组
 * 时  间： 2016/8/23
 */
//public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
//    private static final String DATABASENAME = "crm_group.db";
//    private static final int DATABASEVERSION = 1;
//    private static DatabaseHelper instance;
//
//    private Map<String, Dao> daos = new HashMap<String, Dao>();
//
//    /**
//     * 单例获取该Helper
//     *
//     * @param context
//     * @return
//     */
//    public static synchronized DatabaseHelper getHelper(Context context) {
//        context = context.getApplicationContext();
//        if (instance == null) {
//            synchronized (DatabaseHelper.class) {
//                if (instance == null)
//                    instance = new DatabaseHelper(context);
//            }
//        }
//        return instance;
//    }
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASENAME, null, DATABASEVERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
//        try {
//            TableUtils.clearTable(connectionSource, GroupEntity.class);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
//        try {
//            TableUtils.dropTable(connectionSource, GroupEntity.class, true);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public synchronized Dao getDao(Class clazz) throws SQLException {
//        Dao dao = null;
//        String className = clazz.getSimpleName();
//        if (daos.containsKey(className)) {
//            dao = daos.get(className);
//        }
//        if (dao == null) {
//            dao = super.getDao(clazz);
//            daos.put(className, dao);
//        }
//        return dao;
//    }
//
//    /**
//     * 释放资源
//     */
//    @Override
//    public void close() {
//        super.close();
//
//        for (String key : daos.keySet()) {
//            Dao dao = daos.get(key);
//            dao = null;
//        }
//    }
//}
