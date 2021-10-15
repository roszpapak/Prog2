package com.roszpapak.timereserve.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    //@Query("SELECT * FROM Reservations where businessid = ?1 and businessdate = ?2 ")
    //List<Reservation> getReservationsForBusinessOnDay(Long businessId, LocalDate date);

}
