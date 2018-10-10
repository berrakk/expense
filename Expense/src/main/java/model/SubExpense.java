package model;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "SUB_EXPENSE", schema = "TTDE_BILLING")

    public class SubExpense {

    @Id
    @Column(name = "EXP_SUB_ID")
    private Double expenseSubId;

    @Column(name = "EXP_AMOUNT")
    private Double expenseAmount;

    @Column(name = "SUB_EXP_DATE")
    private Date SubExpenseDate;

    @ManyToOne
    @JoinColumn(name = "EXP_TYPE_ID")
    private ExpenseType expTypeId;


    public Double getExpenseSubId() {
        return expenseSubId;
    }

    public void setExpenseSubId(Double expenseSubId) {
        this.expenseSubId = expenseSubId;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Date getSubExpenseDate() {
        return SubExpenseDate;
    }

    public void setSubExpenseDate(Date subExpenseDate) {
        SubExpenseDate = subExpenseDate;
    }

    public ExpenseType getExpTypeId() {
        return expTypeId;
    }

    public void setExpTypeId(ExpenseType expTypeId) {
        this.expTypeId = expTypeId;
    }

}
