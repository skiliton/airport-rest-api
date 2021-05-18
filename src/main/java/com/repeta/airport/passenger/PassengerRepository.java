package com.repeta.airport.passenger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource(collectionResourceRel = "passengers", path = "passenger")
public interface PassengerRepository extends PagingAndSortingRepository<Passenger, Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT p.id, name, surname, date_of_birth, gender, user_id\n" +
                    "FROM (passenger p INNER JOIN ticket t ON p.id = t.passenger_id) INNER JOIN flight f on t.flight_id=f.id\n" +
                    "WHERE f.id=:flightId;"
    )
    Collection<Passenger> findAllPassengersByFlight(@Param("flightId") int flightId);

    @Query(
            nativeQuery = true,
            value = "SELECT p.id, name, surname, date_of_birth, gender, user_id\n" +
                    "FROM (passenger p INNER JOIN ticket t ON p.id = t.passenger_id) INNER JOIN flight f on t.flight_id=f.id\n" +
                    "WHERE DATE(f.takeoff)=:tackeoffDate;"
    )
    Collection<Passenger> findAllPassengersByTakeoffDate(@Param("takeoffDate") String takeoffDate);

    @Query(
            nativeQuery = true,
            value = "SELECT p.id, p.name, surname, date_of_birth, gender, user_id\n" +
                    "FROM ((passenger p INNER JOIN ticket t ON p.id = t.passenger_id) INNER JOIN flight f on t.flight_id=f.id) INNER JOIN airport a ON f.destination_airport_id=a.id\n" +
                    "WHERE DATE(f.takeoff)=:tackeoffDate AND a.name<>'Ukraine';"
    )
    Collection<Passenger> findAllPassengersByTakeoffDateAndFlyAbroad(@Param("takeoffDate") String takeoffDate);

    @Query(
            nativeQuery = true,
            value = "SELECT p.id, name, surname, date_of_birth, gender, user_id\n" +
                    "FROM (passenger p INNER JOIN ticket t ON p.id = t.passenger_id) INNER JOIN passenger_check pc ON p.id = pc.passenger_id\n" +
                    "WHERE pc.check_name='LUGGAGE_CHECKED_IN' AND pc.check_result=true;"
    )
    Collection<Passenger> findAllPassengersWithLuggageCheckedIn();

    @Query(
            nativeQuery = true,
            value = "SELECT id, name, surname, date_of_birth, gender, user_id\n" +
                    "FROM passenger p\n" +
                    "WHERE gender=:gender;"
    )
    Collection<Passenger> findAllPassengersByGender(@Param("takeoffDate") String takeoffDate);

    @Query(
            nativeQuery = true,
            value = "SELECT id, name, surname, date_of_birth, gender, user_id\n" +
                    "FROM passenger p\n" +
                    "WHERE YEAR(FROM_DAYS(DATEDIFF(CURDATE(), p.date_of_birth))) = :age;"
    )
    Collection<Passenger> findAllPassengersByAge(@Param("age") int age);
}
