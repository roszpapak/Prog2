package com.roszpapak.timereserve.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT * FROM Message " +
            "where ((from_id = ?1 and to_id = ?2) or (from_id = ?2 and to_id = ?1))" +
            "order by received_date"
            , nativeQuery = true)
    List<Message> findAllChatMessages(Long from, Long to);

    List<Message> findByFromIdAndToId(Long fromId, Long toId);

    @Query(value = "SELECT from_id FROM Message where to_id = ?1 and seen = false group by from_id"
            , nativeQuery = true)
    List<Long> findByToIdAndSeen(Long id);
}
