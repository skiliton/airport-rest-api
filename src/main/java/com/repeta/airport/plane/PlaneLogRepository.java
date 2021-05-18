package com.repeta.airport.plane;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "plane_log", path = "plane_log")
public interface PlaneLogRepository extends PagingAndSortingRepository<PlaneLog,Integer> {
}
