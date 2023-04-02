package com.roszpapak.timereserve.holiday;


import com.roszpapak.timereserve.DTO.HolidayDTO;
import com.roszpapak.timereserve.user.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class HolidayController {

    @Autowired
    private HolidayService holidayService;


    @PostMapping("/deleteHoliday/{id}")
    @ResponseBody
    public void deleteHoliday(@PathVariable Long id) {
        holidayService.deleteById(id);
    }

    @PostMapping("/takeHoliday")
    @ResponseBody
    public void takeHoliday(@RequestBody HolidayDTO holidayDTO,
                            @AuthenticationPrincipal User user) {
        holidayService.takeHoliday(holidayDTO, user.getBusiness().getId());
    }
}
