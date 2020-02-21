package com.sky.text;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.sky.text.greendao.DBManager;

/**
 * @Description: 用一句话描述
 * @CreateTime: 2020年02月21日 18:26
 * @CreateAuthor: Mack
 */
public class FriendBeanDetailsActivity extends AppCompatActivity {
	@BindView(R.id.ratingBar1) RatingBar mRatingBar;
	@BindView(R.id.iv_head) ImageView ivHead;
	@BindView(R.id.tv_des) TextView tvDes;
	private FriendBean friendBean;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friendbean_details);
		ButterKnife.bind(this);
		initData();
		initListener();
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
		friendBean = (FriendBean) getIntent().getSerializableExtra("friendBean");
		if (friendBean != null) {
			mRatingBar.setRating(friendBean.getRating());
			Glide.with(FriendBeanDetailsActivity.this).load(friendBean.getIcoUrl()).into(ivHead);
			tvDes.setText(friendBean.getSignature());
		}
	}

	private void initListener() {
		mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
				float i = mRatingBar.getRating();
				friendBean.setRating(i);
				DBManager.getInstance().saveOrUpdateFriend(friendBean);
				//Toast.makeText(FriendBeanDetailsActivity.this.getApplicationContext(),
				//	String.valueOf(i), Toast.LENGTH_LONG).show();
			}
		});
	}
}