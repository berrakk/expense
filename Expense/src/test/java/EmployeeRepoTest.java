import dao.EmployeeRepo;
import dao.ExpenseRepo;
import model.Department;
import model.Employee;
import model.Expense;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:business-config.xml")
public class EmployeeRepoTest {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Test
    @Transactional
    public void canFindEmployeeSuccessfully() {

        Double identityNumber = 11436014516d;

        Employee resultEmplopyee = employeeRepo.getEmployeeByIdNumber(identityNumber);

        assertEquals("Basarili olmadi", identityNumber, resultEmplopyee.getIdentityNumber());

    }

    @Test
    @Transactional
    public void canFindNoOfEmployees() {

        int expectedNumber = 2;

        List<Employee> employeeList = employeeRepo.getAllEmployees();


        assertEquals("Basarili olmadi", expectedNumber, employeeList.size());


    }

    @Test
    @Transactional
    public void canAuthenticateEmployee() {

        boolean exptectedResult = true;

        boolean result = employeeRepo.authenticateUser(27139807478.0, "123456");

        assertEquals("Basarili olmadi", exptectedResult, result);
    }


    @Test
    @Transactional
    public void canSaveEmployee() {

        Department department = new Department();
        department.setDepId(1d);

        Employee manager = new Employee();
        manager.setIdentityNumber(27139807478d);

        Employee employee = new Employee();
        employee.setEmployeeName("Osman");
        employee.setEmployeeSurname("Kiraz");
        employee.setDepartment(department);
        employee.setEmployeeEmail("osman.kiraz@turkcell.com.tr");
        employee.setEmployeePassword("123456");
        employee.setIdentityNumber(12345678912d);
        employee.setManager(manager);

        employeeRepo.saveEmployee(employee);


    }

    @Test
    @Transactional
    public void  canFindAllDepartment() {


        List<Department> departmentList = employeeRepo.getAllDepartments();
        int expectedNumber = 1;
        assertEquals("Basarili olmadi", expectedNumber, departmentList.size());

    }






    }