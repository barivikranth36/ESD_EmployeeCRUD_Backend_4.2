package com.example.microservice.Controller;

import com.example.microservice.Bean.Department;
import com.example.microservice.Bean.Employee;
import com.example.microservice.Services.DepartmentService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.List;

@Path("department")
public class DepartmentController {

    DepartmentService departmentService = new DepartmentService();

    @GET
    @Path("/fetch")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchData()
    {
        List<String> d = departmentService.getDept();
        if(d == null)
        {
            return Response.noContent().build();
        }
        return Response.ok().entity(d).build();
    }

    @POST
    @Path("/count")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCount(Department department){
        boolean c = departmentService.departmentCount(department);
        if(c)
        {
            return Response.ok().entity("Capacity available").build();
        }
        return Response.status(202).entity("Either Department doesn't exist or Department is FULL").build();
    }

    @POST
    @Path("/name")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchDeptName(Employee employee) throws URISyntaxException {
        String deptName = departmentService.getName(employee);

        if (deptName == null)
            return Response.status(202).build();
        return Response.ok().entity(deptName).build();
    }
}
