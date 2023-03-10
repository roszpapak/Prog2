package com.roszpapak.timereserve.business;

import com.roszpapak.timereserve.DTO.HolidayRequestDTO;
import com.roszpapak.timereserve.holiday.Holiday;
import com.roszpapak.timereserve.holiday.HolidayRepository;
import com.roszpapak.timereserve.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    public void editBusiness(BusinessEditRequest businessEditRequest, User user) {


        System.out.println("Business request:" + businessEditRequest.toString());

        user.getBusiness().setName(businessEditRequest.getName());
        user.getBusiness().setPNumber(businessEditRequest.getPhone());
        user.getBusiness().setAddress(businessEditRequest.getAddress());
        user.getBusiness().setStartTime(businessEditRequest.getStartTime());
        user.getBusiness().setEndTime(businessEditRequest.getEndTime());


        businessRepository.save(user.getBusiness());
    }

    public List<Business> listByName(String keyword) {
        return (List<Business>) businessRepository.findByName(keyword);
    }

    public List<Business> listAll() {

        return (List<Business>) businessRepository.findAll();
    }

    public Business listById(Long id) {
        return businessRepository.findById(id).orElseThrow(() -> new IllegalStateException("Business with id :" + id + " not found"));
    }

    public Business getByUserId(Long id) {
        return businessRepository.findByUserId(id);
    }

    public void changeProfilePicutre(MultipartFile file, User user) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName.contains("..")) {
            System.out.println("Not a valid file");
        }

        try {
            user.getBusiness().setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        businessRepository.save(user.getBusiness());
    }

    public void takeHoliday(HolidayRequestDTO holidayRequestDTO, User user) {

        holidayRepository.save(new Holiday(holidayRequestDTO.getStart(), holidayRequestDTO.getEnd(), user.getBusiness().getId()));
    }

    public List<Holiday> listHolidays(Long id) {
        return holidayRepository.findByBusinessId(id);
    }
}
