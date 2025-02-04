package com.Planner.Planner.Service.Implementations;

import com.Planner.Planner.Controller.FloridaPlannerController;
import com.Planner.Planner.Entity.City;
import com.Planner.Planner.Entity.Road;
import com.Planner.Planner.Repository.CityRepository;
import com.Planner.Planner.Repository.RoadRepository;
import com.Planner.Planner.Repository.TripRepository;
import com.Planner.Planner.Service.Client.ITripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TripService implements ITripService {

    private final CityRepository cityRepository;
    private final RoadRepository roadRepository;
    private final TripRepository tripRepository;

    Logger logger = LoggerFactory.getLogger(TripService.class) ;


    public TripService(CityRepository cityRepository, RoadRepository roadRepository, TripRepository tripRepository) {
        this.cityRepository = cityRepository;
        this.roadRepository = roadRepository;
        this.tripRepository = tripRepository;
    }


    @Override
    public List<String> findShortestPath(Long cityStartId, Long cityEndId) {
        logger.info("Finding shortest path from city ID {} to {}", cityStartId, cityEndId);

        City startCity = cityRepository.findById(cityStartId)
                .orElseThrow(() -> new RuntimeException("Start city not found"));
        City endCity = cityRepository.findById(cityEndId)
                .orElseThrow(() -> new RuntimeException("End city not found"));

        Map<Long, Double> distances = new HashMap<>();
        Map<Long, Road> previousRoad = new HashMap<>();
        PriorityQueue<City> priorityQueue = new PriorityQueue<>(Comparator.comparing(c -> distances.getOrDefault(c.getId(), Double.MAX_VALUE)));

        distances.put(startCity.getId(), 0.0);
        priorityQueue.add(startCity);

        while (!priorityQueue.isEmpty()) {
            City currentCity = priorityQueue.poll();

            for (Road road : roadRepository.findByCityFromId(currentCity.getId())) {
                City neighbor = (road.getCityFrom().equals(currentCity)) ? road.getCityTo() : road.getCityFrom();
                double newDist = distances.get(currentCity.getId()) + road.getDistance();
                double currentNeighborDist = distances.getOrDefault(neighbor.getId(), Double.MAX_VALUE);

                if (newDist < currentNeighborDist) {
                    distances.put(neighbor.getId(), newDist);
                    previousRoad.put(neighbor.getId(), road);
                    priorityQueue.add(neighbor);
                }
            }
        }

        if (!previousRoad.containsKey(endCity.getId())) {
            logger.warn("No path found between {} and {}", startCity.getName(), endCity.getName());
            return new ArrayList<>();
        }

        List<String> path = new ArrayList<>();
        for (Long at = endCity.getId(); at != null && previousRoad.containsKey(at); at = previousRoad.get(at).getCityFrom().getId()) {
            path.add(previousRoad.get(at).getCityFrom().getName() +" -> " + previousRoad.get(at).getCityTo().getName());
        }
        Collections.reverse(path);

        logger.info("Shortest path found with {} roads", path.size());
        return path;
    }





}

