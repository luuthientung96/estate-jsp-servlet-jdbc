package com.laptrinhjavaweb.entity;

import java.sql.Timestamp;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;

@Entity
public class BaseEntity {
	@Column(name="id")
	private Long id;
	@Column(name="createdby")
	private String createdBy;
	@Column(name="modifiedby")
	private String modifiedBy;
	@Column(name="createddate")
	private Timestamp createdDate;
	@Column(name="modifieddate") //modifiedate //modifieddate
	private Timestamp modifiedDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateBy() {
		return createdBy;
	}
	public void setCreateBy(String createBy) {
		this.createdBy = createBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Timestamp getCreateDate() {
		return createdDate;
	}
	public void setCreateDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
}
