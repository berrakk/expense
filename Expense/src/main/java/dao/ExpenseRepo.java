package dao;

import model.Expense;

import java.util.List;

public interface ExpenseRepo {

    Expense getExpenseById(Double expenseId);
    void saveExpense(Expense expense);
    List<Expense> getAllExpenses();
    List<Expense> getAllExpensesByEmployee(Double identityNumber);
    List<Expense> getApprovalWaitingExpenses(Double manager);
    void approveExpense(Double expenseId);
    void rejectExpense(Double expenseId);
}
