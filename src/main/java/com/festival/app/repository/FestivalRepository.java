package com.festival.app.repository;

import com.festival.app.model.Attendance;
import com.festival.app.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface FestivalRepository extends JpaRepository<Event, Long> {

    @Query("SELECT f FROM Event f WHERE f.user.id = ?1")
    List<Event> findByUserId(Long id);

    @Query("SELECT f FROM Event f INNER JOIN Area a ON f.id = a.festival.id WHERE f.id = ?1 ")
    Event getAllInfo(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Event f SET f.attendance = ?2 WHERE f.id = ?1")
    Event setAttendance(Long id, Attendance attendance);
}
