package com.repeta.airport.plane;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource(collectionResourceRel = "planes")
public interface PlaneRepository extends PagingAndSortingRepository<Plane,Integer> {

    public static final String AIRPORT = "Kyiv International Airport (Zhuliany)";

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM plane p " +
                    "WHERE 'TAKEOFF' <> (SELECT state" +
                    "                        FROM plane_log pl" +
                    "                        WHERE pl.plane_id=p.id" +
                    "                          AND pl.time<=:time" +
                    "                        ORDER BY pl.time DESC" +
                    "                        LIMIT 1)" +
                    "AND :airportId = (SELECT airport_id" +
                    "                   FROM plane_log pl" +
                    "                   WHERE pl.plane_id=p.id" +
                    "                     AND pl.time<=:time" +
                    "                   ORDER BY pl.time DESC" +
                    "                   LIMIT 1)" 
    )
    public Collection<Plane> findAllAssignedPlanes(@Param("airportId") String airportId, @Param("time") String time);

    @Query(
            nativeQuery = true,
            value = "SELECT p.id, p.type, p.status, first_flight " +
                    "FROM plane p INNER JOIN plane_log pl on p.id = pl.plane_id " +
                    "WHERE pl.state = 'LANDED' " +
                    "GROUP BY p.id, p.type, p.status, first_flight " +
                    "HAVING count(pl.state) BETWEEN :flightsCount1 AND :flightsCount2"
    )
    public Collection<Plane> findAllPlanesByFinishedFlights(@Param("flightsCount1") int flightsCount1, @Param("flightsCount2") int flightsCount2);
}
