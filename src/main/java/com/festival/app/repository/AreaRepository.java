package com.festival.app.repository;

import com.festival.app.model.Area;
import com.festival.app.model.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Long> {
}
