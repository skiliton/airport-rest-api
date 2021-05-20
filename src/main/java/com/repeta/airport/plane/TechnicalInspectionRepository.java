package com.repeta.airport.plane;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "technicalInspections")
public interface TechnicalInspectionRepository extends PagingAndSortingRepository<TechnicalInspection,Integer> {
}
