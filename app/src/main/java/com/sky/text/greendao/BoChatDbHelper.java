package com.sky.text.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.sky.text.Constan;

public class BoChatDbHelper {

    private DaoSession daoSession;

    private Context mContext;

    private static BoChatDbHelper instance;

    public BoChatDbHelper(){

    }

    public static void init(Context context){
        instance=new BoChatDbHelper(context);
    }

    public BoChatDbHelper(Context context){
        this.mContext=context;
        instance=this;
    }

    public static BoChatDbHelper getInstance(){
//        if(instance==null){
//            synchronized (BochatDbHelper.class){
//                instance=new BochatDbHelper();
//            }
//        }
        return instance;
    }

    public synchronized void initDatabase() {
        try{
            BochatDbOpenHelper openHelper = new BochatDbOpenHelper(mContext, Constan.DbName);
            SQLiteDatabase db = openHelper.getWritableDatabase();
            DaoMaster master = new DaoMaster(db);
            daoSession = master.newSession();
            //LogUtil.LogDebug("database", "database[" + Constan.DbName + "] initial successfully, daoSession is " + daoSession);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public FriendBeanDao getFriendBeanDao(){
	    FriendBeanDao friendBeanDao=null;
        if(daoSession!=null){
	        friendBeanDao=daoSession.getFriendBeanDao();
        }
        return friendBeanDao;
    }


    public synchronized void release() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }

        instance = null;
    }

}
