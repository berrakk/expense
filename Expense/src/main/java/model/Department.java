package model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.persistence.OneToOne;

@Entity
@Table(name = "Department", schema = "TTDE_BILLING")
public class Department {

    @Id
    @Column(name = "DEP_ID")
    private Double depId;

    @Column(name = "DEP_NAME")
    private String depName;

    @Column(name = "REPORTING_DEPARTMENT")
    private Double reportingDepartment;

    @Column (name= "MNG_IDENTITY_NUMBER")
    private Double managerId;

    public Double getDepId() {
        return depId;
    }

    public void setDepId(Double depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Double getManagerId() {
        return managerId;
    }

    public void setManagerId(Double managerId) {
        this.managerId = managerId;
    }

    public Double getReportingDepartment() {
        return reportingDepartment;
    }

    public void setReportingDepartment(Double reportingDepartment) {
        this.reportingDepartment = reportingDepartment;
    }
}
