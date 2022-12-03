package com.example.microservice.DAO;

import com.example.microservice.Bean.Department;
import com.example.microservice.Bean.Employee;

import java.util.List;

public interface DepartmentDAO {
    List<String> fetchDepartment();
    boolean checkCount(Department department);
    String getName(Employee employee);
}

