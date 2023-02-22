package com.roszpapak.timereserve.holiday;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HolidayController {

    @Autowired
    private HolidayService holidayService;


    @PostMapping("/deleteHoliday/{id}")
    @ResponseBody
    public void deleteHoliday(@PathVariable Long id) {
        holidayService.deleteById(id);
    }
}
