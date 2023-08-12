package best.project.guice.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import com.google.inject.Inject;

import best.project.guice.model.Employee;
import best.project.guice.service.IEmployeeService;

@Path("/employees")
public class EmployeeController {

    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
    private IEmployeeService nameService;

    @Inject
    public EmployeeController(IEmployeeService nameService) {
        LOGGER.log(Level.INFO, "EmployeeController initialized!");
        this.nameService = nameService;
    }

    @GET
    @Path("/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("id") Long id) {
        LOGGER.log(Level.INFO, "getEmployeeById {0}", id);
        Response.Status status = Response.Status.OK;
        Employee employee = nameService.getEmployeeById(id);
        if(null == employee){
            status = Response.Status.BAD_REQUEST;
        }
        return Response.status(status).entity(employee).build();
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesByName(@PathParam("name") String name) {
        LOGGER.log(Level.INFO, "getEmployeesByName {0}", name);
        List<Employee> employees = nameService.getEmployeesByName(name);
        return Response.status(Response.Status.OK).entity(employees).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployee() {
        List<Employee> employees = nameService.getAllEmployee();
        return Response.status(Response.Status.OK).entity(employees).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveEmployee(JSONObject json) {
        Response.Status status = Response.Status.OK;
		Boolean isEmployeeSaved = nameService.saveEmployee(json);
		if (!Boolean.TRUE.equals(isEmployeeSaved)) {
			status = Response.Status.BAD_REQUEST;
		}
        return Response.status(status).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(JSONObject json) {
    	Response.Status status = Response.Status.OK;
        Boolean isEmployeeUpdated = nameService.updateEmployee(json);
        if(!Boolean.TRUE.equals(isEmployeeUpdated)){
            status = Response.Status.BAD_REQUEST;
        }
        return Response.status(status).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployeeById(@PathParam("id") Long id) {
    	LOGGER.log(Level.INFO, "deleteEmployeeById {0}", id);
        Response.Status status = Response.Status.OK;
        Boolean isEmployeeDeleted = nameService.deleteEmployeeById(id);
        if(!Boolean.TRUE.equals(isEmployeeDeleted)){
            status = Response.Status.BAD_REQUEST;
        }
        return Response.status(status).build();
    }
}
