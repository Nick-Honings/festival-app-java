package com.festival.app.repository;

import com.festival.app.model.Attendance;
import com.festival.app.model.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface FestivalRepository extends JpaRepository<Festival, Long> {

    @Query("SELECT f FROM Festival f WHERE f.user.id = ?1")
    List<Festival> findByUserId(Long id);

    @Query("SELECT f FROM Festival f INNER JOIN Area a ON f.id = a.festival.id WHERE f.id = ?1 ")
    Festival getAllInfo(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Festival f SET f.attendance = ?2 WHERE f.id = ?1")
    Festival setAttendance(Long id, Attendance attendance);
}
