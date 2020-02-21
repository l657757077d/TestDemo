package com.sky.text;

import android.app.Application;
import com.sky.text.greendao.BoChatDbHelper;
import com.sky.text.greendao.DBManager;

/**
 * @Description: 用一句话描述
 * @CreateTime: 2020年02月21日
 * @CreateAuthor: Mack
 */
public class MainApplication extends Application {

	@Override public void onCreate() {
		super.onCreate();
		initDbManagerContext();
	}


	public void initDbManagerContext() {
		BoChatDbHelper.init(this);
		BoChatDbHelper.getInstance().initDatabase();
		DBManager.init(this);
	}
}
