package com.roszpapak.timereserve.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    @Query(value = "select * from BUSINESS b where b.NAME like %:keyword%", nativeQuery = true)
    List<Business> findByName(@Param("keyword") String keyword);
}
