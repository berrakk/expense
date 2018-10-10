package controllers;
import model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ExpenseService;

import java.util.List;

@Controller
@RequestMapping("rest/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


    @RequestMapping(value = "/{expenseId}", method = RequestMethod.GET)
    public ResponseEntity<Expense> getExpense(@PathVariable Double expenseId){

        return new ResponseEntity<Expense>(expenseService.getExpenseById(expenseId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Expense>> getAllExpenses() {

        return new ResponseEntity<List<Expense>> (expenseService.getAllExpenses(),HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{identityNumber}", method = RequestMethod.GET)
    public ResponseEntity<List<Expense>> getAllExpensesByEmployee(@PathVariable Double identityNumber) {

        return new ResponseEntity<List<Expense>> (expenseService.getAllExpensesByEmployee(identityNumber),HttpStatus.OK);
    }

    @RequestMapping(value = "waiting/{managerId}", method = RequestMethod.GET)
    public ResponseEntity<List<Expense>> getApprovalWaitingExpenses(@PathVariable Double managerId){

        return new ResponseEntity<List<Expense>> (expenseService.getApprovalWaitingExpenses(managerId),HttpStatus.OK);
    }

    @RequestMapping(value = "approve/{expenseId}", method = RequestMethod.GET)
    public ResponseEntity<Void> approveExpense(@PathVariable Double expenseId){
        expenseService.approveExpense(expenseId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveExpense(@RequestBody Expense expense) {
        expenseService.saveExpense(expense);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "reject/{expenseId}", method = RequestMethod.GET)
    public ResponseEntity<Void> rejectExpense(@PathVariable Double expenseId){
        expenseService.rejectExpense(expenseId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}

