package com.smart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTACT")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cid;
	private String name;
	private String nickname;
	private String email;
	private String work;
	private String Phone;
	private String address;
	private String picture;
	@Column(length = 1000)
	private String description;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getLinkedInLink() {
		return LinkedInLink;
	}

	public void setLinkedInLink(String linkedInLink) {
		LinkedInLink = linkedInLink;
	}

	private String LinkedInLink;

	@ManyToOne
	@JsonIgnore
	private User user;

	public Contact(int cid, String name, String nickname, String email, String work, String phone, String address,
			String picture, String description, String linkedInLink, User user) {
		this.cid = cid;
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.work = work;
		Phone = phone;
		this.address = address;
		this.picture = picture;
		this.description = description;
		LinkedInLink = linkedInLink;
		this.user = user;
	}

	public Contact() {
		super();
	
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getpicture() {
		return picture;
	}

	public void setpicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
