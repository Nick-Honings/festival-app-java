package com.festival.app.service;

import com.festival.app.model.Area;
import com.festival.app.model.Artist;
import com.festival.app.model.Festival;
import com.festival.app.repository.AreaRepository;
import com.festival.app.repository.ArtistRepository;
import com.festival.app.repository.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FestivalService {

    private FestivalRepository festivalRepo;
    private AreaRepository areaRepo;
    private ArtistRepository artistRepo;

    @Autowired
    public FestivalService(FestivalRepository festivalRepo, AreaRepository areaRepo, ArtistRepository artistRepo){

        this.festivalRepo = festivalRepo;
        this.areaRepo = areaRepo;
        this.artistRepo = artistRepo;
    }

    public void addEvent(Festival festival)
    {
        System.out.println(festival.getAreas());
        
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
        
        festivalRepo.save(festival);
    }

    public List<Festival> getAll(){
        return festivalRepo.findAll();
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

}
