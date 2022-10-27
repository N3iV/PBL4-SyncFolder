package com.SyncFolderPBL4.model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "Type")
public class TypeEntity {
	
	@Id
	@Expose
	private int id;
	
	@Column
	@Expose
	private String name;
	
	@OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
	private List<FileEntity> files;

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

	public List<FileEntity> getFiles() {
		return files;
	}

	public void setFiles(List<FileEntity> files) {
		this.files = files;
	}

	public TypeEntity(int id, String name, List<FileEntity> files) {
		this.id = id;
		this.name = name;
		this.files = files;
	}
	
	public TypeEntity() {
	}
}
