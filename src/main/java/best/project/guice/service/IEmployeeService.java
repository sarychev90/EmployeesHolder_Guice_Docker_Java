package best.project.guice.service;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;

import com.google.inject.ImplementedBy;

import best.project.guice.model.Employee;
import best.project.guice.service.impl.EmployeeServiceImpl;

@ImplementedBy(EmployeeServiceImpl.class)
public interface IEmployeeService {
	
    public List<Employee> getAllEmployee();
    public Employee getEmployeeById(Long id);
    public List<Employee> getEmployeesByName(String name);
    public Boolean saveEmployee(JSONObject json);
    public Boolean updateEmployee(JSONObject json);
    public Boolean deleteEmployeeById(Long id);

}
