package com.repeta.airport.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "users")
public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> findByUsername(String username);

}
