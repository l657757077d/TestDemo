package com.sky.text.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.sky.text.Constan;
import com.sky.text.FriendBean;
import java.util.List;

public class DBManager implements DatabaseInterface {

	private static DBManager mInstance;
	private DaoMaster.DevOpenHelper openHelper;
	private Context context;
	private FriendBeanDao friendBeanDao;
	private DaoSession daoSession;

	public DBManager(Context context) {
		mInstance = this;
		this.context = context;
		openHelper = new DaoMaster.DevOpenHelper(context, Constan.DbName, null);
		friendBeanDao = BoChatDbHelper.getInstance().getFriendBeanDao();
	}

	/**
	 * 获取单例引用
	 */
	public static DBManager init(Context context) {
		if (context != null) {
			if (mInstance == null) {
				synchronized (DBManager.class) {
					if (mInstance == null) {
						mInstance = new DBManager(context);
					}
				}
			}
		}
		return mInstance;
	}

	public void saveOrUpdateFriend(FriendBean bean) {
		try {
			friendBeanDao.insertOrReplace(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveOrUpdateFriend(List<FriendBean> list) {
		try {
			friendBeanDao.insertOrReplaceInTx(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<FriendBean> findAllFriend() {
		List<FriendBean> list = null;
		if (friendBeanDao != null) {
			list = friendBeanDao.queryBuilder().list();
		}
		return list;
	}

	public static DBManager getInstance() {
		return mInstance;
	}

	/**
	 * 初始化数据库
	 */
	public void initDatabase() {
		release();// 初始化数据库前，先释放掉之前的数据库连接及一些单例，否则会使用同一个数据库--Created by FreddyChen
		BoChatDbHelper.getInstance().initDatabase();
	}

	/**
	 * 释放数据库资源
	 */
	public void release() {
		BoChatDbHelper.getInstance().release();
		mInstance = null;
	}

	/**
	 * 获取可读数据库
	 */
	private SQLiteDatabase getReadableDatabase() {
		if (openHelper == null) {
			openHelper = new DaoMaster.DevOpenHelper(context, Constan.DbName, null);
		}
		SQLiteDatabase db = openHelper.getReadableDatabase();
		return db;
	}

	/**
	 * 获取可写数据库
	 */
	private SQLiteDatabase getWritableDatabase() {
		if (openHelper == null) {
			openHelper = new DaoMaster.DevOpenHelper(context, Constan.DbName, null);
		}
		SQLiteDatabase db = openHelper.getWritableDatabase();
		return db;
	}
}
