package W08_AllTestingMethods;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeRestaurantDaoTest {
    List<Restaurant> restaurants = List.of(new Restaurant(0, "restaurant1", "cuisine1", 6.5, "location1"),
            new Restaurant(1, "Prestaurant1", "cuisine2", 7, "location2"),
            new Restaurant(2, "PPrestaurant2", "cuisine3", 8, "location3"),
            new Restaurant(3, "restaurant3", "cuisine4", 9, "location4"));

    List<Employee> employees = List.of(new Employee(0, "employee1", "job1", 100, 10, "restaurant1"),
            new Employee(1, "employee2", "job2", 110, 20, "Prestaurant1"),
            new Employee(2, "employee3", "job3", 120, 30, "PPrestaurant2"),
            new Employee(3, "employee4", "job3", 130, 35, "PPrestaurant2"),
            new Employee(4, "employee1", "job2", 140, 70, "restaurant3"),
            new Employee(5, "employee1", "job1", 150, 80, "restaurant3"));

    RestaurantDao restaurantDao;
    EmployeeDao employeeDao;
    DaoIntegrationTemplate daoIntegrationTemplate = new DaoIntegrationTemplate();


    @BeforeEach
    void setup() throws SQLException {
        daoIntegrationTemplate.openConnectionAndCleanup();
        employeeDao = new EmployeeDao(daoIntegrationTemplate.connection);
        restaurantDao = new RestaurantDao(daoIntegrationTemplate.connection);
        employees.forEach((e) -> employeeDao.save(e));
        restaurants.forEach((r) -> restaurantDao.save(r));
    }

    @AfterEach
    void clean() throws SQLException {
        daoIntegrationTemplate.close();
    }

    @Test
    void testGetEmployeesFromRestaurant() {
        assertEquals(employees.subList(1, 3).stream().map(Employee::getName).collect(Collectors.toList()),
                employeeDao.getEmployeesFromRestaurant());
    }

    @Test
    void testAverageRestaurant() {
        assertEquals(restaurants.subList(1, 3).stream().map(Restaurant::getName).sorted().collect(Collectors.toList()),
                restaurantDao.getAverageRestaurant());
    }
}