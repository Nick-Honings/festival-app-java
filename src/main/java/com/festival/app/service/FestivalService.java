package com.festival.app.service;

import com.festival.app.model.*;
import com.festival.app.repository.AreaRepository;
import com.festival.app.repository.ArtistRepository;
import com.festival.app.repository.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FestivalService {

    private FestivalRepository festivalRepo;
    private AreaRepository areaRepo;
    private ArtistRepository artistRepo;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public FestivalService(FestivalRepository festivalRepo, AreaRepository areaRepo, ArtistRepository artistRepo, UserDetailsServiceImpl userDetailsService){

        this.festivalRepo = festivalRepo;
        this.areaRepo = areaRepo;
        this.artistRepo = artistRepo;
        this.userDetailsService = userDetailsService;
    }

    public Event addEvent(Event event)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ApplicationUser user = userDetailsService.findByUserName(username);
        event.setUser(user);

        List<Area> areas = new ArrayList<Area>(event.getAreas());

        for (Area area : areas) {
            event.addArea(area);


            List<Artist> artists = new ArrayList<Artist>(area.getArtists());

            for(Artist artist : artists){
                area.addArtist(artist);
                artistRepo.save(artist);
            }
            areaRepo.save(area);
        }
        
        Event newEvent = festivalRepo.save(event);
        //System.out.println(newFestival);
        return newEvent;
    }

    public List<Event> getAll(){
        return festivalRepo.findAll();
    }

    public List<Event> findByUserId(Long id){
        return festivalRepo.findByUserId(id);
    }

    public Event save(Event event){
        return festivalRepo.save(event);
    }

    public void deleteById(Long id){
        festivalRepo.deleteById(id);
    }

    public Optional<Event> getById(Long id){
        return festivalRepo.findById(id);
    }

    public Event getAllInfoById(Long id){ return festivalRepo.getAllInfo(id);}

    public Event setAttendance(Long id, Attendance attendance) {
        return festivalRepo.setAttendance(id, attendance);
    }
}
