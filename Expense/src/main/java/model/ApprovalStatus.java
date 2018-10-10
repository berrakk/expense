package model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Approval_Status", schema = "TTDE_BILLING")
public class ApprovalStatus {

    @Id
    @Column(name = "APPROVAL_ID")
    private Double approvalId;

    @Column(name = "APPROVAL_TYPE")
    private String approvalType;

    public Double getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Double approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalStatus() {
        return approvalType;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalType = approvalStatus;
    }
}
