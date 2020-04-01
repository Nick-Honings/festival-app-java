package com.festival.app.repository;

import com.festival.app.model.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FestivalRepository extends JpaRepository<Festival, Long> {


}
