package com.tecktree.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserConnectionIDs implements Serializable {

    private static final long serialVersionUID = 1L;

	public UserConnectionIDs() {
		
    }
	
	public UserConnectionIDs(String userId, String providerId, String providerUserId) {
		this.userId = userId;
		this.providerId = providerId;
		this.providerUserId = providerUserId;
    }
	
	@Column(length = 255, nullable=false)
    private String userId;
	
	@Column(length = 255, nullable=false)
    private String providerId;
	
	@Column(columnDefinition="VARCHAR(255) NOT NULL DEFAULT ''")
    private String providerUserId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((providerId == null) ? 0 : providerId.hashCode());
		result = prime * result
				+ ((providerUserId == null) ? 0 : providerUserId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserConnectionIDs other = (UserConnectionIDs) obj;
		if (providerId == null) {
			if (other.providerId != null)
				return false;
		} else if (!providerId.equals(other.providerId))
			return false;
		if (providerUserId == null) {
			if (other.providerUserId != null)
				return false;
		} else if (!providerUserId.equals(other.providerUserId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserConnectionIDs [userId=" + userId + ", providerId="
				+ providerId + ", providerUserId=" + providerUserId + "]";
	}
	
}
