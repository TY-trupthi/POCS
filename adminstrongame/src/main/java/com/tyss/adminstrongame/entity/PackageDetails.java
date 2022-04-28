package com.tyss.adminstrongame.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Entity
@Table(name = "package_details")
@Data
public class PackageDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "package_id")
	private int packageId;
	
	@Column(name = "package_name")
	private String packageName;
	
	@Column(name = "package_duration")
	private double packageDuration;
	
	@Column(name = "actual_price")
	private double actualPrice;
	
	@Column(name = "offer_percentage")
	private double offerPercentage;
	
	@Column(name="package_type")
	private String packageType;
	
	@Column(name="package_icon")
	private String packageIcon;
	
	@Exclude
    @JsonBackReference
	@ManyToMany(cascade = CascadeType.PERSIST,mappedBy = "userPackages")
	private List<UserInformation> packageUsers;

	public PackageDetails() {
		super();
	}

	public PackageDetails(int packageId, String packageName, double packageDuration, double actualPrice,
			double offerPercentage, String packageType, String packageIcon, List<UserInformation> packageUsers) {
		super();
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageDuration = packageDuration;
		this.actualPrice = actualPrice;
		this.offerPercentage = offerPercentage;
		this.packageType = packageType;
		this.packageIcon = packageIcon;
		this.packageUsers = packageUsers;
	}	
	
	@PreRemove
	public void makePackageNull() {
		
		for (UserInformation userInformation : packageUsers) {
			userInformation.getUserPackages().remove(this);
		}
	}

}
