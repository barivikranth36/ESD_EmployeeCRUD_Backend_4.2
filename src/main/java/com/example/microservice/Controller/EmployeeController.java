package com.example.microservice.Controller;

import com.example.microservice.Bean.Employee;
import com.example.microservice.DAO.Admin;
import com.example.microservice.DAO.EmpDep;
import com.example.microservice.Services.EmployeeService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;

@Path("employee")
public class  EmployeeController {
    EmployeeService employeeService = new EmployeeService();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginEmployee(Admin admin) throws URISyntaxException {
        String e = employeeService.verifyEmail(admin);
        if(e == null)
        {
            return Response.noContent().build();
        }
        return Response.ok().entity(e).build();
    }

    @GET
    @Path("/fetch")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchData(){
        List<EmpDep> employeeList = employeeService.getEmployees();
        if(employeeList == null)
        {
            return Response.noContent().entity("No data of Employee!!").build();
        }
        return Response.ok().entity(employeeList).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerEmployee(EmpDep empDep)
    {
        EmpDep employee = employeeService.registerEmployee(empDep);
        if(employee == null)
        {
            return Response.status(202).entity("Either Capacity full for department or something went wrong").build();
        }
        return Response.ok().entity(employee).build();
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(EmpDep empDep){
        boolean updated = employeeService.employeeUpdate(empDep);
        if(updated){
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
//    @POST
//    @Path("/all")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response allEmployee(Employee employee) throws URISyntaxException{
//        Employee e = employeeService.verifyAll(employee);
//        System.out.println(e.getEmployee_id());
//        if(e == null)
//        {
//            return Response.noContent().build();
//        }
//        return Response.ok().entity(e).build();
//    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response employeeDelete(Employee employee){
        boolean check = employeeService.employeeDelete(employee);
        if(check){
            return Response.ok().entity("Delete successfully").build();
        }
        return Response.noContent().build();
    }
}
