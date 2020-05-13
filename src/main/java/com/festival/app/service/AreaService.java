package com.festival.app.service;

import com.festival.app.model.Area;
import com.festival.app.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AreaService {

    private AreaRepository repository;

    @Autowired
    public AreaService(AreaRepository repository){

        this.repository = repository;
    }

}
