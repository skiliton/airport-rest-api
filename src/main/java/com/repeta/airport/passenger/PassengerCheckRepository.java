package com.repeta.airport.passenger;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "passengerChecks")
public interface PassengerCheckRepository extends PagingAndSortingRepository<PassengerCheck,Integer> {
}
