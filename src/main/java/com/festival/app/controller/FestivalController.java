package com.festival.app.controller;

import com.festival.app.model.Festival;
import com.festival.app.model.Area;
import com.festival.app.repository.FestivalRepository;
import com.festival.app.service.FestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/festival")
public class FestivalController {

    private FestivalService service;

    @Autowired
    public FestivalController(FestivalService service){

        this.service = service;
    }


    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Iterable<Festival> getAll(){
        var festivals = service.getAll();

        if(festivals.size() > 0){
            return festivals;
        }
        return null;
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String add(@RequestBody Festival festival){

        service.addEvent(festival);

        return "saved";
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Festival festival){
        service.save(festival);
    }


    @DeleteMapping(value= "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){
       service.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    Optional<Festival> getById(@PathVariable Long id){
        return service.getById(id);
    }

}
