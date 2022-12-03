package com.example.microservice.Services;


import com.example.microservice.Bean.Employee;
import com.example.microservice.DAO.Admin;
import com.example.microservice.DAO.EmpDep;
import com.example.microservice.DAO.EmployeeDAO;
import com.example.microservice.DAO.Implementation.EmployeeDAOImpl;

import java.util.List;

public class EmployeeService {
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
    public String verifyEmail(Admin admin){
        return employeeDAO.emailVerify(admin);
    }
    public List<EmpDep> getEmployees()
    {
        return  employeeDAO.getEmpList();
    }
    public EmpDep registerEmployee(EmpDep empDep){ return employeeDAO.registerEmployee(empDep); }
    public boolean employeeUpdate(EmpDep empDep){ return employeeDAO.updateEmployee(empDep); }
//    public Employee verifyAll(Employee employee){ return employeeDAO.allVerify(employee); }
    public boolean employeeDelete(Employee employee){ return employeeDAO.deleteEmployee(employee); }
}
