package com.repeta.airport.ticket;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ticket_log", path = "ticket_log")
public interface TicketLogRepository extends PagingAndSortingRepository<TicketLog,Integer> {
}
