package com.example.microservice.Services;

import com.example.microservice.Bean.Department;
import com.example.microservice.Bean.Employee;
import com.example.microservice.DAO.DepartmentDAO;
import com.example.microservice.DAO.Implementation.DepartmentDAOImpl;

import java.util.List;

public class DepartmentService {
    DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    public List<String> getDept(){
        return departmentDAO.fetchDepartment();
    }
    public boolean departmentCount(Department department){
        return departmentDAO.checkCount(department);
    }
    public String getName(Employee employee){ return departmentDAO.getName(employee); }
}
