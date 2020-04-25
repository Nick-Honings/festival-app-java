package com.festival.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "area")
@Data
@NoArgsConstructor
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NonNull
    private String name;

    private String genre;


    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL)
    private List<Artist> artists = new ArrayList<Artist>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "festival_id")
    private Festival festival;


    public void addArtist(Artist artist){
        this.artists.add(artist);
        artist.setArea(this);
    }

    public List<Artist> getArtists(){
        return this.artists;
    }


    public void setFestival(Festival festival) {
        this.festival = festival;
    }
}
