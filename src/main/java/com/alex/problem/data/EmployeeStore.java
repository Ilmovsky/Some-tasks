package com.alex.problem.data;

import com.alex.problem.domain.EmployeeDao;
import com.alex.problem.entity.Employee;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

@Singleton
public class EmployeeStore implements EmployeeDao {

    private Map<String, EmployeeData> employeesMap = new HashMap<>();

    private Map<String, Set<String>> reportsMap = new HashMap<>();

    @Inject
    public EmployeeStore() {
        //Adding the main boss to have possibility to add bosses for every new employee
        employeesMap.put(Employee.BOSS_ID,
                new EmployeeData(Employee.BOSS_ID, 35, "Moon Street", null));
    }

    @Override
    @NonNls
    public Employee getEmployee(@NonNls String employeeId) {
        EmployeeData employeeData = employeesMap.get(employeeId);
        if (employeeData == null) {
            return Employee.createEmpty();
        }
        Employee employee = employeeData.getEmployee();
        employee.setCurrentReports(reportsMap.get(employeeId));
        return employee;
    }

    @Override
    public boolean addEmployee(@NonNls Employee employee) {
        String employeeId = employee.getEmployeeId();
        if (employeesMap.containsKey(employeeId)) {
            return false;
        }
        employeesMap.put(employeeId, new EmployeeData(employee));
        String bossId = employee.getCurrentBoss();
        if (!reportsMap.containsKey(bossId)) {
            reportsMap.put(bossId, new HashSet<>());
        }
        reportsMap.get(bossId).add(employeeId);
        return true;
    }

    @Override
    public List<String> getEmployeeIds() {
        return new ArrayList<>(employeesMap.keySet());
    }

    private class EmployeeData {
        @NonNls
        private final String employeeId;

        private final long age;

        @Nullable
        private final String address;

        @Nullable
        private final String currentBoss;

        public EmployeeData(String employeeId, long age, @Nullable String address, @Nullable String currentBoss) {
            this.employeeId = employeeId;
            this.age = age;
            this.address = address;
            this.currentBoss = currentBoss;
        }

        public EmployeeData(@NonNls Employee employee) {
            this.employeeId = employee.getEmployeeId();
            this.age = employee.getAge();
            this.address = employee.getAddress();
            this.currentBoss = employee.getCurrentBoss();
        }

        private Employee getEmployee () {
            return new Employee(employeeId, age, address, null, currentBoss);
        }
    }
}
