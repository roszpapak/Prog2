package com.roszpapak.timereserve.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query(value = "SELECT * FROM Rating where to_Id = ?1", nativeQuery = true)
    List<Rating> findByToId(Long id);

    Optional<Rating> findByToIdAndFromId(Long toId, Long fromId);

    @Query(value = "SELECT AVG(value) FROM rating  where to_Id = ?1", nativeQuery = true)
    Optional<Double> getAvgRatingById(Long businessId);
}
