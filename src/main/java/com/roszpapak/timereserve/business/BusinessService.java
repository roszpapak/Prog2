package com.roszpapak.timereserve.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessService {

    @Autowired
    BusinessRepository businessRepository;

    public List<Business> listByName(String keyword) {
        return (List<Business>) businessRepository.findByName(keyword);
    }

    public List<Business> listAll() {

        return (List<Business>) businessRepository.findAll();
    }

    public Business listById(Long id) {
        return businessRepository.findById(id).orElseThrow(() -> new IllegalStateException("Business with id :" + id + " not found"));
    }
}
