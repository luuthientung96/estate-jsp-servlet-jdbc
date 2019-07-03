package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;
@Entity
@Table(name="assignmentbuilding")
public class AssignmentBuilding extends BaseEntity{
@Column(name="buildingid")
private String buildingId;
@Column(name="staffid")
private String staffId;
public String getBuildingId() {
	return buildingId;
}
public void setBuildingId(String buildingId) {
	this.buildingId = buildingId;
}
public String getStaffId() {
	return staffId;
}
public void setStaffId(String staffId) {
	this.staffId = staffId;
}

}
