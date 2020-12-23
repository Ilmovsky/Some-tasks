package com.alex.problem.domain.usecase;

import com.alex.problem.domain.EmployeeDao;
import com.alex.problem.entity.Employee;
import org.jetbrains.annotations.NonNls;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
//This UseCase can be started in other thread in case of network or database
public class GetEmployeeUseCase implements UseCase<GetEmployeeUseCase.Request, Employee> {

    EmployeeDao employeeDao;

    @Inject
    public GetEmployeeUseCase(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee execute(Request params) {
        return employeeDao.getEmployee(params.employeeId);
    }

    public static class Request implements UseCase.RequestValues {
        @NonNls
        private final String employeeId;

        public Request(@NonNls String employeeId) {
            this.employeeId = employeeId;
        }

        @NonNls
        public String  getEmployeeId() {
            return employeeId;
        }
    }
}
