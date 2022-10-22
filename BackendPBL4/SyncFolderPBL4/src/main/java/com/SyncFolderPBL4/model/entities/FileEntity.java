package com.SyncFolderPBL4.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
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

@Entity
@Table(name = "File")
public class FileEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "node_id",nullable = false)
	private int nodeId;
	
	@Column
	private String name;
	
	@Column
	@Type(type = "text")
	private String path;
	
	@Column(name = "created_date")
	@Temporal(value = TemporalType.DATE)
	private Date createdDate;
	
	@Column(name = "modified_date")
	@Temporal(value = TemporalType.DATE)
	private Date modifiedDate;
	
	@OneToMany(mappedBy = "file", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	private List<UserRoleFileEntity> roles = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "fk_file_type"))
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
	
	public FileEntity() {
		// TODO Auto-generated constructor stub
	}
}
