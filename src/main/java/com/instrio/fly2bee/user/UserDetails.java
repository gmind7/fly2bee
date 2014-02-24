package com.instrio.fly2bee.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import com.instrio.fly2bee.security.AuthRole;
import com.instrio.fly2bee.security.ProviderMedia;

public class UserDetails extends SocialUser {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String userId;
	
	private String userName;
	
	private String name;
	
	private String firstName;

    private String lastName;

    private AuthRole role;

    private ProviderMedia providerId;

    public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }
    
    public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getName() {
        return name;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AuthRole getRole() {
        return role;
    }

    public ProviderMedia getProviderId() {
        return providerId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", userId)
                .append("name", name)
                .append("username", userName)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("role", role)
                .append("providerId", providerId)
                .toString();
    }

    public static class Builder {

        private Long id;

        private String name;
         
        private String userName;
        
        private String userId;
        
        private String firstName;

        private String lastName;

        private String password;

        private AuthRole role;

        private ProviderMedia providerId;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
                
        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }
        
        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }

            this.password = password;
            return this;
        }

        public Builder role(AuthRole role) {
            this.role = role;

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            this.authorities.add(authority);

            return this;
        }

        public Builder providerId(ProviderMedia providerId) {
            this.providerId = providerId;
            return this;
        }
        
        public UserDetails build() {
            UserDetails user = new UserDetails(userName, password, authorities);

            user.id = id;
            user.userId = userId;
            user.userName = userName;
            user.name = name;
            user.firstName = firstName;
            user.lastName = lastName;
            user.role = role;
            user.providerId = providerId;

            return user;
        }
    }
}
