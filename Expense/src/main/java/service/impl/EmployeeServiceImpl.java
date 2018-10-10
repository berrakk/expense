package service.impl;

import dao.EmployeeRepo;
import model.Department;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.EmployeeService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public Employee getEmployeeByIdNumber(Double identityNumber) {
        return employeeRepo.getEmployeeByIdNumber(identityNumber);
    }

    public List<Employee> getAllEmployees() {

        return employeeRepo.getAllEmployees();
    }

    public void saveEmployee(Employee employee) {

        employeeRepo.saveEmployee(employee);
    }

    public boolean authenticateUser(Double identityNumber, String password) {
        return employeeRepo.authenticateUser(identityNumber, password);
    }

    public List<Department> getAllDepartments() {
        return employeeRepo.getAllDepartments();
    }
}
