package com.repeta.airport.ticket;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource(collectionResourceRel = "tickets", path = "ticket")
public interface TicketRepository extends PagingAndSortingRepository<Ticket,Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(*) " +
                    "FROM ticket t INNER JOIN ticket_log tl ON t.id = tl.ticket_id " +
                    "WHERE tl.state = 'REFUNDED' AND t.flight_id=:flightId"
    )
    public long countRefundedTicketsByFlight(@Param("flightId") int flightId);

    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(*) " +
                    "FROM ticket t INNER JOIN ticket_log tl ON t.id = tl.ticket_id " +
                    "WHERE tl.state = 'REFUNDED' AND DATE(tl.time)=:date"
    )
    public long countRefundedTicketsByDate(@Param("date") String date);

    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(*) " +
                    "FROM (ticket t INNER JOIN flight f ON t.flight_id = f.id) INNER JOIN ticket_log tl ON t.id = tl.ticket_id " +
                    "WHERE tl.state = 'REFUNDED' AND f.destination_airport_id=:destAirportId"
    )
    public long countRefundedTicketsByDestinationAirport(@Param("destAirportId") int destAirportId);

    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(*) " +
                    "FROM (ticket t INNER JOIN flight f ON t.flight_id = f.id) INNER JOIN ticket_log tl ON t.id = tl.ticket_id " +
                    "WHERE tl.state = 'REFUNDED' AND f.ticket_price=:ticketPrice;"
    )
    public long countRefundedTicketsByTicketPrice(@Param("ticketPrice") double ticketPrice);

    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(*) " +
                    "FROM (ticket t INNER JOIN passenger p ON t.passenger_id = p.id) INNER JOIN ticket_log tl ON t.id = tl.ticket_id " +
                    "WHERE tl.state = 'REFUNDED' AND p.gender=:gender;"
    )
    public long countRefundedTicketsByGender(@Param("gender") char gender);

    @Query(
            nativeQuery = true,
            value = "SELECT COUNT(*) " +
                    "FROM (ticket t INNER JOIN passenger p ON t.passenger_id = p.id) INNER JOIN ticket_log tl ON t.id = tl.ticket_id " +
                    "WHERE tl.state = 'REFUNDED' AND YEAR(FROM_DAYS(DATEDIFF(CURDATE(), p.date_of_birth))) = :age;"
    )
    public long countRefundedTicketsByAge(@Param("age") int age);

    @Query(
            nativeQuery = true,
            value = "SELECT t.id, passenger_id, flight_id, seat, t.status " +
                    "FROM (ticket t INNER JOIN flight f ON t.flight_id = f.id) INNER JOIN ticket_log tl on t.status=tl.id " +
                    "WHERE tl.state='RESERVED' AND f.id=:flightId;"
    )
    public Collection<Ticket> findAllReservedTicketsByFlight(@Param("flightId") int flightId);

    @Query(
            nativeQuery = true,
            value = "SELECT t.id, passenger_id, flight_id, seat, t.status " +
                    "FROM (ticket t INNER JOIN flight f ON t.flight_id = f.id) INNER JOIN ticket_log tl on t.status=tl.id " +
                    "WHERE tl.state='RESERVED' AND DATE(tl.time) = :date;"
    )
    public Collection<Ticket> findAllReservedTicketsByDate(@Param("date") int date);

    @Query(
            nativeQuery = true,
            value = "SELECT t.id, passenger_id, flight_id, seat, t.status " +
                    "FROM (ticket t INNER JOIN flight f ON t.flight_id = f.id) INNER JOIN ticket_log tl on t.status=tl.id " +
                    "WHERE tl.state='RESERVED' AND f.destination_airport_id=:destAirportId;"
    )
    public Collection<Ticket> findAllReservedTicketsByDestinationAirport(@Param("destAirportId") int destAirportId);

    @Query(
            nativeQuery = true,
            value = "SELECT t.id, passenger_id, flight_id, seat, t.status " +
                    "FROM (ticket t INNER JOIN flight f ON t.flight_id = f.id) INNER JOIN ticket_log tl on t.status=tl.id " +
                    "WHERE tl.state='RESERVED' AND f.ticket_price=:ticketPrice;"
    )
    public Collection<Ticket> findAllReservedTicketsByTicketPrice(@Param("ticketPrice") double ticketPrice);

    @Query(
            nativeQuery = true,
            value = "SELECT t.id, passenger_id, flight_id, seat, t.status " +
                    "FROM (ticket t INNER JOIN flight f ON t.flight_id = f.id) INNER JOIN ticket_log tl on t.status=tl.id " +
                    "WHERE tl.state='RESERVED' AND f.takeoff=:takeoffTime;"
    )
    public Collection<Ticket> findAllReservedTicketsByTakeoffTime(@Param("takeoffTime") double takeoffTime);


}
