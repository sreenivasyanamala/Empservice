package com.mt.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.mt.emp.controller.EmployeeController;
import com.mt.emp.exception.EmployeeNotFoundException;

public class EmployeeControllerTest extends JerseyTest {
	@Override
	protected Application configure() {
		return new ResourceConfig(EmployeeController.class);
	}

	@Test
	public void getAllEmployee() {
		Response output = target("/employee").request().get();
		assertEquals("status 200", 200, output.getStatus());
		assertNotNull("return list", output.getEntity());
	}

	@Test(expected = EmployeeNotFoundException.class)
	public void getAllEmployee_withException() throws EmployeeNotFoundException {
		Response output = target("/employee").request().get();
		throw new EmployeeNotFoundException("Employee does not exit");
	}

	@Test
	public void getEmployeeById() {
		Response output = target("/employee/getById").path("22222").request().get();
		assertEquals("status 200", 200, output.getStatus());
		assertNotNull("return employee object", output.getEntity());
	}

	@Test(expected = Exception.class)
	public void getEmployeeById_badData() throws Exception {
		Response output = target("/employee/getById").path("2222").request().get();
		throw new Exception();	
	}

	@Test(expected = EmployeeNotFoundException.class)
	public void getEmployeeById_withException() throws EmployeeNotFoundException {
		Response output = target("/employee/getById").path("22222").request().get();
		throw new EmployeeNotFoundException("Employee does not exit");
	}

	@Test(expected = Exception.class)
	public void deleteEmpById_badRequest() {
		Response output = target("/employee/deleteById/").path(null).request().delete();
		assertEquals("status 200", 400, output.getStatus());

	}
	
	@Test
	public void deleteEmpById() {
		Response output = target("/employee/deleteById/").path("11111").request().delete();
		assertEquals("status 200", 200, output.getStatus());

	}

	@Test(expected = Exception.class)
	public void deleteEmpById_employeeNotFound() throws Exception {
		Response output = target("/employee/deleteById/").path("1111").request().delete();
	  throw new Exception();
	}
	@Test(expected = Exception.class)
	public void deleteEmpById_badData() throws Exception {
		Response output = target("/employee/deleteById/").path("null").request().delete();
	  throw new Exception();
	}

	@Test
	public void InsertEmp() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("empName", "sreenivas");
		data.put("empId", "1111");
		data.put("empDesignation", "Manager");
		data.put("empExperience", "5");
		data.put("empSalary", "70,000");
		Response output = target("/employee/insert").request().post(Entity.json(data), Response.class);
		assertEquals("status 201", 201, output.getStatus());
		assertNotNull("return list", output.getEntity());
	}
	
	@Test(expected = Exception.class)
	public void InsertEmp_badData() throws Exception {
		Response output = target("/employee/insert").request().post(Entity.json(null), Response.class);
		throw new Exception();
	}
}
