
import dao.ExpenseRepo;
import model.ApprovalStatus;
import model.Employee;
import model.Expense;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:business-config.xml")

public class ExpenseRepoTest {
    @Autowired
    private ExpenseRepo expenseRepo;


    @Test
    @Transactional
    public void canSaveExpense() {
        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.setApprovalId(1d);

        Employee employee = new Employee();
        employee.setIdentityNumber(11436014516d);

        Expense expense = new Expense();
        expense.setExpenseId(4d);
        expense.setExpStatement("Mesai akşam yemeği");
        expense.setEmployee(employee);
        expense.setApprovalStatus(approvalStatus);


        expenseRepo.saveExpense(expense);

    }


    @Test
    @Transactional
    public void canFindAllExpenses() {


        List<Expense> expenseList = expenseRepo.getAllExpenses();
        int expectedNumber = 0;
        assertEquals("Basarili olmadi", expectedNumber, expenseList.size());


    }

    @Test
    @Transactional

    public void canFindApprovalWaitingExpensesForManager() {

        List<Expense> expenseList = expenseRepo.getApprovalWaitingExpenses(27139807478d);
        int expectedNumber = 1;

        assertEquals("Basarisiz", expectedNumber, expenseList.size());

    }



    @Test
    @Transactional

    public void canFindApprovalWaitingExpensesForUnithead() {

        List<Expense> expenseList = expenseRepo.getApprovalWaitingExpenses(12345678913d);
        int expectedNumber = 3;

        assertEquals("Basarisiz", expectedNumber, expenseList.size());

    }



    @Test
    @Transactional
    @Rollback(false)
    public void approveExpense(){
        Double expenseId = 4d;
        expenseRepo.approveExpense(expenseId);


        //List<Expense> expenseList = expenseRepo.getApprovalWaitingExpenses(27139807478d,2);

        //assertEquals("Basarisiz",0,expenseList.size());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void RejectExpense(){

        Double expenseId=5d;
        expenseRepo.rejectExpense(expenseId);

    }

}
