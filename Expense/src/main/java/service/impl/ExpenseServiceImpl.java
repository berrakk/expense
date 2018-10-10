package service.impl;

import dao.ExpenseRepo;
import model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ExpenseService;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseRepo expenseRepo;

    public Expense getExpenseById(Double ExpenseId) {
        return expenseRepo.getExpenseById(ExpenseId);
    }

    public List<Expense> getAllExpenses() {

        return expenseRepo.getAllExpenses();
    }

    public List<Expense> getApprovalWaitingExpenses(Double managerId) {
        return expenseRepo.getApprovalWaitingExpenses(managerId);
    }

    public void approveExpense(Double expenseId) {

        expenseRepo.approveExpense(expenseId);
    }

    public void saveExpense(Expense expense) {
        expenseRepo.saveExpense(expense);
    }

    public void rejectExpense(Double expenseId) {
        expenseRepo.rejectExpense(expenseId);
    }

    public List<Expense> getAllExpensesByEmployee(Double identityNumber) {
        return expenseRepo.getAllExpensesByEmployee(identityNumber);
    }
}
