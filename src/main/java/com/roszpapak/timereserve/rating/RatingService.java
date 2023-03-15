package com.roszpapak.timereserve.rating;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getRatingById(Long businessId) {
        return ratingRepository.findByToId(businessId);
    }

    public void save(Rating rating) {
        Rating actualRating;
        if (ratingRepository.findByToIdAndFromId(rating.getToId(), rating.getFromId()).isPresent()) {
            actualRating = ratingRepository.findByToIdAndFromId(rating.getToId(), rating.getFromId()).get();
            actualRating.setValue(rating.getValue());
            actualRating.setMessage(rating.getMessage());
            ratingRepository.save(actualRating);
        } else {
            ratingRepository.save(rating);
        }
    }

    public String getAvgRatingById(Long businessId) {
        if (ratingRepository.getAvgRatingById(businessId).isEmpty()) {
            return "1.0";
        }
        return String.valueOf(ratingRepository.getAvgRatingById(businessId).get());

    }
}
