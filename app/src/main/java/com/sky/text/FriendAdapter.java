package com.sky.text;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * @Description: 用一句话描述
 * @CreateTime: 2020年02月21日 17:20
 * @CreateAuthor: Mack
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.MyViewHolder> {
	private Context mContext;
	private List<FriendBean> mData;
	private OnFriendItemListener onFriendItemListener;

	public FriendAdapter(Context context, List<FriendBean> mData) {
		this.mContext = context;
		this.mData = mData;
	}

	@NonNull @Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.item_friend, parent, false);
		return new MyViewHolder(view);
	}

	@Override public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
		FriendBean itemBean = mData.get(position);
		Glide.with(mContext).load(itemBean.getIcoUrl()).into(holder.ivHead);
		holder.tvName.setText(itemBean.getName());
	}

	@Override public int getItemCount() {
		return mData == null ? 0 : mData.size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private ImageView ivHead;
		private TextView tvName;

		public MyViewHolder(View itemView) {
			super(itemView);
			ivHead = (ImageView) itemView.findViewById(R.id.iv_head);
			tvName = (TextView) itemView.findViewById(R.id.tv_name);
			itemView.setOnClickListener(this);
		}

		@Override public void onClick(View view) {
			if (onFriendItemListener != null) {
				onFriendItemListener.onItemClick(mData.get(getAdapterPosition()),
					getAdapterPosition());
			}
		}
	}

	public List<FriendBean> getList() {
		return mData;
	}

	public void setOnFriendItemListener(OnFriendItemListener onFriendItemListener) {
		this.onFriendItemListener = onFriendItemListener;
	}

	public interface OnFriendItemListener {
		void onItemClick(FriendBean itemBen, int position);
	}
}