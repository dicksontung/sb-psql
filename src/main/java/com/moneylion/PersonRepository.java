package com.moneylion;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, String> {

    @Query(value = "SELECT * FROM persons p WHERE p.name = :name ", nativeQuery = true)
    Collection<Person> findByNameNative(@Param("name") String name);
}
