package com.repeta.airport.airport;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "airport", path = "airport")
public interface AirportRepository extends PagingAndSortingRepository<Airport,Integer> {
}
