package com.alex.problem.entity;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

//POJO
public final class Employee {

    public static final String BOSS_ID = "BOSS_ID";

    private static final String EMPTY_ID = "EMPTY_ID";

    @NonNls
    private final String employeeId;

    private final long age;

    @Nullable
    private final String address;

    @Nullable
    private final String currentBoss;

    @Nullable
    private Set<String> currentReports;

    public Employee(@NonNls String employeeId, long age, @Nullable String address,
                    @Nullable Set<String> currentReports, @Nullable String currentBoss) {
        this.employeeId = employeeId;
        this.age = age;
        this.address = address;
        this.currentReports = currentReports;
        this.currentBoss = currentBoss;
    }

    public static Employee createEmpty() {
        return new Employee(EMPTY_ID, 0, null, null, null);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public long getAge() {
        return age;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    @Nullable
    public String getCurrentBoss() {
        return currentBoss;
    }

    @Nullable
    public Set<String> getCurrentReports() {
        return currentReports;
    }

    public void setCurrentReports(@Nullable Set<String> currentReports) {
        this.currentReports = currentReports;
    }

    public boolean isEmpty() {
        return employeeId.equals(EMPTY_ID);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", currentReports=" + currentReports +
                ", currentBoss='" + currentBoss + '\'' +
                '}';
    }
}
