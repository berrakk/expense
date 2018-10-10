package dao.jpa;
import dao.EmployeeRepo;
import model.Department;
import model.Employee;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaEmployeeRepo implements EmployeeRepo {

    @PersistenceContext(unitName = "defaultEMF")
    EntityManager em;

    public Employee getEmployeeByIdNumber(Double identityNumber) {
        return em.find(Employee.class, identityNumber);
    }

    public List<Employee> getAllEmployees() {
        Query query = em.createQuery("select e from Employee e");
        return query.getResultList();
    }

    public boolean authenticateUser(Double identityNumber, String password) {
        Query query = em.createQuery("select count(e) from Employee e where e.identityNumber = :identityNumber " +
                "and e.employeePassword = :password");
        query.setParameter("identityNumber", identityNumber);
        query.setParameter("password", password);

        Long resultCount = (Long) query.getSingleResult();

        return (resultCount > 0);
    }

    public void saveEmployee(Employee employee) {
        em.persist(employee);
    }

    public List<Department> getAllDepartments() {
       Query query=em.createQuery("select  d from Department d");
       return query.getResultList();
    }


}
