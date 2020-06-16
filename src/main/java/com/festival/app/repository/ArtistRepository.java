package com.festival.app.repository;

import com.festival.app.model.Area;
import com.festival.app.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query("SELECT a FROM Artist a WHERE a.area.id = :id")
    List<Artist> findByAreaId(@Param("id") Long id);

}
