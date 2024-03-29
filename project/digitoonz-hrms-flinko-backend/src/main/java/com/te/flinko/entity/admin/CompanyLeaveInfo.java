package com.te.flinko.entity.admin;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.te.flinko.audit.Audit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fa_company_leave_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "leaveId")
public class CompanyLeaveInfo extends Audit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_leave_id", unique = true, nullable = false, precision = 19)
	private Long leaveId;
	@Column(name = "cli_leave_title", length = 50)
	private String leaveTitle;
	@Column(name = "cli_no_of_leave", precision = 5, scale = 2)
	private BigDecimal noOfLeave;
	@ManyToOne
	@JoinColumn(name = "cli_rule_id")
	private CompanyRuleInfo companyRuleInfo;

}
