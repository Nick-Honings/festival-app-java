package com.festival.app.service;

import com.festival.app.model.Area;
import com.festival.app.model.Festival;
import com.festival.app.repository.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FestivalService {

    private FestivalRepository repository;

    @Autowired
    public FestivalService(FestivalRepository repository){

        this.repository = repository;
    }

    public void addEvent(Festival festival)
    {
        Set<Area> areas = festival.getAreas();


        for (Area area: areas) {
            area.setFestival(festival);
            festival.addArea(area);
            System.out.println(area);
        }
        repository.save(festival);
    }

    public List<Festival> getAll(){
        return repository.findAll();
    }

    public Festival save(Festival festival){
        return repository.save(festival);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    public Optional<Festival> getById(Long id){
        return repository.findById(id);
    }

}
