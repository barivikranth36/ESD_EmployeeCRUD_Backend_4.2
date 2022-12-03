package com.example.microservice.DAO.Implementation;

import com.example.microservice.Bean.Department;
import com.example.microservice.Bean.Employee;
import com.example.microservice.DAO.DepartmentDAO;
import com.example.microservice.DAO.EmpDep;
import com.example.microservice.Util.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    @Override
    public List<String> fetchDepartment() {
        Session session = SessionUtil.getSession();
        List<String> deptList = new ArrayList<>();
        List<String> newDeptList = new ArrayList<>();
        try {
            for(final Object response: session.createQuery("select name from Department").list())
            {
                deptList.add((String) response);
            }

            if(deptList.size() == 0)
                return null;

            for(final String s: deptList) {
                List<Department> list = new ArrayList<>();
                for (final Object dep : session.createQuery("FROM Department WHERE name= :name")
                        .setParameter("name", s).list()) {
                    list.add((Department) dep);
                }
                int deptCapacity = list.get(0).getCapacity();
                int deptId = list.get(0).getDept_id();

                List<Employee> employeeList = new ArrayList<>(
                        session.createQuery("FROM Employee WHERE department.dept_id= :dept_id")
                                .setParameter("dept_id", deptId).list()
                );

                int empCount = employeeList.size();

                if (deptCapacity >= empCount + 1)
                    newDeptList.add(s);
            }
            if(newDeptList.size() == 0)
                return null;
            return newDeptList;
        }
        catch (HibernateException e)
        {
            System.out.println(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public boolean checkCount(Department department) {
        Session session = SessionUtil.getSession();
        try {
//            List<Department> list = new ArrayList<>(
//                    session.createQuery("FROM Department WHERE name= :name")
//                            .setParameter("name",department.getName()).list()
//            );
            List<Department> list = new ArrayList<>();
            for(final Object dep : session.createQuery("FROM Department WHERE name= :name")
                    .setParameter("name", department.getName()).list()){
                    list.add((Department) dep);
            }
            int deptCapacity = list.get(0).getCapacity();
            int deptId = list.get(0).getDept_id();

            List<Employee> employeeList = new ArrayList<>(
                    session.createQuery("FROM Employee WHERE department.dept_id= :dept_id")
                            .setParameter("dept_id",deptId).list()
            );

            int empCount = employeeList.size();

            if (deptCapacity < empCount + 1)
                return false;
            return true;

        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public String getName(Employee employee) {
        Session session = SessionUtil.getSession();
        Employee employee1 = new Employee();
        try{

            List<Employee> list = new ArrayList<>(
                    session.createQuery("FROM Employee WHERE email= :email")
                            .setParameter("email",employee.getEmail()).list()
            );

            if(list.size() == 0)
                return null;
            return list.get(0).getDepartment().getName();
//            Query query = session.createQuery("from Employee where email=:email");
//            query.setParameter("email",employee.getEmail());
//            for(final Object fetch: query.list()){
//                employee1 = (Employee) fetch;
//            }
//            return employee1.getDepartment().getName();
//            return employee1.getDepartment().getName();
        }
        catch (HibernateException e){
            System.out.println(e.getLocalizedMessage());
            return null;
        }

    }
}
