package com.repeta.airport.plane;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource(collectionResourceRel = "plane", path = "plane")
public interface PlaneRepository extends PagingAndSortingRepository<Plane,Integer> {

    public static final String AIRPORT = "Kyiv International Airport (Zhuliany)";

    @Query(
            nativeQuery = true,
            value = "SELECT * " +
                    "FROM plane p " +
                    "WHERE 'TAKEOFF' <> (SELECT state" +
                    "                        FROM plane_log pl" +
                    "                        WHERE pl.plane_id=p.id" +
                    "                          AND  pl.time<=:time" +
                    "                        ORDER BY pl.time DESC" +
                    "                        LIMIT 1)" +
                    "AND :airportId = (SELECT airport_id" +
                    "                   FROM plane_log pl" +
                    "                   WHERE pl.plane_id=p.id" +
                    "                     AND  pl.time<=:time" +
                    "                   ORDER BY pl.time DESC" +
                    "                   LIMIT 1);"
    )
    public Collection<Plane> findAllAssignedPlanes(@Param("airportId") String airportId, @Param("time") String time);


}
