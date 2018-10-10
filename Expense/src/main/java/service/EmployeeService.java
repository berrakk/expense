package service;
import model.Department;
import model.Employee;
import java.util.List;

public interface EmployeeService {

    Employee getEmployeeByIdNumber(Double identityNumber);
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    boolean authenticateUser(Double identityNumber, String password);
    List<Department> getAllDepartments();
}
