package com.festival.app.controller;

import com.festival.app.model.Festival;
import com.festival.app.repository.FestivalRepository;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/festival")
public class FestivalController {

    private FestivalRepository repository;

    @Autowired
    public FestivalController(FestivalRepository repository){

        this.repository = repository;
    }

    
    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Festival> getAll(){
        var festivals = repository.findAll();

        if(festivals.size() > 0){
            return festivals;
        }
        return null;

    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String add(@RequestBody Festival festival){
        repository.save(festival);
        System.out.println(festival);
        return "saved";
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Festival festival){
        repository.save(festival);
    }


    @DeleteMapping(value= "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
       repository.deleteById(id);
    }

}
