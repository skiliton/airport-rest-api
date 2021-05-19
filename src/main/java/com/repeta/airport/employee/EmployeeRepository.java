package com.repeta.airport.employee;


import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "employees")
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

    final public String SERVICES_DEPT = "services";
    final public String TECHNICIANS_DEPT = "technicians";
    final public String PILOTS_DEPT = "pilots";


/*    @Query(nativeQuery = true, value = "SELECT name, surname FROM employee LIMIT 2")
    Object[][] test();*/

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM employee WHERE supervisor_id IS NULL"
    )
    Collection<Employee> findAllSupervisors();

    @Query(
            nativeQuery = true,
            value = "SELECT *  FROM employee WHERE department=:department"
    )
    Collection<Employee> findAllByDepartment(@Param("department") String department);

    @Query(
        nativeQuery = true,
        value = "SELECT *  FROM employee WHERE employed_since < DATE_SUB(CURDATE(), INTERVAL :years YEAR)"
    )
    Collection<Employee> findAllByWorkExperience(@Param("years") int years);

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM employee WHERE IFNULL(kids,0)>:kids"
    )
    Collection<Employee> findAllWithKidsGreaterThan(@Param("kids") int kids);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM employee WHERE sal_hour BETWEEN :salHour1 AND :salHour2"
    )
    Collection<Employee> findAllBySalHourIsBetween(@Param("salHour1") double salHour1, @Param("salHour2") double salHour2);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM employee WHERE brigade=:brigade"
    )
    Collection<Employee> findAllByBrigade(@Param("brigade") int brigade);

    @Query(
            nativeQuery = true,
            value = "SELECT *" +
                    "FROM employee" +
                    "WHERE brigade IN (SELECT b.id" +
                    "                  FROM brigade b INNER JOIN flight f ON  b.plane_id=f.plane_id" +
                    "                  WHERE f.id=:flightId)"
    )
    Collection<Employee> findAllAssignedToTheFlight(@Param("flightId") int flightId);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM employee WHERE YEAR(FROM_DAYS(DATEDIFF(CURDATE(), employee.date_of_birth))) BETWEEN :age1 AND :age2"
    )
    Collection<Employee> findAllByAge(@Param("age1") int age1, @Param("age2") int age2);



    @Query(
            nativeQuery = true,
            value = "SELECT * FROM employee WHERE brigade IN (SELECT brigade FROM employee GROUP BY brigade HAVING AVG(sal_hour) BETWEEN :salHour1 AND :salHour2);"
    )
    Collection<Employee> findAllByAvgBrigadeSalHourBetween(@Param("salHour1") double salHour1, @Param("salHour2") double salHour2);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM employee WHERE brigade IN (SELECT brigade FROM employee GROUP BY brigade HAVING SUM(sal_hour) BETWEEN :salHour1 AND :salHour2);"
    )
    Collection<Employee> findAllByBrigadeSalHourSumBetween(@Param("salHour1") double salHour1, @Param("salHour2") double salHour2);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM employee WHERE department='" + PILOTS_DEPT + "' AND brigade IS NOT NULL"
    )
    Collection<Employee> findAllPilots();

    @Query(
            nativeQuery = true,
            value = "SELECT" +
                    "   e.id," +
                    "   brigade," +
                    "   supervisor_id," +
                    "   department," +
                    "   name," +
                    "   surname," +
                    "   date_of_birth," +
                    "   gender," +
                    "   kids," +
                    "   sal_hour," +
                    "   employed_since," +
                    "   user_id" +
                    "FROM employee e INNER JOIN medical_inspection m ON e.id = m.pilot_id" +
                    "WHERE m.passed=:passed AND YEAR(m.inspection_date)=:year"
    )
    Collection<Employee> findAllPilotsThatCompletedMedicalInspection(@Param("year") int year, @Param("passed") boolean passed);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM employee WHERE department='" + PILOTS_DEPT + "' AND brigade IS NOT NULL AND gender=:gender"
    )
    Collection<Employee> findAllPilotsByGender(@Param("gender") char gender);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM employee WHERE department='" + PILOTS_DEPT + "' AND brigade IS NOT NULL AND YEAR(FROM_DAYS(DATEDIFF(CURDATE(), employee.date_of_birth)))=:age"
    )
    Collection<Employee> findAllPilotsByAge(@Param("age") int age);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM employee WHERE department='" + PILOTS_DEPT + "' AND brigade IS NOT NULL AND sal_hour BETWEEN :salHour1 AND :salHour2"
    )
    Collection<Employee> findAllPilotsBySalHourBetween(@Param("salHour1") double salHour1, @Param("salHour2") double salHour2);

}
