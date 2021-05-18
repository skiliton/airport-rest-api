package com.repeta.airport.flight;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "flight_log", path = "flight_log")
public interface FlightLogRepository extends PagingAndSortingRepository<FlightLog,Integer> {
}
