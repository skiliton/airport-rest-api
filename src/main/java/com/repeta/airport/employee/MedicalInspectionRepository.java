package com.repeta.airport.employee;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "medicalInspections")
public interface MedicalInspectionRepository extends PagingAndSortingRepository<MedicalInspection,Integer> {
}
