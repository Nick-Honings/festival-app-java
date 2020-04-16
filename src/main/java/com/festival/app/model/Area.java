package com.festival.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "area")
@Data
@NoArgsConstructor
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NonNull
    private String name;

    private String genre;


    @JsonIgnore
    @JsonBackReference
    @OneToMany( mappedBy = "area", cascade = CascadeType.ALL)
    private Set<Artist> artists;

    @ManyToOne
    @JoinColumn(name = "festival_id", referencedColumnName = "id", nullable = false)
    private Festival festival;

    public void setFestival(Festival festival){
        this.festival = festival;
    }


}
