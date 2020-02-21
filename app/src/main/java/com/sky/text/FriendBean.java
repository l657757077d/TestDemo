package com.sky.text;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Description: 用一句话描述
 * @CreateTime: 2020年02月21日
 * @CreateAuthor: Mack
 */
@Entity(
	nameInDb = "table_user"
)
public class FriendBean implements Serializable {
	private static final long serialVersionUID = -1176L;

	@Id
	private Long id;
	private String icoUrl;
	private String name;
	private String signature;
	private float rating;
	@Generated(hash = 2083880218)
	public FriendBean(Long id, String icoUrl, String name, String signature,
			float rating) {
		this.id = id;
		this.icoUrl = icoUrl;
		this.name = name;
		this.signature = signature;
		this.rating = rating;
	}
	@Generated(hash = 152145004)
	public FriendBean() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIcoUrl() {
		return this.icoUrl;
	}
	public void setIcoUrl(String icoUrl) {
		this.icoUrl = icoUrl;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSignature() {
		return this.signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public float getRating() {
		return this.rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

	
}
