package com.example.microservice.DAO;

import com.example.microservice.Bean.Employee;

import java.util.List;

public interface EmployeeDAO {
    String emailVerify(Admin admin);

    List<EmpDep> getEmpList();

    EmpDep registerEmployee(EmpDep empDep);

    boolean updateEmployee(EmpDep empDep);

//    Employee allVerify(Employee employee);

    boolean deleteEmployee(Employee employee);
}