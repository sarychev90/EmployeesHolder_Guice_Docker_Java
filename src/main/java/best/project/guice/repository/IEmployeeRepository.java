package best.project.guice.repository;

import best.project.guice.model.Employee;
import best.project.guice.repository.impl.EmployeeRepositoryImpl;
import com.google.inject.ImplementedBy;

import java.util.List;

@ImplementedBy(EmployeeRepositoryImpl.class)
public interface IEmployeeRepository {

    public List<Employee> getAllEmployee();
    public Employee getEmployeeById(Long id);
    public List<Employee> getEmployeesByName(String name);
    public Boolean saveEmployee(Employee employee);
    public Boolean updateEmployee(Employee employee);
    public Boolean deleteEmployeeById(Long id);



}
