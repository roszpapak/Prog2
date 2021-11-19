package com.roszpapak.timereserve.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    @Query(value = "select * from BUSINESS b where lower(b.NAME) like %?1%", nativeQuery = true)
    List<Business> findByName(String keyword);
}
