package com.festival.app.controller;

import com.festival.app.model.Area;
import com.festival.app.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
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
    public ResponseEntity<List<Area>> getById(@PathVariable Long id){

        List<Area> areas = repository.findByFestivalId(id);
        if(areas.size() != 0) {
            return new ResponseEntity<>(areas, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/add/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> add(@RequestBody Area area){

        try {
            repository.save(area);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Area cannot be null", HttpStatus.OK);
        }

    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody Area area){
        try {
            repository.save(area);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Area cannot be null", HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<? extends Object> delete(@PathVariable Long id){
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Area cannot be null", HttpStatus.OK);
        }
    }

}
