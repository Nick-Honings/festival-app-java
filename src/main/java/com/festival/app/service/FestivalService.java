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

    public Festival addEvent(Festival festival)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        ApplicationUser user = userDetailsService.findByUserName(username);
        festival.setUser(user);

        List<Area> areas = new ArrayList<Area>(festival.getAreas());

        for (Area area : areas) {
            festival.addArea(area);


            List<Artist> artists = new ArrayList<Artist>(area.getArtists());

            for(Artist artist : artists){
                area.addArtist(artist);
                artistRepo.save(artist);
            }
            areaRepo.save(area);
        }
        
        return festivalRepo.save(festival);
    }

    public List<Festival> getAll(){
        return festivalRepo.findAll();
    }

    public List<Festival> findByUserId(Long id){
        return festivalRepo.findByUserId(id);
    }

    public Festival save(Festival festival){
        return festivalRepo.save(festival);
    }

    public void deleteById(Long id){
        festivalRepo.deleteById(id);
    }

    public Optional<Festival> getById(Long id){
        return festivalRepo.findById(id);
    }

    public Festival getAllInfoById(Long id){ return festivalRepo.getAllInfo(id);}

    public Festival setAttendance(Long id, Attendance attendance) {
        return festivalRepo.setAttendance(id, attendance);
    }
}
