package com.festival.app.service;

import com.festival.app.repository.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FestivalService {

    private FestivalRepository repository;

    @Autowired
    public FestivalService(FestivalRepository repository){

        this.repository = repository;
    }
}
