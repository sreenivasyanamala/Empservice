package com.mt.emp.service;

import java.util.Collection;

import com.mt.emp.dao.EmployeeDAO;
import com.mt.emp.exception.EmployeeNotFoundException;
import com.mt.emp.model.EmployeeModel;

public class EmployeeService {

	private static EmployeeDAO empDAO = new EmployeeDAO();

	public Collection<EmployeeModel> getEmpDetails() throws EmployeeNotFoundException {
		System.out.println("Enetered into empdetails ");
		return empDAO.getEmpDetails();
	}

	public EmployeeModel getEmpById(String id) throws EmployeeNotFoundException {
		System.out.println("Entered into the getEmpById method");
		return empDAO.getEmpById(id);
	}

	public void delEmpById(String id) throws EmployeeNotFoundException {
		System.out.println("Entered into the delEmpById method");
		empDAO.delEmpById(id);
	}

	public void InsertEmp(EmployeeModel emp) {
		System.out.println("Entered into the delEmpById method");
		empDAO.InsertEmp(emp);
	}

}