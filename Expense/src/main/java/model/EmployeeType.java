package model;
import javax.persistence.*;

@Entity
    @Table(name = "Employee_Type", schema = "TTDE_BILLING")
    public class EmployeeType {
        @Id
        @Column(name = "EMPLOYEE_TYPE_ID")
        private Double employeeTypeId;

        @Column(name = "E_TYPE")
        private String employeeType;

        public Double getEmployeeId() {
            return employeeTypeId;
        }

        public void setEmployeeId(Double employeeId) {
            this.employeeTypeId = employeeId;
        }

        public String getEmployeeType() {
            return employeeType;
        }

        public void setEmployeeType(String employeeType) {
            this.employeeType = employeeType;
        }

}
