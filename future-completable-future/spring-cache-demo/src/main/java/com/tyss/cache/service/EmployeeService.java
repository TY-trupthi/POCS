package com.tyss.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.cache.dto.EmployeeDTO;
import com.tyss.cache.util.CacheStore;

@Service
public class EmployeeService {

	@Autowired
	private CacheStore<EmployeeDTO> employeeCacheStore;

	public Boolean addEmployee(EmployeeDTO employeeDTO) {
		employeeCacheStore.addData(employeeDTO.getEmployeeId().toString(), employeeDTO);
		return true;
	}

	public EmployeeDTO getEmployee(Integer employeeId) {
		return employeeCacheStore.getData(employeeId.toString());
	}

}
