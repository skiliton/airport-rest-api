package com.repeta.airport.flight;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource(collectionResourceRel = "flight", path = "flight")
public interface FlightRepository extends PagingAndSortingRepository<Flight,Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM flight " +
                    "WHERE type = :type;"
    )
    Collection<Flight> findAllFlightsByType(@Param("type") String type);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM flight " +
                    "WHERE destination_airport_id = :destAirportId;"
    )
    Collection<Flight> findAllFlightsByDestinationAirport(@Param("destAirportId") int destAirportId);

    @Query(
            nativeQuery = true,
            value = "SELECT f.id, " +
                    "       plane_id, " +
                    "       takeoff, " +
                    "       landing, " +
                    "       f.type, " +
                    "       departure_airport_id, " +
                    "       transfer_airport_id, " +
                    "       destination_airport_id, " +
                    "       ticket_price, " +
                    "       max_passengers, " +
                    "       min_passengers, " +
                    "       f.status " +
                    "FROM flight f INNER JOIN plane p ON f.plane_id = p.id " +
                    "WHERE p.type=:planeType;"
    )
    Collection<Flight> findAllFlightsByPlaneType(@Param("planeType") String planeType);

    @Query(
            nativeQuery = true,
            value = "SELECT AVG(passenger_count) " +
                    "FROM flight f " +
                    "         INNER JOIN (SELECT flight_id, COUNT(*) AS passenger_count " +
                    "                     FROM ticket INNER JOIN ticket_log " +
                    "                     WHERE state <> 'REFUNDED' " +
                    "                     GROUP BY flight_id) AS pc ON f.id=pc.flight_id " +
                    "WHERE f.destination_airport_id=:destAirportId;"
    )
    double avgPassengerCountOnFlightByDestinationAirport(@Param("destAirportId") int destAirportId);

    @Query(
            nativeQuery = true,
            value = "SELECT AVG(passenger_count) " +
                    "FROM flight f " +
                    "         INNER JOIN (SELECT flight_id, COUNT(*) AS passenger_count " +
                    "                     FROM ticket INNER JOIN ticket_log " +
                    "                     WHERE state <> 'REFUNDED' " +
                    "                     GROUP BY flight_id) AS pc ON f.id=pc.flight_id " +
                    "WHERE ROUND(TIME_TO_SEC(TIMEDIFF(takeoff, landing)) / 60) BETWEEN :minutes1 AND :minutes2;"
    )
    double avgPassengerCountOnFlightByDuration(@Param("minutes1") int minutes1, @Param("minutes2") int minutes2);

    @Query(
            nativeQuery = true,
            value = "SELECT AVG(passenger_count) " +
                    "FROM flight f " +
                    "         INNER JOIN (SELECT flight_id, COUNT(*) AS passenger_count " +
                    "                     FROM ticket INNER JOIN ticket_log " +
                    "                     WHERE state <> 'REFUNDED' " +
                    "                     GROUP BY flight_id) AS pc ON f.id=pc.flight_id " +
                    "WHERE ticket_price BETWEEN :ticketPrice1 AND :ticketPrice2;"
    )
    double avgPassengerCountOnFlightByTicketPrice(@Param("ticketPrice1") double ticketPrice1, @Param("ticketPrice2") double ticketPrice2);

    @Query(
            nativeQuery = true,
            value = "SELECT AVG(passenger_count) " +
                    "FROM flight f " +
                    "         INNER JOIN (SELECT flight_id, COUNT(*) AS passenger_count " +
                    "                     FROM ticket INNER JOIN ticket_log " +
                    "                     WHERE state <> 'REFUNDED' " +
                    "                     GROUP BY flight_id) AS pc ON f.id=pc.flight_id " +
                    "WHERE takeoff BETWEEN :takeoffTime1 AND :takeoffTime2;"
    )
    double avgPassengerCountOnFlightByTakeoffTime(@Param("takeoffTime1") String takeoffTime1, @Param("takeoffTime2") String takeoffTime2);

    @Query(
            nativeQuery = true,
            value = "SELECT f.id, " +
                    "       plane_id, " +
                    "       takeoff, " +
                    "       landing, " +
                    "       type, " +
                    "       departure_airport_id, " +
                    "       transfer_airport_id, " +
                    "       destination_airport_id, " +
                    "       ticket_price, " +
                    "       max_passengers, " +
                    "       min_passengers, " +
                    "       status " +
                    "FROM flight f " +
                    "         INNER JOIN flight_log fl ON f.id = fl.flight_id " +
                    "WHERE state LIKE 'DELAYED DUE TO %';"
    )
    Collection<Flight> findAllDelayedFlights();

    @Query(
            nativeQuery = true,
            value = "SELECT f.id, " +
                    "       plane_id, " +
                    "       takeoff, " +
                    "       landing, " +
                    "       type, " +
                    "       departure_airport_id, " +
                    "       transfer_airport_id, " +
                    "       destination_airport_id, " +
                    "       ticket_price, " +
                    "       max_passengers, " +
                    "       min_passengers, " +
                    "       status " +
                    "FROM flight f " +
                    "         INNER JOIN flight_log fl ON f.id = fl.flight_id " +
                    "WHERE state = :reason;"
    )
    Collection<Flight> findAllDelayedFlightsByReason(@Param("reason") String reason);

    @Query(
            nativeQuery = true,
            value = "SELECT f.id, " +
                    "       plane_id, " +
                    "       takeoff, " +
                    "       landing, " +
                    "       type, " +
                    "       departure_airport_id, " +
                    "       transfer_airport_id, " +
                    "       destination_airport_id, " +
                    "       ticket_price, " +
                    "       max_passengers, " +
                    "       min_passengers, " +
                    "       status " +
                    "FROM flight f " +
                    "         INNER JOIN flight_log fl ON f.id = fl.flight_id " +
                    "WHERE destination_airport_id = :destAirportId " +
                    "  AND state LIKE 'DELAYED DUE TO %';"
    )
    Collection<Flight> findAllDelayedFlightsByDestinationAirport(@Param("destAirportId") String destAirportId);

    @Query(
            nativeQuery = true,
            value = "WITH " +
                    "takeoff_est AS ( " +
                    "    SELECT takeoff AS time " +
                    "    FROM flight " +
                    "    WHERE id = :flightId " +
                    "), " +
                    "takeoff_act AS ( " +
                    "         SELECT time " +
                    "         FROM flight_log " +
                    "         WHERE flight_id = :flightId " +
                    "           AND state = 'TAKEOFF' " +
                    ") " +
                    "SELECT COUNT(*) " +
                    "FROM ticket_log tl " +
                    "         INNER JOIN ticket t ON tl.ticket_id = t.id " +
                    "WHERE flight_id = :flightId " +
                    "  AND state = 'REFUNDED' " +
                    "  AND time BETWEEN (SELECT time FROM takeoff_est) AND (SELECT time FROM takeoff_act)"
    )
    long countRefundedTicketsDuringDelayByFlightId(@Param("flightId") int flightId);

    @Query(
            nativeQuery = true,
            value = "SELECT f.id, " +
                    "       plane_id, " +
                    "       takeoff, " +
                    "       landing, " +
                    "       type, " +
                    "       departure_airport_id, " +
                    "       transfer_airport_id, " +
                    "       destination_airport_id, " +
                    "       ticket_price, " +
                    "       max_passengers, " +
                    "       min_passengers, " +
                    "       status " +
                    "FROM flight f " +
                    "         INNER JOIN flight_log fl ON f.id = fl.flight_id " +
                    "WHERE state LIKE 'CANCELED DUE TO %';"
    )
    Collection<Flight> findAllCancelledFlights();

    @Query(
            nativeQuery = true,
            value = "SELECT f.id, " +
                    "       plane_id, " +
                    "       takeoff, " +
                    "       landing, " +
                    "       type, " +
                    "       departure_airport_id, " +
                    "       transfer_airport_id, " +
                    "       destination_airport_id, " +
                    "       ticket_price, " +
                    "       max_passengers, " +
                    "       min_passengers, " +
                    "       status " +
                    "FROM flight f " +
                    "         INNER JOIN flight_log fl ON f.id = fl.flight_id " +
                    "WHERE destination_airport_id = :destAirportId " +
                    "    AND state LIKE 'CANCELED DUE TO %';"
    )
    Collection<Flight> findAllCancelledFlightsByDestinationAirport(@Param("destAirportId") String destAirportId);

    @Query(
            nativeQuery = true,
            value = "SELECT f.id, " +
                    "       plane_id, " +
                    "       takeoff, " +
                    "       landing, " +
                    "       type, " +
                    "       departure_airport_id, " +
                    "       transfer_airport_id, " +
                    "       destination_airport_id, " +
                    "       ticket_price, " +
                    "       max_passengers, " +
                    "       min_passengers, " +
                    "       status " +
                    "FROM flight f " +
                    "         INNER JOIN flight_log fl ON f.id = fl.flight_id " +
                    "WHERE departure_airport_id = :depAirportId " +
                    "  AND destination_airport_id = :destAirportId " +
                    "    AND state LIKE 'CANCELED DUE TO %';"
    )
    Collection<Flight> findAllCancelledFlightsByRoute(@Param("depAirportId") int depAirportId, @Param("destAirportId") int destAirportId);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM flight f " +
                    "WHERE (max_passengers - (SELECT COUNT(*) " +
                    "                         FROM ticket " +
                    "                                  INNER JOIN ticket_log " +
                    "                         WHERE flight_id = f.id " +
                    "                           AND state <> 'REFUNDED')) BETWEEN :emptSeats1 AND :emptSeats1;"
    )
    Collection<Flight> findAllCancelledFlightsByEmptySeats(@Param("emptSeats1") int emptSeats1, @Param("emptSeats2") int emptSeats2);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM flight f " +
                    "WHERE ((SELECT COUNT(*) " +
                    "       FROM ticket " +
                    "                INNER JOIN ticket_log " +
                    "       WHERE flight_id = f.id " +
                    "         AND state <> 'REFUNDED') / max_passengers) BETWEEN :ratValue1 AND :ratValue2"
    )
    Collection<Flight> findAllCancelledFlightsByEmptySeatsRatio(@Param("ratValue1") int ratValue1, @Param("ratValue2") int ratValue2);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM flight " +
                    "WHERE departure_airport_id = :depAirportId " +
                    "  AND destination_airport_id = :destAirportId;"
    )
    Collection<Flight> findAllFlightsByRoute(@Param("depAirportId") int depAirportId, @Param("destAirportId") int destAirportId);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM flight " +
                    "WHERE ROUND(TIME_TO_SEC(TIMEDIFF(takeoff, landing)) / 60) BETWEEN :time1 AND :time2;"
    )
    Collection<Flight> findAllFlightsByDuration(@Param("minutes1") int minutes1, @Param("minutes2") int minutes2);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM flight " +
                    "WHERE ticket_price BETWEEN :ticketPrice1 AND :ticketPrice2;"
    )
    Collection<Flight> findAllFlightsByTicketPrice(@Param("ticketPrice1") double ticketPrice1, @Param("ticketPrice2") double ticketPrice2);

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM flight " +
                    "WHERE departure_airport_id = :depAirportId " +
                    "  AND destination_airport_id = :destAirportId " +
                    "  AND (ROUND(TIME_TO_SEC(TIMEDIFF(takeoff, landing)) / 60) = :minutes) " +
                    "  AND ticket_price = :ticketPrice;"
    )
    Collection<Flight> findAllFlightsByRouteAndTileAndTicketPrice(@Param("depAirportId") int depAirportId, @Param("destAirportId") int destAirportId, @Param("minutes") int minutes, @Param("ticketPrice") double ticketPrice);

}