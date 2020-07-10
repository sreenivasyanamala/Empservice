package com.mt.emp.controller;

import java.util.Collection;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mt.emp.exception.EmployeeNotFoundException;
import com.mt.emp.model.EmployeeModel;
import com.mt.emp.service.EmployeeService;

@Path("/employee")
public class EmployeeController {

	private static EmployeeService empService = new EmployeeService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEmployee() {
		Collection<EmployeeModel> emp = null;
		try {
			emp = empService.getEmpDetails();
		} catch (EmployeeNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(emp).build();
	}

	@GET
	@Path("/getById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeById(@PathParam("id") String id) {

		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>" + id);
		EmployeeModel employee;

		try {
			employee = empService.getEmpById(id);
			if (id == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(id).build();
			}
		} catch (EmployeeNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e).build();
		}
		return Response.status(Response.Status.OK).entity(employee).build();
	}

	@DELETE
	@Path("/deleteById/{id}")
	public Response deleteEmpById(@PathParam("id") String id) {
		try {
			if (id == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(id).build();
			}
			empService.delEmpById(id);
		} catch (EmployeeNotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).entity(e).build();
		}
		return Response.status(Status.OK).entity("successfully Deleted !!").build();
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response InsertEmp(EmployeeModel emp) {
		try {
			empService.InsertEmp(emp);
			if (emp == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity(emp).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
		}
		return Response.status(Status.CREATED).entity("successfully Inserted !!").build();
	}

}
