package service;

import model.Expense;

import java.util.List;

public interface ExpenseService {

    Expense getExpenseById(Double ExpenseId);
    List<Expense> getAllExpenses();
    List<Expense> getApprovalWaitingExpenses(Double managerId);
    void approveExpense(Double expenseId);
    void saveExpense(Expense expense);
    void rejectExpense(Double expenseId);
    List<Expense> getAllExpensesByEmployee(Double identityNumber);

}
