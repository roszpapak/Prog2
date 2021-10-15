package com.roszpapak.timereserve.reservation;


import com.roszpapak.timereserve.business.Business;
import com.roszpapak.timereserve.business.BusinessNotFoundException;
import com.roszpapak.timereserve.business.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationService {

    private static final LocalTime START_TIME = LocalTime.of(7,0);
    private static final LocalTime END_TIME = LocalTime.of(23,0);

    private final Set<Pair<LocalTime,LocalTime>> quarterReservations;
    private final Set<Pair<LocalTime,LocalTime>> halfReservations;
    private final Set<Pair<LocalTime,LocalTime>> hourReservations;
    private final Set<Pair<LocalTime,LocalTime>> twoHourReservations;

    @Autowired
    public ReservationService (BusinessRepository businessRepository){

        this.businessRepository = businessRepository;
        quarterReservations = initializeReservations(15);
        halfReservations = initializeReservations(30);
        hourReservations = initializeReservations(60);
        twoHourReservations = initializeReservations(120);

    }

    private Set<Pair<LocalTime,LocalTime>> initializeReservations(long minute){

        Set<Pair<LocalTime,LocalTime>> timeReservations = new HashSet<>();

        LocalTime startTime = START_TIME;
        LocalTime endTime = null;

        while(!END_TIME.equals(endTime)){

            if(startTime.until(END_TIME, ChronoUnit.MINUTES) >= minute) {
                endTime = startTime.plusMinutes(minute);
            } else {
                break;
            }

            timeReservations.add(Pair.of(startTime, endTime));
            startTime = endTime;

        }

        return timeReservations;

    }

    private BusinessRepository businessRepository;
    public Set<Pair<LocalTime,LocalTime>> getFreeReservationsForDay(Long businessId , LocalDateTime date) throws BusinessNotFoundException{

        Set<Pair<LocalTime,LocalTime>> reservations = new HashSet<>();
        Optional<Business> optionalBusiness = businessRepository.findById(businessId);
        Business business = optionalBusiness.orElseThrow(()-> new BusinessNotFoundException(String.format("Business with Id : %s not found", businessId)));

        int interval = business.getTimeInterval();
        LocalTime startTime = business.getStartTime();
        LocalTime endTime = business.getEndTime();

        Set<Pair<LocalTime,LocalTime>> allReservations = new HashSet<>();

        switch (interval){

            case 15:
                allReservations = quarterReservations;
                break;

            case 30:
                allReservations = halfReservations;
                break;

            case 60:
                allReservations = hourReservations;
                break;

            case 120:
                allReservations = twoHourReservations;
                break;
        }

        Set<Pair<LocalTime,LocalTime>> possibleReservations = new HashSet<>();

        for(Pair<LocalTime,LocalTime> actual : allReservations){
            if((actual.getFirst().equals(startTime) || actual.getFirst().isAfter(startTime)) &&
                    (actual.getSecond().equals(endTime) || actual.getSecond().isBefore(endTime))){

                possibleReservations.add(actual);
            }

        }


        return reservations;
    }

}
