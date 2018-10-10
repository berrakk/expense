package model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "Employee", schema = "TTDE_BILLING")
 public class Employee {

    @Id
    @Column(name = "IDENTITY_NUMBER")
    private Double identityNumber;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "EMPLOYEE_SURNAME")
    private String employeeSurname;

    @Column(name = "EMPLOYEE_EMAIL")
    private String employeeEmail;

    @Column(name = "EMP_PASSWORD")
    private String employeePassword;

    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "identityNumber")
    @JsonIdentityReference(alwaysAsId = true)
    private Employee manager;

    @ManyToOne
    @JoinColumn(name = "DEP_ID")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_TYPE_ID")
    private EmployeeType employeeType;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Double getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(Double identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }
}
