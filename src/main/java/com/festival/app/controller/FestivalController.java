package com.festival.app.controller;

import com.festival.app.model.Attendance;
import com.festival.app.model.Event;
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
    public ResponseEntity<List<Event>> getAll(){
        var festivals = service.getAll();

        if(festivals.size() > 0){
            return new ResponseEntity<>(festivals, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> add(@RequestBody Event event){

        try {
            Event f = service.addEvent(event);
            return new ResponseEntity<>(event, HttpStatus.OK);
        }
        catch (IllegalArgumentException ex) {
            return new ResponseEntity<>("Festival cannot be null", HttpStatus.BAD_REQUEST);
        }
    }

    // Todo: change to /all/user/{id} also in frontend.
    @GetMapping(path = "/all/{id}")
    public ResponseEntity<?> getAllByUserId(@PathVariable Long id){

        List<Event> events = service.findByUserId(id);
        if(events.size() != 0) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        }

        return new ResponseEntity<>("No festivals found for user with id: " + id, HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody Event event){
        try {
            Event f = service.save(event);
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
    public ResponseEntity<Event> getById(@PathVariable Long id) {

        Optional<Event> f = service.getById(id);
        if(f.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(f.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/info/all/{id}")
    public ResponseEntity<Event> getAllInfoById(@PathVariable Long id) {

        return new ResponseEntity<>(service.getAllInfoById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/attendance")
    public ResponseEntity<Event> setAttendance(@PathVariable Long id, @RequestBody JSONObject attendance)
    {
        Event f = service.setAttendance(id, Attendance.valueOf(attendance.getAsString("attendance")));
        return new ResponseEntity<Event>(f, HttpStatus.OK);
    }
}
