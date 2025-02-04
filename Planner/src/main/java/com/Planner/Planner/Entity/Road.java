package com.Planner.Planner.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roads")
public class Road {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_from_id")
    private City cityFrom;

    @ManyToOne
    @JoinColumn(name = "city_to_id")
    private City cityTo;

    private double distance;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public City getCityFrom() { return cityFrom; }
    public void setCityFrom(City cityFrom) { this.cityFrom = cityFrom; }
    public City getCityTo() { return cityTo; }
    public void setCityTo(City cityTo) { this.cityTo = cityTo; }
    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }


}
