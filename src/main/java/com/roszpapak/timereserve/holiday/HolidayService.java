package com.roszpapak.timereserve.holiday;


import com.roszpapak.timereserve.DTO.HolidayDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    public List<Holiday> listHolidays(Long id) {
        return holidayRepository.findByBusinessId(id);
    }

    public void takeHoliday(HolidayDTO holidayDTO, Long id) {

        holidayRepository.save(new Holiday(holidayDTO.getStart(), holidayDTO.getEnd(), id));
    }

    public void deleteById(Long id) {
        holidayRepository.deleteById(id);
    }

}
