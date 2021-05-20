package com.repeta.airport.employee;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "brigades")
public interface BrigadeRepository extends PagingAndSortingRepository<Brigade,Integer> {
}
