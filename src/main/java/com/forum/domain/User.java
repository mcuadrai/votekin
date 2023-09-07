package com.forum.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.forum.responsemodels.ResponseModel.GetIt;
import com.forum.responsemodels.ResponseModel.SkipIt;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;


@Entity
@Table(name="users", indexes = {
	@Index(columnList = "email", unique=true)
})
public class User implements Serializable, UserDetails 
{
	

	/**
	 * 
	 */

//	public static enum Role {
//		UNVERIFIED, ADMIN, BLOCKED
//	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	//@Email
	@Column(nullable=false, length=250)
	private String email;
	
	@NotEmpty
	@Column(nullable=false, length=100)
	private String name;
	
	private String accountName;

	@SkipIt
	@NotEmpty
	@Column(nullable=false)
	private String password;
	
	@SkipIt
	private boolean enabled;
	
	@SkipIt
	@Column(length=36)
	private String verificationCode;

	@SkipIt
	@Column(length=36, unique=true)
	private String resetPasswordCode;
	
	@SkipIt
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private List<Role> roles;
	
	@SkipIt
	@Transient
	private boolean editable;

	@GetIt
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Ciudadano ciudadano;
	
	private String filters;
	
	private String themeFilters;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public void setPassword(String password) {
		this.password = password;
	}


	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getResetPasswordCode() {
		
		return resetPasswordCode;
	}

	public void setResetPasswordCode(String resetPasswordCode) {
		this.resetPasswordCode = resetPasswordCode;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        for(Role role: this.getRoles()) {
        	//logger.info("Role: ".concat(role.getAuthority()));
        	authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	public User() {
		roles = new ArrayList<Role>();
		//ciudadano = new Ciudadano();
	}
     
	

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public Ciudadano getCiudadano() {
		return ciudadano;
	}

	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}
	
	


	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}


	


	public String getThemeFilters() {
		return themeFilters;
	}

	public void setThemeFilters(String themeFilters) {
		this.themeFilters = themeFilters;
	}





	private static final long serialVersionUID = 1L;
	
}
