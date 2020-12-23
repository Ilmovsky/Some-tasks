package com.alex.problem.domain.usecase;

import com.alex.problem.domain.EmployeeDao;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
//This UseCase can be started in other thread in case of network or database
public class GetEmployeesUseCase implements UseCase<GetEmployeesUseCase.Request, List<String>> {

    EmployeeDao employeeDao;

    @Inject
    public GetEmployeesUseCase(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<String> execute(Request params) {
        return employeeDao.getEmployeeIds();
    }

    public static class Request implements UseCase.RequestValues {
    }
}
