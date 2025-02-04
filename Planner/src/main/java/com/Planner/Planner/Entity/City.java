package com.Planner.Planner.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double latitude;
    private Double longitude;

//    @OneToMany(mappedBy = "cityFrom")
//    @JsonManagedReference
//    private List<Road> roadsFrom;
//
//    @OneToMany(mappedBy = "cityTo")
//    @JsonManagedReference
//    private List<Road>  roadsTo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

//    public List<Road> getRoadsFrom() {
//        return roadsFrom;
//    }
//
//    public void setRoadsFrom(List<Road> roadsFrom) {
//        this.roadsFrom = roadsFrom;
//    }
//
//    public List<Road> getRoadsTo() {
//        return roadsTo;
//    }
//
//    public void setRoadsTo(List<Road> roadsTo) {
//        this.roadsTo = roadsTo;
//    }
}
