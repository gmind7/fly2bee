package com.instrio.fly2bee.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.joda.time.DateTime;

@Entity
@Table(name = "UserConnection", uniqueConstraints = {@UniqueConstraint(name="UserConnectionRank", columnNames = {"userId", "providerId", "rank"})})
public class UserConnection implements Serializable {

    private static final long serialVersionUID = 1L;

	public UserConnection() {

    }
	
	@EmbeddedId
	private UserConnectionIDs ids;
	
	@Column(length = 11, nullable=false)
	private int rank;
	
	@Column(length = 255)
	private String displayName;
	
	@Column(length = 512)
	private String profileUrl;
	
	@Column(length = 512)
	private String imageUrl;
	
	@Column(length = 255, nullable=false)
	private String accessToken;
	
	@Column(length = 255)
	private String secret;
	
	@Column(length = 255)
	private String refreshToken;
	
	@Column(columnDefinition="BIGINT(20) NULL DEFAULT NULL")
	private DateTime expireTime;

	public UserConnectionIDs getIds() {
		return ids;
	}

	public void setIds(UserConnectionIDs ids) {
		this.ids = ids;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public DateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(DateTime expireTime) {
		this.expireTime = expireTime;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessToken == null) ? 0 : accessToken.hashCode());
		result = prime * result
				+ ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result
				+ ((expireTime == null) ? 0 : expireTime.hashCode());
		result = prime * result + ((ids == null) ? 0 : ids.hashCode());
		result = prime * result
				+ ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result
				+ ((profileUrl == null) ? 0 : profileUrl.hashCode());
		result = prime * result + rank;
		result = prime * result
				+ ((refreshToken == null) ? 0 : refreshToken.hashCode());
		result = prime * result + ((secret == null) ? 0 : secret.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserConnection other = (UserConnection) obj;
		if (accessToken == null) {
			if (other.accessToken != null)
				return false;
		} else if (!accessToken.equals(other.accessToken))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (expireTime == null) {
			if (other.expireTime != null)
				return false;
		} else if (!expireTime.equals(other.expireTime))
			return false;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (profileUrl == null) {
			if (other.profileUrl != null)
				return false;
		} else if (!profileUrl.equals(other.profileUrl))
			return false;
		if (rank != other.rank)
			return false;
		if (refreshToken == null) {
			if (other.refreshToken != null)
				return false;
		} else if (!refreshToken.equals(other.refreshToken))
			return false;
		if (secret == null) {
			if (other.secret != null)
				return false;
		} else if (!secret.equals(other.secret))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserConnection [ids=" + ids + ", rank=" + rank
				+ ", displayName=" + displayName + ", profileUrl=" + profileUrl
				+ ", imageUrl=" + imageUrl + ", accessToken=" + accessToken
				+ ", secret=" + secret + ", refreshToken=" + refreshToken
				+ ", expireTime=" + expireTime + "]";
	}
	
}
