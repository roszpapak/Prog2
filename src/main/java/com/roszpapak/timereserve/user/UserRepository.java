package com.roszpapak.timereserve.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "select * from User u where u.FIRST_NAME like %:keyword% or u.LAST_NAME like %:keyword%" , nativeQuery = true )
    List<User> findByKeyWord(@Param("keyword") String keyword);

}
