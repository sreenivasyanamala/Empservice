package com.mt.emp.exception;

public class EmployeeNotFoundException extends Exception {

	private static final long serialVersionUID = 7335440789151725992L;

	public EmployeeNotFoundException(String msg) {
		super(msg);
	}
}
