package com.alex.problem.domain;

import com.alex.problem.domain.usecase.AddEmployeeUseCase;
import com.alex.problem.domain.usecase.GetEmployeeUseCase;
import com.alex.problem.domain.usecase.GetEmployeesUseCase;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DomainModule {

    @Singleton
    @Provides
    AddEmployeeUseCase providesAddEmployeeUseCase(EmployeeDao dao) {
        return new AddEmployeeUseCase(dao);
    }

    @Singleton
    @Provides
    GetEmployeeUseCase providesGetEmployeeUseCase(EmployeeDao dao) {
        return new GetEmployeeUseCase(dao);
    }

    @Singleton
    @Provides
    GetEmployeesUseCase providesGetEmployeesUseCase(EmployeeDao dao) {
        return new GetEmployeesUseCase(dao);
    }
}
