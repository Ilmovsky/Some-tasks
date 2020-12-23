package com.alex.problem.domain.usecase;

import com.alex.problem.domain.EmployeeDao;
import com.alex.problem.entity.Employee;
import org.jetbrains.annotations.NonNls;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
//This UseCase can be started in other thread in case of network or database
public class AddEmployeeUseCase implements UseCase<AddEmployeeUseCase.Request, Boolean> {

    EmployeeDao employeeDao;

    @Inject
    public AddEmployeeUseCase(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Boolean execute(Request params) {
        return employeeDao.addEmployee(params.employee);
    }

    public static class Request implements UseCase.RequestValues {
        @NonNls
        private final Employee employee;

        public Request(@NonNls Employee employee) {
            this.employee = employee;
        }

        @NonNls
        public Employee getEmployee() {
            return employee;
        }
    }
}
