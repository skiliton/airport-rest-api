package com.repeta.airport.passenger;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "passenger_check", path = "passenger_check")
public interface PassengerCheckRepository extends PagingAndSortingRepository<PassengerCheck,Integer> {
}
