package com.example.microservice.DAO.Implementation;

import com.example.microservice.Bean.Department;
import com.example.microservice.Bean.Employee;
import com.example.microservice.DAO.Admin;
import com.example.microservice.DAO.DepartmentDAO;
import com.example.microservice.DAO.EmpDep;
import com.example.microservice.DAO.EmployeeDAO;
import com.example.microservice.Util.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public String emailVerify(Admin admin) {
        try (Session session = SessionUtil.getSession()){
//            Query query = session.createQuery("from Employee where email=:email and department in (select dept_id from Department where name='HR')");
//            query.setParameter("email", employee.getEmail());
//            query.setParameter("name", "HR");
            if(admin.getEmail().equals("admin@iiitb.ac.in") && admin.getPassword().equals("12345678"))
                return "Success";
//            for (final Object fetch : query.list()) {
//                return (Employee) fetch;
//            }
        }
        catch (HibernateException e) {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
        return null;
    }

    @Override
    public List<EmpDep> getEmpList() {
        Session session = SessionUtil.getSession();
//        List<Employee> employees = new ArrayList<>();
//        List<EmpDep> empDepList = new ArrayList<>();
//        try{
//            for(Object fetch : session.createQuery("from Employee ").list()){
//                employees.add((Employee) fetch);
//            }
//        }
        try {
            List<Employee> emplist = new ArrayList<>(
                    session.createQuery("FROM Employee").list()
            );

            if(emplist.size() == 0)
                return null;

            List<EmpDep> empDepList = new ArrayList<>();
            for(Employee emp: emplist) {
                EmpDep ed = new EmpDep(emp.getEmployee_id(), emp.getFirst_name(), emp.getLast_name(), emp.getEmail(), emp.getTitle(), emp.getPhoto_path(), emp.getDepartment().getName());
                empDepList.add(ed);
            }
            return empDepList;
        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }
//        for(final Employee emp : employees){
//            EmpDep ed = new EmpDep(emp,emp.getDepartment().getName());
//            empDepList.add(ed);
//        }
//
//        return empDepList;
    }

    @Override
    public EmpDep registerEmployee(EmpDep empDep) {

        // checking if capacity of that dept is full or not
        DepartmentDAO departmentDAO = new DepartmentDAOImpl();
        Department checkDept = new Department();
        checkDept.setName(empDep.getName());
        if(!departmentDAO.checkCount(checkDept)) {
            System.out.println("Department capacity exceeded");
            return null;
        }

        try(Session session = SessionUtil.getSession()) {
            Department department = new Department();
            Query query1 = session.createQuery("from Department where name=:name");
            query1.setParameter("name", empDep.getName());
            for (final Object fetch1 : query1.list()) {
                department = (Department) fetch1;
            }

            Transaction transaction = session.beginTransaction();

            Employee employee = new Employee();
            employee.setDepartment(department);
            employee.setEmail(empDep.getEmail());
            employee.setFirst_name(empDep.getFirst_name());
            employee.setLast_name(empDep.getLast_name());
            employee.setTitle(empDep.getTitle());
            employee.setPhoto_path(empDep.getPhoto_path());
            session.persist(employee);
            transaction.commit();

            EmpDep ed = new EmpDep(employee.getEmployee_id(), employee.getFirst_name(), employee.getLast_name(), employee.getEmail(), employee.getTitle(), employee.getPhoto_path(), employee.getDepartment().getName());
            return ed;
        }
        catch (HibernateException e)
        {
            System.out.println(e.getLocalizedMessage());
            return null;
        }

    }

    @Override
    public boolean updateEmployee(EmpDep empDep) {
        Session session = SessionUtil.getSession();
        try{
            Transaction transaction = session.beginTransaction();
            Query query1 = session.createQuery("from Department where name=:name");
            query1.setParameter("name",empDep.getName());
            Department department = new Department();
            for(final Object fetch1 : query1.list()){
                department = (Department) fetch1;
            }
            System.out.println(empDep.getEmployee_id());
            Query query2 = session.createQuery("update Employee set first_name=:fname,last_name=:lname,email=:email,title=:title,photo_path=:photo_path,department=:department where employee_id=:employee_id");
            query2.setParameter("fname",empDep.getFirst_name());
            query2.setParameter("lname",empDep.getLast_name());
            query2.setParameter("email",empDep.getEmail());
            query2.setParameter("title",empDep.getTitle());
            query2.setParameter("photo_path",empDep.getPhoto_path());
            query2.setParameter("department",department);
            query2.setParameter("employee_id",empDep.getEmployee_id());
            query2.executeUpdate();
            transaction.commit();
            return true;
        }
        catch (HibernateException e)
        {
            System.out.println(e.getLocalizedMessage());
            return false;
        }

    }

//    @Override
//    public Employee allVerify(Employee employee) {
//        try (Session session = SessionUtil.getSession()){
//            Query query = session.createQuery("from Employee where email=:email");
//            query.setParameter("email", employee.getEmail());
//            for (final Object fetch : query.list()) {
//                return (Employee) fetch;
//            }
//        }
//        catch (HibernateException e) {
//            System.out.println(e.getLocalizedMessage());
//            return null;
//        }
//        return null;
//    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        Session session = SessionUtil.getSession();
        try{
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete Employee where email=:email");
            query.setParameter("email",employee.getEmail());
            query.executeUpdate();
            transaction.commit();
            return true;
        }
        catch (HibernateException e){
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }
}
