package com.festival.app.controller;

import com.festival.app.model.Area;
import com.festival.app.model.Artist;
import com.festival.app.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private ArtistRepository repository;

    @Autowired
    public ArtistController(ArtistRepository repository){
        this.repository = repository;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Artist> getById(@PathVariable Long id){
        return repository.findByAreaId(id);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void add(@RequestBody Artist artist){
        repository.save(artist);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Artist artist){
        repository.save(artist);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}
