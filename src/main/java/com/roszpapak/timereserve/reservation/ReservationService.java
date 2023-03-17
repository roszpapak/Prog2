package com.roszpapak.timereserve.reservation;


import com.roszpapak.timereserve.business.Business;
import com.roszpapak.timereserve.business.BusinessNotFoundException;
import com.roszpapak.timereserve.business.BusinessRepository;
import com.roszpapak.timereserve.holiday.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationService {


    private BusinessRepository businessRepository;
    private ReservationRepository reservationRepository;

    private HolidayRepository holidayRepository;

    @Autowired
    public ReservationService(BusinessRepository businessRepository, ReservationRepository reservationRepository, HolidayRepository holidayRepository) {

        this.reservationRepository = reservationRepository;
        this.businessRepository = businessRepository;
        this.holidayRepository = holidayRepository;

    }

    //Calculating Reservations
    private Set<Pair<LocalTime, LocalTime>> initializeReservations(long minute, LocalTime businessStartTime, LocalTime businessEndTime) {

        Set<Pair<LocalTime, LocalTime>> timeReservations = new LinkedHashSet<>();

        LocalTime startTime = businessStartTime;
        LocalTime endTime = null;

        while (!businessEndTime.equals(endTime)) {

            if (startTime.until(businessEndTime, ChronoUnit.MINUTES) >= minute) {
                endTime = startTime.plusMinutes(minute);
            } else {
                break;
            }

            timeReservations.add(Pair.of(startTime, endTime));
            startTime = endTime;

        }

        return timeReservations;

    }

    //Creating Reservations

    public Set<Pair<LocalTime, LocalTime>> getFreeReservationsForDay(Long businessId, LocalDate date) throws BusinessNotFoundException {

        Set<Pair<LocalTime, LocalTime>> possibleReservations = new LinkedHashSet<>();

        if (!holidayRepository.findByBusinessIdAndDate(businessId, date).isEmpty()) {
            return possibleReservations;
        }

        Optional<Business> optionalBusiness = businessRepository.findById(businessId);
        Business business = optionalBusiness.orElseThrow(() -> new BusinessNotFoundException(String.format("Business with Id : %s not found", businessId)));

        int interval = business.getTimeInterval();
        LocalTime startTime = business.getStartTime();
        LocalTime endTime = business.getEndTime();

        Set<Pair<LocalTime, LocalTime>> allReservations = initializeReservations(interval, startTime, endTime);

        List<Reservation> reservationsForBusinessOnDay = reservationRepository.findByReservationDate(date);


        for (Pair<LocalTime, LocalTime> actual : allReservations) {
            if (isFree(actual, reservationsForBusinessOnDay)) {
                possibleReservations.add(actual);
            }
        }

        return possibleReservations;
    }

    //Checking if reservation is Free
    private boolean isFree(Pair<LocalTime, LocalTime> actual, List<Reservation> possibleReservations) {
        for (Reservation currentReservation : possibleReservations) {
            if (actual.getFirst().equals(currentReservation.getStartTime())) {
                return false;
            }
        }
        return true;
    }


    public Object getByBusinessId(Long id) {
        return reservationRepository.findByBusinessId(id);
    }

    public List<Reservation> getByUserId(Long id) {
        return reservationRepository.findByUserId(id);
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
