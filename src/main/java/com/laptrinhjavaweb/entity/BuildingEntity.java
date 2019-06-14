package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;
import com.laptrinhjavaweb.annotation.Table;

@Entity
@Table(name="building")
public class BuildingEntity extends BaseEntity{
	@Column(name = "name")
	private String name;
	@Column(name = "ward")
	private String ward;
	@Column(name = "street")
	private String street;
	@Column(name = "structure")
	private String structure;
	@Column(name = "numberofbasement")
	private Integer numberOfBasement;
	@Column(name = "buildingarea")
	private Integer buildingArea;
	@Column(name = "district")
	private String district;
	@Column(name = "costrent")
	private String costRent;
	@Column(name = "costdescription")
	private String costDescription;
	@Column(name = "servicecost")
	private String serviceCost;
	@Column(name = "carcost")
	private String carCost;
	@Column(name = "motorbikecost")
	private String motorbikeCost;
	@Column(name = "overtimecost")
	private String overtimeCost;
	@Column(name = "electricitycost")
	private String electricityCost;
	@Column(name = "deposit")
	private String deposit;
	@Column(name = "payment")
	private String payment;
	@Column(name = "timecontract")
	private String timeContract;
	@Column(name = "timedecorator")
	private String timeDecorator;
	@Column(name = "managername")
	private String managerName;
	@Column(name = "managerphone")
	private String managerPhone;
	@Column(name = "type")
	private String type;
	
	//private String[] buildingTypes = new String[] {};

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public Integer getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(Integer buildingArea) {
		this.buildingArea = buildingArea;
	}

}
