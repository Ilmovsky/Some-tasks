import com.alex.problem.data.EmployeeStore
import com.alex.problem.domain.usecase.AddEmployeeUseCase
import com.alex.problem.domain.usecase.GetEmployeeUseCase
import com.alex.problem.domain.usecase.GetEmployeesUseCase
import com.alex.problem.entity.Employee
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class UsecaseTests {

    lateinit var addEmployeeUseCase: AddEmployeeUseCase
    lateinit var getEmployeeUseCase: GetEmployeeUseCase
    lateinit var getEmployeesUseCase: GetEmployeesUseCase

    @Before
    fun init() {
        // INIT
        val employeeDao = EmployeeStore()
        addEmployeeUseCase = AddEmployeeUseCase(employeeDao)
        getEmployeeUseCase = GetEmployeeUseCase(employeeDao)
        getEmployeesUseCase = GetEmployeesUseCase(employeeDao)
    }

    @Test
    fun addPeerTest() {
        val result = addEmployeeUseCase.execute(AddEmployeeUseCase.Request(
            Employee("Alex", 28, "Big Street", null, Employee.BOSS_ID)
        ))
        assertTrue(result)
    }

    @Test
    fun addTwoDifferentIdsPeers() {
        addEmployeeUseCase.execute(AddEmployeeUseCase.Request(
            Employee("Alex", 28, "Big Street", null, Employee.BOSS_ID)
        ))
        val result = addEmployeeUseCase.execute(AddEmployeeUseCase.Request(
            Employee("Alex2", 28, "Big Street", null, Employee.BOSS_ID)
        ))
        assertTrue(result)
    }

    @Test
    fun addRepeatedIdPeer() {
        addEmployeeUseCase.execute(AddEmployeeUseCase.Request(
            Employee("Alex", 28, "Big Street", null, Employee.BOSS_ID)
        ))
        val result = addEmployeeUseCase.execute(AddEmployeeUseCase.Request(
            Employee("Alex", 28, "Big Street", null, Employee.BOSS_ID)
        ))
        assertFalse(result)
    }

    @Test
    fun getAddedPeerTest() {
        addEmployeeUseCase.execute(AddEmployeeUseCase.Request(
            Employee("Alex", 28, "Big Street", null, Employee.BOSS_ID)
        ))
        val employee = getEmployeeUseCase.execute(GetEmployeeUseCase.Request("Alex"))
        assertFalse(employee.isEmpty)
    }

    @Test
    fun getPeerByWrongId() {
        addEmployeeUseCase.execute(AddEmployeeUseCase.Request(
            Employee("Alex", 28, "Big Street", null, Employee.BOSS_ID)
        ))
        val employee = getEmployeeUseCase.execute(GetEmployeeUseCase.Request("Alex1"))
        assertTrue(employee.isEmpty)
    }

    @Test
    fun getListOFPeers() {
        addEmployeeUseCase.execute(AddEmployeeUseCase.Request(
            Employee("Alex", 28, "Big Street", null, Employee.BOSS_ID)
        ))
        addEmployeeUseCase.execute(AddEmployeeUseCase.Request(
            Employee("Alex2", 28, "Big Street", null, Employee.BOSS_ID)
        ))
        val list = getEmployeesUseCase.execute(GetEmployeesUseCase.Request())
        assertTrue(list.size == 3)
    }
}