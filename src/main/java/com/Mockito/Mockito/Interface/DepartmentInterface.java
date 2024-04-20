package com.Mockito.Mockito.Interface;

import com.Mockito.Mockito.Model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentInterface {
    Employee employeeMaxSalaryWithDepartment(int dept);

    Employee employeeMinSalaryWithDepartment(int dept);

    List<Employee> allEmployeesByDepartment(int dept);

    Map<Integer, List<Employee>> allEmployeesGroupOfDepartment();

    Double employeeSumSalaryWithDepartment(int dept);
}