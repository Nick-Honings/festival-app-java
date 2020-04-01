package com.festival.app.controller;

import com.festival.app.model.Area;
import com.festival.app.model.Festival;
import com.festival.app.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/area")
public class AreaController {

    private AreaRepository repository;

    @Autowired
    public AreaController(AreaRepository repository){
        this.repository = repository;
    }


    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Area> getById(@PathVariable Long id){
        return repository.findAll();
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void add(@RequestBody Area area){
        repository.save(area);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Area area){
        repository.save(area);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }

}
