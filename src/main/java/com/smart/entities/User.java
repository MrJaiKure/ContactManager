package com.smart.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="USER")
public class User implements UserDetails {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotBlank(message="User name can not be Empty")
	@Size(min=2,max=20,message="user name must be between 2 to 20 charachter")
	private String name;
    @Column(unique=true)
	@Email(message = "Ïnvalid Email Address")
	private String email;
	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Min 6 characters is required")
	private String password;
	private String role;
	private boolean Enabled = true;
	private String imageurl;
	@Column(length=100)
	private String about;
	private String profilePic;
	@Size(min = 10,max = 12,message = "Enter a validation no")
	private String phoneNumber;
	private Providers provider=Providers.SELF;
	private String ProviderUserId;
	
	
	
	public List<String> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfilePic() {
		return profilePic;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public Providers getProvider() {
		return provider;
	}
	public String getProviderUserId() {
		return ProviderUserId;
	}
	
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="user")
	private List<Contact> contacts=new ArrayList<>();


	public User(int id,
			@NotBlank(message = "User name can not be Empty") @Size(min = 2, max = 20, message = "user name must be between 2 to 20 charachter") String name,
			String email, String password, String role, boolean enabled, String imageurl, String about,
			String profilePic, String phoneNumber, List<Contact> contacts, Providers provider, String providerUserId) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		Enabled = enabled;
		this.imageurl = imageurl;
		this.about = about;
		this.profilePic = profilePic;
		this.phoneNumber = phoneNumber;
		this.contacts = contacts;
		this.provider = provider;
		ProviderUserId = providerUserId;
	}

	
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public void setProvider(Providers provider) {
		this.provider = provider;
	}
	public void setProviderUserId(String providerUserId) {
		ProviderUserId = providerUserId;
	}
	
	
	public User() {
		super(); 
	
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
//	public boolean isEnabled() {
//		return Enabled;
//	}
//	public void setEnabled(boolean enabled) {
//		Enabled = enabled;
//	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", Enabled=" + Enabled + ", imageurl=" + imageurl + ", about=" + about + ", contacts=" + contacts
				+ "]";
	}
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String>roleList=new ArrayList<>();
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority>roles=roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		return roles;
	}
	@Override
	public String getUsername() {
	
		return this.email;
	}
	@Override
	public String getPassword() {
		
		return this.password;
	}
	
	@Override
	public boolean isEnabled() {
		return this.Enabled;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	
	

}
