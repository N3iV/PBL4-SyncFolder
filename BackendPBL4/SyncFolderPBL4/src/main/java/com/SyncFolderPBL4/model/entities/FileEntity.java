package com.SyncFolderPBL4.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "File")
public class FileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private int id;
	@Column(name = "node_id",nullable = false)
	@Expose
	private int nodeId;
	
	@Column
	@Expose
	private String name;
	
	@Column(nullable = false)
	@Type(type = "text")
	@Expose
	private String path;
	
	@Column(name = "created_date")
	@Temporal(value = TemporalType.DATE)
	@Expose
	private Date createdDate;
	
	@Column(name = "modified_date")
	@Temporal(value = TemporalType.DATE)
	@Expose
	private Date modifiedDate;
	
	@OneToMany(mappedBy = "file",cascade = CascadeType.REMOVE)
	private List<UserRoleFileEntity> roles = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	@Expose
	private TypeEntity type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public TypeEntity getType() {
		return type;
	}

	public void setType(TypeEntity type) {
		this.type = type;
	}

	public FileEntity(int nodeId, String name, String path, Date createdDate, Date modifiedDate,
			List<UserRoleFileEntity> roles, TypeEntity type) {
		this.nodeId = nodeId;
		this.name = name;
		this.path = path;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.roles = roles;
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "\nFileEntity [id=" + id + ", nodeId=" + nodeId + ", name=" + name + ", path=" + path + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", type=" + type + "]";
	}

	public FileEntity() {
		// TODO Auto-generated constructor stub
	}
}
