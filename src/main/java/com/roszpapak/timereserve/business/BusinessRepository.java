package com.roszpapak.timereserve.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    @Query(value = "select id from BUSINESS  where lower(NAME) like %?1%", nativeQuery = true)
    List<Long> findIdByName(String keyword);

    @Query(value = "select * from BUSINESS b inner join users u ON b.USER_ID = u.ID where u.ID = ?1 ", nativeQuery = true)
    Optional<Business> findByUserId(Long id);
}
