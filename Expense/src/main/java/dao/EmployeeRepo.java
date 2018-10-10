package dao;
import model.Department;
import model.Employee;
import java.util.List;

public interface EmployeeRepo {

    Employee getEmployeeByIdNumber(Double identityNumber);
    List<Employee>  getAllEmployees();
    boolean authenticateUser(Double identityNumber, String password);
    void saveEmployee(Employee employee);
    List<Department> getAllDepartments();

}
