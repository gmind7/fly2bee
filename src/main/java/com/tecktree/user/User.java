package com.tecktree.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.tecktree.security.AuthRole;
import com.tecktree.security.ProviderMedia;

@Entity
@Table(name = "Users")
public class User extends AbstractPersistable<Long> {

    private static final long serialVersionUID = 1L;

	public User() {

    }
	
	@Column(length = 100, unique = true)
    private String userId;

	@Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ProviderMedia providerId;
    
	@Column(length = 100)
    private String email;
    
    @Column(length = 100, nullable = false)
    private String name;
    
    @Column(length = 100, nullable = false)
    private String userName;

    @Column(length = 100,nullable = false)
    private String firstName;

    @Column(length = 100, nullable = true)
    private String lastName;

    @Column(length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private AuthRole role;
    
    @Column(nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdDate;

	@Column(nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime lastModifiedDate;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
        return email;
    }
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public AuthRole getRole() {
        return role;
    }
    
    public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(AuthRole role) {
		this.role = role;
	}

	public void setProviderId(ProviderMedia providerId) {
		this.providerId = providerId;
	}

	public ProviderMedia getProviderId() {
        return providerId;
    }
	
	public DateTime getCreatedDate() {

		return null == createdDate ? null : new DateTime(createdDate);
	}

	public void setCreatedDate(final DateTime createdDate) {

		this.createdDate = null == createdDate ? null : createdDate.toDateTime();
	}

	public DateTime getLastModifiedDate() {

		return null == lastModifiedDate ? null : new DateTime(lastModifiedDate);
	}

	public void setLastModifiedDate(final DateTime lastModifiedDate) {

		this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDateTime();
	}
	
    @PrePersist
    public void prePersist() {
        DateTime now = DateTime.now();
        this.createdDate = now;
        this.lastModifiedDate = now;
        
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModifiedDate = DateTime.now();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("userId", userId)
                .append("email", email)
                .append("name", name)
                .append("userName", userName)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("providerId", getProviderId())
                .toString();
    }
    
    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {

        private User user;

        public Builder() {
            user = new User();
            user.role = AuthRole.ROLE_USER;
        }
        
        public Builder userId(String userId) {
            user.userId = userId;
            return this;
        }

        public Builder email(String email) {
            user.email = email;
            return this;
        }
        
        public Builder name(String name) {
            user.name = name;
            return this;
        }

        public Builder userName(String userName) {
            user.userName = userName;
            return this;
        }

        public Builder firstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            user.password = password;
            return this;
        }

        public Builder providerId(ProviderMedia providerId) {
            user.providerId = providerId;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
