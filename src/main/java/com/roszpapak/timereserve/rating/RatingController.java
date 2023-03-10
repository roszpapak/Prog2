package com.roszpapak.timereserve.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/rating/find/{businessId}")
    @ResponseBody
    public List<Rating> getRatingById(@PathVariable Long businessId) {
        return ratingService.getRatingById(businessId);
    }

    @PostMapping("/rating/save")
    @ResponseBody
    public void saveRate(@RequestBody Rating rating) {
        ratingService.save(rating);
    }
}
