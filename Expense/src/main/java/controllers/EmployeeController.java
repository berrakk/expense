package controllers;
import model.Department;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.EmployeeService;

import java.util.List;


@Controller
@RequestMapping("rest/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/{identityNumber}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployee(@PathVariable Double identityNumber) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeByIdNumber(identityNumber), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<List<Employee>>(employeeService.getAllEmployees(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveEmployee(@RequestBody Employee employee) {

        employeeService.saveEmployee(employee);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public ResponseEntity<List<Department>> getAllDepartments() {

        return new ResponseEntity<List<Department>>(employeeService.getAllDepartments(), HttpStatus.OK);
    }

    @RequestMapping(value="/{identityNumber}/{password}",method=RequestMethod.GET)
    public @ResponseBody ResponseEntity<Boolean> authenticateUser(@PathVariable Double identityNumber, @PathVariable String password){
        return new ResponseEntity<Boolean>(employeeService.authenticateUser(identityNumber, password),HttpStatus.OK);
    }

}

