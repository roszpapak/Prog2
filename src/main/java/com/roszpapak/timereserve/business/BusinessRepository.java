package com.roszpapak.timereserve.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    @Query(value = "select * from BUSINESS  where lower(NAME) like %?1%", nativeQuery = true)
    List<Business> findByName(String keyword);

    @Query(value = "select * from BUSINESS b inner join users u ON b.USER_ID = u.ID where u.ID = ?1 ", nativeQuery = true)
    Business findByUserId(Long id);
}
