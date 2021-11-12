package com.roszpapak.timereserve.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM Reservation where BUSINESS_ID = ?1 and FORMATDATETIME(START_TIME,'yyyy-MM-dd') = ?2", nativeQuery = true)
    List<Reservation> getReservationsForBusinessOnDay(Long businessId, LocalDate date);

}
