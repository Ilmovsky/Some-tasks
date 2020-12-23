package com.alex.problem.domain;

import com.alex.problem.entity.Employee;
import org.jetbrains.annotations.NonNls;

import java.util.List;

public interface EmployeeDao {

    @NonNls
    Employee getEmployee(@NonNls String employeeId);

    boolean addEmployee(@NonNls Employee employee);

    @NonNls
    List<String> getEmployeeIds();

}
