package com.roszpapak.timereserve.business;

import com.roszpapak.timereserve.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

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
}
