package com.alex.problem.data;

import com.alex.problem.domain.EmployeeDao;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class DataModule {

    @Binds
    abstract EmployeeDao provideEmployeeDao(EmployeeStore employeeStore);
}
