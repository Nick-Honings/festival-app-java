package com.festival.app.repository;

import com.festival.app.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query("SELECT a FROM Area a WHERE a.festival.id = :id")
    List<Area> findByFestivalId(@Param("id") Long id);
}
