package com.mt.emp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.mt.emp.exception.EmployeeNotFoundException;
import com.mt.emp.model.EmployeeModel;

public class EmployeeDAO {

	private static Map<String, EmployeeModel> empDetailsMap;

	public EmployeeDAO() {
		// TODO Auto-generated constructor stub
		empDetailsMap = new HashMap<String, EmployeeModel>();
		EmployeeModel emp1 = new EmployeeModel();
		emp1.setEmpId("11111");
		emp1.setEmpDesignation("Manager");
		emp1.setEmpExperience("5");
		emp1.setEmpName("Sreenivas");
		emp1.setEmpSalary("70,000");
		EmployeeModel emp2 = new EmployeeModel();
		emp2.setEmpId("22222");
		emp2.setEmpDesignation("Asst.Manager");
		emp2.setEmpExperience("4");
		emp2.setEmpName("Dileep kumar");
		emp2.setEmpSalary("40,000");

		empDetailsMap.put(emp1.getEmpId(), emp1);
		empDetailsMap.put(emp2.getEmpId(), emp2);

	}

	public Collection<EmployeeModel> getEmpDetails() throws EmployeeNotFoundException {
		System.out.println(">>>>>>>>>>>>>>>" + empDetailsMap);
		Collection<EmployeeModel> employees = empDetailsMap.values();
		if (employees.isEmpty()) {
			throw new EmployeeNotFoundException("Employees does not exist");
		}
		return employees;
	}

	public EmployeeModel getEmpById(String Id) throws EmployeeNotFoundException {
		EmployeeModel employee = empDetailsMap.get(Id);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employees does not exist");
		}
		return employee;
	}

	public void delEmpById(String Id) throws EmployeeNotFoundException {
		EmployeeModel employee = empDetailsMap.remove(Id);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employees does not exist");
		}
		System.out.println(">>>>>>>>>>>>>>>" + empDetailsMap);
	}

	public void InsertEmp(EmployeeModel emp) {
		empDetailsMap.put(emp.getEmpId(), emp);
		System.out.println(">>>>>>>>>>>>>>>" + empDetailsMap);
	}

}
