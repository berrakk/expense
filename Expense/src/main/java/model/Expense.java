package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javafx.beans.binding.IntegerExpression;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "Expense", schema = "TTDE_BILLING")
public class Expense {

    @Id
    @Column(name = "EXP_ID")
    private Double expenseId;

    @Column(name = "DATE_CREATE_EXP")
    private Date dateCreateExp;

    @Column(name = "EXP_STATEMENT")
    private String expStatement;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "APPROVAL_ID")
    private ApprovalStatus approvalStatus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "expense_id")
    private Set<SubExpense> expenseSet;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public ApprovalStatus getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Set<SubExpense> getExpenseSet() {
        return expenseSet;
    }

    public void setExpenseSet(Set<SubExpense> expenseSet) {
        this.expenseSet = expenseSet;
    }

    public Date getDateCreateExp() {
        return dateCreateExp;
    }

    public void setDateCreateExp(Date dateCreateExp) {
        this.dateCreateExp = dateCreateExp;
    }

    public String getExpStatement() {
        return expStatement;
    }

    public void setExpStatement(String expStatement) {
        this.expStatement = expStatement;
    }

    public Double getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Double expenseId) {
        this.expenseId = expenseId;
    }
}
