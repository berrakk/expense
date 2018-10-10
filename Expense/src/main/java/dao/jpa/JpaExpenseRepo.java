package dao.jpa;
import dao.ExpenseRepo;
import model.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class JpaExpenseRepo implements ExpenseRepo {

    @PersistenceContext(unitName = "defaultEMF")
    EntityManager em;

    public Expense getExpenseById(Double expenseId) {
        Query query = em.createQuery("select ex from Expense ex join fetch ex.expenseSet where ex.expenseId = :expenseId");
        query.setParameter("expenseId", expenseId);
        return (Expense) query.getSingleResult();
    }

    public void saveExpense(Expense expense) {
        em.persist(expense);
    }

    public List<Expense> getAllExpenses() {
        Query query = em.createQuery("select ex from Expense ex join fetch ex.expenseSet");

        return query.getResultList();
    }

    public List<Expense> getAllExpensesByEmployee(Double identityNumber) {
        Query query = em.createQuery("select ex from Expense ex join fetch ex.expenseSet where ex.employee.identityNumber = :identityNumber");

        query.setParameter("identityNumber", identityNumber);

        return query.getResultList();
    }

    public List<Expense> getApprovalWaitingExpenses(Double managerId) {

        Employee employee = em.find(Employee.class, managerId);
        Double employeeTypeId = employee.getEmployeeType().getEmployeeId();

        List<Expense> expenseList = new ArrayList<Expense>();
        if (employeeTypeId == 2) {
            Query query3 = em.createQuery("select ex from Expense ex join fetch ex.expenseSet " +
                    "where ex.employee.identityNumber in " +
                    "(select e.identityNumber from Employee e where e.manager.identityNumber =:managerId) " +
                    "and ex.approvalStatus.approvalId = 1");
            query3.setParameter("managerId", managerId);
            expenseList = query3.getResultList();

        } else if (employeeTypeId == 3) {
            Query query1 = em.createQuery("select ex from Expense ex join fetch ex.expenseSet where ex.employee.identityNumber in " +
                    "(select e.identityNumber from Employee e where e.manager.identityNumber =:managerId) " +
                    "and ex.approvalStatus.approvalId = 1");
            query1.setParameter("managerId", managerId);
            List<Expense> list1 = query1.getResultList();

            Query query2 = em.createQuery("select ex from Expense ex join fetch ex.expenseSet where ex.employee.identityNumber in" +
                    " (select e.identityNumber from Employee e where e.department.depId in" +
                    " (select d.depId from Department d where d.reportingDepartment in " +
                    "(select d.depId from Department d where d.managerId = :managerId)))and ex.approvalStatus.approvalId = 2");

            query2.setParameter("managerId", managerId);
            List<Expense> list2 = query2.getResultList();

            expenseList.addAll(list1);
            expenseList.addAll(list2);
        }
        return expenseList;
    }

    public void approveExpense(Double expenseId) {

        Query query = em.createQuery("select e from Expense e join fetch e.expenseSet where e.expenseId = :expenseId");
        query.setParameter("expenseId", expenseId);
        Expense expense = (Expense) query.getSingleResult();
        Employee employee = expense.getEmployee();
        ApprovalStatus approvalStatus = expense.getApprovalStatus();
        Set<SubExpense> subExpenses = expense.getExpenseSet();

        if (approvalStatus.getApprovalId() == 1) {

            if (employee.getEmployeeType().getEmployeeId() == 1) {
                Double totalAmount = 0d;
                ApprovalStatus approvalStatusFor1 = new ApprovalStatus();
                for (SubExpense subExpense : subExpenses) {
                    totalAmount += subExpense.getExpenseAmount();
                }

                if (totalAmount > 100d) {
                    approvalStatusFor1.setApprovalId(2d);
                    expense.setApprovalStatus(approvalStatusFor1);
                } else {
                    approvalStatusFor1.setApprovalId(5d);
                    expense.setApprovalStatus(approvalStatusFor1);
                }
            }

        } else if (approvalStatus.getApprovalId() == 2) {
            ApprovalStatus newApprovalStatus = new ApprovalStatus();
            newApprovalStatus.setApprovalId(5d);
            expense.setApprovalStatus(newApprovalStatus);
        }

        em.persist(expense);
    }

    public void rejectExpense(Double expenseId) {
        Expense expense = getExpenseById(expenseId);
        ApprovalStatus approvalStatus = new ApprovalStatus();
        approvalStatus.setApprovalId(3d);

        expense.setApprovalStatus(approvalStatus);

        em.persist(expense);
    }
 }




