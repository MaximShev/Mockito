package com.Mockito.Mockito.Service;

import com.Mockito.Mockito.Model.Employee;
import com.Mockito.Mockito.Exception.EmployeeAlreadyAddedException;
import com.Mockito.Mockito.Exception.EmployeeNotFoundException;
import com.Mockito.Mockito.Exception.EmployeeStorageIsFullException;
import com.Mockito.Mockito.Interface.EmployeeInterface;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService implements EmployeeInterface {
    static final int EMPLOYEES_MAX = 10;
    private final Map<String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int dept, double salary) throws BadRequestException {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new BadRequestException();
        }
        String capFirstName = StringUtils.capitalize(firstName);
        String capLastName = StringUtils.capitalize(lastName);
        Employee employee = new Employee(capFirstName, capLastName, dept, salary);
        if (employees.containsKey(capFirstName + capLastName)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < EMPLOYEES_MAX) {
            employees.put(capFirstName + capLastName, employee);
            return employee;
        } else {
            throw new EmployeeStorageIsFullException();
        }
    }

    @Override
    public Employee delEmployee(String firstName, String lastName, int dept, double salary) {
        Employee employee = new Employee(firstName, lastName, dept, salary);
        if (employees.containsKey(firstName + lastName)) {
            employees.remove(employee.getFirstName() + employee.getLastName());
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, int dept, double salary) {
        Employee employee = new Employee(firstName, lastName, dept, salary);
        if (employees.containsKey(firstName + lastName)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public List<Employee> allEmployees() {
        return new ArrayList<>(employees.values());
    }
}