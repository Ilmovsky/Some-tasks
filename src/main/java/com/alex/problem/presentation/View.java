package com.alex.problem.presentation;

import com.alex.problem.di.AppComponent;
import com.alex.problem.domain.usecase.AddEmployeeUseCase;
import com.alex.problem.domain.usecase.GetEmployeeUseCase;
import com.alex.problem.domain.usecase.GetEmployeesUseCase;
import com.alex.problem.entity.Employee;
import com.alex.problem.util.Communicator;
import dagger.Lazy;

import javax.inject.Inject;
import java.util.InputMismatchException;
import java.util.List;


// For presentation better to use MVVM or MVI but  current UI part too easy to add it
public class View {

    private static final String ADD = "A";
    private static final String GET = "B";
    private static final String CLOSE = "F";
    private static final int DEFAULT_AGE = 24;

    @Inject
    Lazy<AddEmployeeUseCase> addEmployeeUseCaseLazy;

    @Inject
    Lazy<GetEmployeeUseCase> getEmployeeUseCaseLazy;

    @Inject
    Lazy<GetEmployeesUseCase> getEmployeesUseCaseLazy;

    private final Communicator communicator;

    @Inject
    public View(AppComponent appComponent) {
        this.communicator = new Communicator(System.in, System.out);
        appComponent.inject(this);
    }

    public void start() {
        System.out.println("You can add new employee or get tha old one:" + '\'');

        while (true) {
            System.out.println("Enter " + ADD + " if you want to add new employee");
            System.out.println("Enter " + GET + " if you want to receive existing info");
            System.out.println("Enter " + CLOSE + " if you want to finish app");

            String answer = communicator.askString("Choose one option:");

            switch (answer.toUpperCase()) {
                case ADD: {
                    addEmployee();
                    break;
                }
                case GET: {
                    getEmployee();
                    break;
                }
                case CLOSE: {
                    return;
                }
                default: {
                    System.out.println("You added wrong value.Try again");
                }
            }
        }
    }

    private void addEmployee() {
        Employee newEmployee = createNewEmployee();
        boolean isDone = addEmployeeUseCaseLazy
                .get()
                .execute(new AddEmployeeUseCase.Request(newEmployee));

        if (isDone) {
            System.out.println("New employee was added.");
        } else {
            System.out.println("The employee with current id exists. Create a new one");
        }
    }

    private void getEmployee() {
        String id = communicator.askString("Enter employee id:");
        Employee employee = getEmployeeUseCaseLazy
                .get()
                .execute(new GetEmployeeUseCase.Request(id));
        if (employee.isEmpty()) {
            System.out.println("There is no current employee");
        } else {
            System.out.println("Employee: " + '\'' + employee.toString());
        }
    }

    private Employee createNewEmployee() {
        List<String> employeeIds = getEmployeesUseCaseLazy
                .get()
                .execute(new GetEmployeesUseCase.Request());
        System.out.println("You have to choose boss for current employee");
        String bossId;
        try {
            int position = communicator.askInt("Enter number from 0 to " + (employeeIds.size() - 1));
            if (position > employeeIds.size() - 1) {
                bossId = Employee.BOSS_ID;
            } else {
                bossId = employeeIds.get(position);
            }
        } catch (InputMismatchException e) {
            bossId = Employee.BOSS_ID;
        }
        String id = communicator.askString("Enter employee id:");
        int age;
        try {
            age = communicator.askInt("Enter employee age:");
        } catch (InputMismatchException e) {
            age = DEFAULT_AGE;
        }
        String address = communicator.askString("Enter employee address:");
        return new Employee(id, age, address, null, bossId);
    }
}
