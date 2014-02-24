package com.instrio.fly2bee.signup;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.instrio.fly2bee.security.ProviderMedia;

public class SignUp implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String FIELD_NAME_EMAIL = "email";

	@NotEmpty
    @Size(max = 100)
    private String userId;
	
    @Email
    @Size(max = 100)
    private String email;

    @NotEmpty
    @Size(max = 100)
    private String name;
    
    @NotEmpty
    @Size(max = 100)
    private String userName;
    
    @Size(max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    private String password;

    private String passwordVerification;

    private ProviderMedia providerId;

    public SignUp() {

    }

    public boolean isNormalRegistration() {
        return providerId == null;
    }

    public boolean isSocialSignIn() {
        return providerId != null;
    }
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
		this.userName = (userName != null ? userName : name);
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerification() {
        return passwordVerification;
    }

    public void setPasswordVerification(String passwordVerification) {
        this.passwordVerification = passwordVerification;
    }

    public ProviderMedia getProviderId() {
        return providerId;
    }

    public void setProviderId(ProviderMedia providerId) {
        this.providerId = providerId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("providerId", providerId)
                .toString();
    }
}
