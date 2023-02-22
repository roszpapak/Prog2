package com.roszpapak.timereserve.holiday;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    public List<Holiday> findAll() {
        return holidayRepository.findAll();
    }

    public void deleteById(Long id) {
        holidayRepository.deleteById(id);
    }

}
