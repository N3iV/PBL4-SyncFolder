package com.SyncFolderPBL4.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "User")
public class UserEntity {
	
	public UserEntity() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private int id;
	
	@Column
	@Expose
	private String username;
	
	@Column(nullable = false)
	@Expose(serialize = false)
	private String password;
	
	@Column
	@Expose
	private String firstname;
	
	@Column
	@Expose
	private String lastname;
	
	@Column
	@Expose
	@Temporal(value = TemporalType.DATE)
	private Date birthday;
	
	@Column
	@Expose
	private String image;
	
	@Column
	@Expose
	private boolean gender;
	
	@Column
	@Expose
	private String phonenumber;
	
	@Column
	@Expose
	private String email;
	
	@Column(name = "created_date")
	@Temporal(value = TemporalType.DATE)
	@Expose
	private Date createdDate;
	
	@Column(name = "modified_date")
	@Temporal(value = TemporalType.DATE)
	@Expose
	private Date modifiedDate;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<UserRoleFileEntity> roles = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<UserRoleFileEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoleFileEntity> roles) {
		this.roles = roles;
	}


	public UserEntity(int id, String username, String password, String firstname, String lastname, Date birthday,
			String image, boolean gender, String phonenumber, String email, Date createdDate, Date modifiedDate,
			List<UserRoleFileEntity> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.image = image;
		this.gender = gender;
		this.phonenumber = phonenumber;
		this.email = email;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.roles = roles;
	}
	
	
	
	
}
