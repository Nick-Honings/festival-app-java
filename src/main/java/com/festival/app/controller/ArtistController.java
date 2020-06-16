package com.festival.app.controller;

import com.festival.app.model.Artist;
import com.festival.app.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private ArtistRepository repository;

    @Autowired
    public ArtistController(ArtistRepository repository){
        this.repository = repository;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<Artist>> getById(@PathVariable Long id){
        List<Artist> a = repository.findByAreaId(id);

        if(a.size() != 0) {
            return new ResponseEntity<>(a, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> add(@RequestBody Artist artist)
    {
        try {
            Artist a = repository.save(artist);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Artist cannot be null",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> update(@RequestBody Artist artist){
        try {
            Artist a = repository.save(artist);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Artist cannot be null", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try{
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Id cannot be null", HttpStatus.BAD_REQUEST);
        }
    }
}
