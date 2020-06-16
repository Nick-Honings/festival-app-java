package com.festival.app.controller;

import com.festival.app.model.Attendance;
import com.festival.app.model.Festival;
import com.festival.app.model.Area;
import com.festival.app.repository.FestivalRepository;
import com.festival.app.service.FestivalService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<List<Festival>> getAll(){
        var festivals = service.getAll();

        if(festivals.size() > 0){
            return new ResponseEntity<>(festivals, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> add(@RequestBody Festival festival){

        try {
            Festival f = service.addEvent(festival);
            return new ResponseEntity<>(festival, HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Festival cannot be null", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/all/{id}")
    public ResponseEntity<?> getAllByUserId(@PathVariable Long id){

        List<Festival> festivals = service.findByUserId(id);
        if(festivals.size() != 0) {
            return new ResponseEntity<>(festivals, HttpStatus.OK);
        }

        return new ResponseEntity<>("No festivals found for user with id: " + id, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody Festival festival){
        try {
            Festival f = service.save(festival);
            return new ResponseEntity<>(f, HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Festival cannot be null", HttpStatus.OK);
        }
    }


    @DeleteMapping(value= "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Festival> getById(@PathVariable Long id) {

        Optional<Festival> f = service.getById(id);
        if(f.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(f.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/info/all/{id}")
    public ResponseEntity<Festival> getAllInfoById(@PathVariable Long id) {

        return new ResponseEntity<>(service.getAllInfoById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/attendance")
    public ResponseEntity<Festival> setAttendance(@PathVariable Long id, @RequestBody JSONObject attendance)
    {
        Festival f = service.setAttendance(id, Attendance.valueOf(attendance.getAsString("attendance")));
        return new ResponseEntity<Festival>(f, HttpStatus.OK);
    }
}
