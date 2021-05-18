package com.repeta.airport.plane;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "technical_inspection", path = "technical_inspection")
public interface TechnicalInspectionRepository extends PagingAndSortingRepository<TechnicalInspection,Integer> {
}
