package com.roszpapak.timereserve.holiday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Query(value = "SELECT * FROM Holiday where BUSINESS_ID = ?1 and ?2 between start_date and end_date", nativeQuery = true)
    Optional<Holiday> findByBusinessIdAndDate(Long id, LocalDate date);

    @Query(value = "SELECT * FROM Holiday where BUSINESS_ID = ?1 and CURRENT_DATE between start_date and end_date", nativeQuery = true)
    List<Holiday> findByBusinessId(Long id);

}
