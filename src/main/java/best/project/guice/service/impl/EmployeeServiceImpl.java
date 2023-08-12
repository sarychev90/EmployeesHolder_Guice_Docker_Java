package best.project.guice.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.inject.Inject;

import best.project.guice.model.Employee;
import best.project.guice.repository.IEmployeeRepository;
import best.project.guice.service.IEmployeeService;

public class EmployeeServiceImpl implements IEmployeeService {
	
	private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class.getName());

    private IEmployeeRepository employeeRepository;

    @Inject
    public EmployeeServiceImpl(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.getAllEmployee();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.getEmployeesByName(name);
    }

    @Override
	public Boolean saveEmployee(JSONObject json) {
    	Boolean isEmployeeSaved = Boolean.FALSE;
    	Employee employee = new Employee();
		try {
			employee.setName(json.getString("name"));
			isEmployeeSaved = employeeRepository.saveEmployee(employee);
		} catch (JSONException e) {
			LOGGER.log(Level.SEVERE, "saveEmployee exception", e);
		}
		return isEmployeeSaved;
	}

    @Override
    public Boolean updateEmployee(JSONObject json) {
    	Boolean isEmployeeUpdated = Boolean.FALSE;
    	Employee employee = new Employee();
		try {
			employee.setName(json.getString("name"));
			employee.setId(json.getLong("id"));
			isEmployeeUpdated = employeeRepository.updateEmployee(employee);
		} catch (JSONException e) {
			LOGGER.log(Level.SEVERE, "updateEmployee exception", e);
		}
        return isEmployeeUpdated;
    }

    @Override
    public Boolean deleteEmployeeById(Long id) {
        return employeeRepository.deleteEmployeeById(id);
    }
}
