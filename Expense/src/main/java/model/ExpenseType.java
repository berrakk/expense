package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Expense_Type", schema = "TTDE_BILLING")


   public class ExpenseType {

    @Id
    @Column(name = "EXP_TYPE_ID")
    private Double expenseTypeId;

    @Column(name = "EXP_TYPE_NAME")
    private String expenseTypeName;

    public Double getExpenseTypeId() {
        return expenseTypeId;
    }

    public void setExpenseTypeId(Double expenseTypeId) {
        this.expenseTypeId = expenseTypeId;
    }

    public String getExpenseTypeName() {
        return expenseTypeName;
    }

    public void setExpenseTypeName(String expenseTypeName) {
        this.expenseTypeName = expenseTypeName;
    }
}
