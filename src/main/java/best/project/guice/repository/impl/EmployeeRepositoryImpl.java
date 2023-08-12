package best.project.guice.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.google.inject.Inject;

import best.project.guice.model.Employee;
import best.project.guice.repository.IEmployeeRepository;


public class EmployeeRepositoryImpl implements IEmployeeRepository {

    private static final Logger LOGGER = Logger.getLogger(EmployeeRepositoryImpl.class.getName());

    private EntityManager entityManager;

    @Inject
    public EmployeeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteria.from(Employee.class);
            criteria.select(root);
            employees = entityManager.createQuery(criteria).getResultList();
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "getAllEmployee exception", e);
        }
        return employees;
    }

    @Override
    public Boolean saveEmployee(Employee employee) {
        Boolean isEmployeeSaved = Boolean.FALSE;
        try {
        	entityManager.getTransaction().begin();
            entityManager.persist(employee);
            entityManager.getTransaction().commit();
            isEmployeeSaved = Boolean.TRUE;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "saveEmployee exception", e);
        }
        return isEmployeeSaved;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Employee employee = null;
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteria.from(Employee.class);
            criteria.select(root);
            criteria.where(criteriaBuilder.equal(root.get("id"), id));
            employee = entityManager.createQuery(criteria).getSingleResult();
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "getEmployeeById exception", e);
        }
        return employee;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getEmployeesByName(String name) {
        List<Employee> employees = new ArrayList<>();
        try {
            String query = "FROM Employee WHERE name=:name";
            employees = entityManager.createQuery(query).setParameter("name", name).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "getEmployeeByName exception", e);
        }
        return employees;
    }

    @Override
    public Boolean updateEmployee(Employee employeeForUpdate) {
        Boolean isEmployeeUpdated = Boolean.FALSE;
        try {
            Employee employee = getEmployeeById(employeeForUpdate.getId());
            if(null != employee){
                employee.setName(employeeForUpdate.getName());
                entityManager.getTransaction().begin();
                entityManager.merge(employee);
                entityManager.getTransaction().commit();
                isEmployeeUpdated = Boolean.TRUE;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "updateEmployee exception", e);
        }
        return isEmployeeUpdated;
    }

    @Override
    public Boolean deleteEmployeeById(Long id) {
        Boolean isEmployeeDelete = Boolean.FALSE;
        try {
            Employee employee = getEmployeeById(id);
            if(null != employee){
            	entityManager.getTransaction().begin();
                entityManager.remove(employee);
                isEmployeeDelete = Boolean.TRUE;
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "deleteEmployeeById exception", e);
        }
        return isEmployeeDelete;
    }
}
