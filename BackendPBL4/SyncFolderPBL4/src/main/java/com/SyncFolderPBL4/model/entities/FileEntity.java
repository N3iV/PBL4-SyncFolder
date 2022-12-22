package com.SyncFolderPBL4.model.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "File")
public class FileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private int id;

	@Column(name = "node_id", nullable = false)
	@Expose
	private int nodeId;

	@Column(name = "owner_id", nullable = false)
	@Expose
	private int ownerId;

	@Column
	@Expose
	private String name;

	@Column(nullable = false)
	@Type(type = "text")
	@Expose
	private String path;

	@Column(name = "created_date", columnDefinition = "TIMESTAMP")
	@Expose
	private LocalDateTime createdDate;

	@Column(name = "modified_date", columnDefinition = "TIMESTAMP")
	@Expose
	private LocalDateTime modifiedDate;

	@OneToMany(mappedBy = "file",fetch = FetchType.EAGER ,cascade = CascadeType.REMOVE)
	@Expose
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
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

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public FileEntity(int nodeId, int ownerId, String name, String path, LocalDateTime createdDate,
			LocalDateTime modifiedDate, List<UserRoleFileEntity> roles, TypeEntity type) {
		this.ownerId = ownerId;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name, nodeId, ownerId, path);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileEntity other = (FileEntity) obj;
		return  id == other.id && name.compareTo(other.getName()) == 0
				&& nodeId == other.nodeId && ownerId == other.ownerId && path.compareTo(other.getPath()) == 0;
	}

	public FileEntity() {
		// TODO Auto-generated constructor stub
	}
}
