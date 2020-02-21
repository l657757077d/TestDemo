package com.sky.text;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.sky.text.greendao.DBManager;
import java.util.List;

public class MainActivity extends AppCompatActivity {
	@BindView(R.id.rc_friend) RecyclerView rcFriend;
	@BindView(R.id.ratingBar1) RatingBar mRatingBar;
	@BindView(R.id.iv_head) ImageView ivHead;
	@BindView(R.id.tv_des) TextView tvDes;
	@BindView(R.id.ll_info) LinearLayout llInfo;
	private List<FriendBean> mList;
	private final int ORIENTATION_LANDSCAPE = 2;
	private int index = 0;
	private FriendAdapter adapter;
	private int orientation;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		initData();
		setAdapter();
		initListener();
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		mList = DBManager.getInstance().findAllFriend();
		if (mList == null || mList.size() == 0) {
			mList.add(new FriendBean(1L,
				"http://img2.imgtn.bdimg.com/it/u=3978455617,844481405&fm=26&gp=0.jpg", "笑笑1",
				"笑一笑十年少", 1));
			mList.add(new FriendBean(2L,
				"http://img4.imgtn.bdimg.com/it/u=3194544764,153238520&fm=26&gp=0.jpg", "笑笑2",
				"笑一笑十年少", 2));
			mList.add(new FriendBean(3L,
				"http://img3.imgtn.bdimg.com/it/u=3356724737,3053734736&fm=26&gp=0.jpg", "笑笑3",
				"笑一笑十年少", 3));
			mList.add(new FriendBean(4L,
				"http://img5.imgtn.bdimg.com/it/u=2221286028,2650620459&fm=26&gp=0.jpg", "笑笑4",
				"笑一笑十年少", 4));
			mList.add(new FriendBean(5L,
				"http://img0.imgtn.bdimg.com/it/u=3129864253,446353757&fm=26&gp=0.jpg", "笑笑5",
				"笑一笑十年少", 5));
			mList.add(new FriendBean(6L,
				"http://img2.imgtn.bdimg.com/it/u=4066494201,2784898485&fm=26&gp=0.jpg", "笑笑6",
				"笑一笑十年少", 3));
			DBManager.getInstance().saveOrUpdateFriend(mList);
		}
	}

	/**
	 * 设置适配器
	 */
	private void setAdapter() {
		GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
		rcFriend.setLayoutManager(layoutManager);
		adapter = new FriendAdapter(MainActivity.this, mList);
		rcFriend.setAdapter(adapter);
		adapter.setOnFriendItemListener(new FriendAdapter.OnFriendItemListener() {
			@Override public void onItemClick(FriendBean itemBen, int position) {
				index = position;
				if (orientation == ORIENTATION_LANDSCAPE) {
					setDataInfo(itemBen);
				} else {
					Intent intent = new Intent(MainActivity.this, FriendBeanDetailsActivity.class);
					intent.putExtra("friendBean", itemBen);
					MainActivity.this.startActivity(intent);
				}
			}
		});
	}

	@Override public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		orientation = newConfig.orientation;
		if (orientation == ORIENTATION_LANDSCAPE) {
			Log.i("=====>", "-------------横屏-------------");
			llInfo.setVisibility(View.VISIBLE);
			FriendBean friendBean = adapter.getList().get(index);
			setDataInfo(friendBean);
		} else {
			Log.i("=====>", "-------------竖屏-------------");
			llInfo.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置信息展示
	 */
	private void setDataInfo(FriendBean friendBean) {
		if (friendBean != null) {
			mRatingBar.setRating(friendBean.getRating());
			Glide.with(MainActivity.this).load(friendBean.getIcoUrl()).into(ivHead);
			tvDes.setText(friendBean.getSignature());
		}
	}

	/**
	 * 事件监听绑定
	 */
	private void initListener() {
		mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
				float i = mRatingBar.getRating();
				FriendBean friendBean = adapter.getList().get(index);
				friendBean.setRating(i);
				DBManager.getInstance().saveOrUpdateFriend(friendBean);
				Toast.makeText(MainActivity.this.getApplicationContext(), String.valueOf(i),
					Toast.LENGTH_LONG).show();
			}
		});
	}
}
