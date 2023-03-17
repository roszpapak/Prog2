package com.roszpapak.timereserve.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByReservationDate(LocalDate date);


    @Query(value = "SELECT * FROM Reservation where BUSINESS_ID = ?1 and reservation_date > CAST(CURRENT_TIMESTAMP - INTERVAL '1 day' AS DATE) ORDER BY reservation_date", nativeQuery = true)
    List<Reservation> findByBusinessId(Long id);

    @Query(value = "SELECT * FROM Reservation where USER_ID = ?1 and reservation_date > CAST(CURRENT_TIMESTAMP - INTERVAL '1 day' AS DATE) ORDER BY reservation_date", nativeQuery = true)
    List<Reservation> findByUserId(Long id);
}
