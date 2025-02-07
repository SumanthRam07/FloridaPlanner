package com.Planner.Planner.Controller;


import com.Planner.Planner.Entity.City;
import com.Planner.Planner.Entity.Road;
import com.Planner.Planner.Repository.CityRepository;
import com.Planner.Planner.Repository.RoadRepository;
import com.Planner.Planner.Repository.TripRepository;
import com.Planner.Planner.Service.Implementations.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;



@RestController
@RequestMapping(path = "Planner/Florida/" )
public class FloridaPlannerController {


    private final TripService tripService;

    private final CityRepository cityRepository;
    private final RoadRepository roadRepository;
    private final TripRepository tripRepository;







    Logger logger = LoggerFactory.getLogger(FloridaPlannerController.class) ;

    public FloridaPlannerController(TripService tripService ,CityRepository cityRepository  , RoadRepository roadRepository , TripRepository tripRepository ) {
        this.tripService = tripService;
        this.cityRepository = cityRepository;
        this.roadRepository = roadRepository;
        this.tripRepository = tripRepository;
    }


    @Value("${application1.update}")
    String Welcome ;



    @GetMapping("/shortest-route/welcome")
    public ResponseEntity<String> welcome()
    {

        return ResponseEntity.status(HttpStatus.OK).body(Welcome) ;
    }

    @GetMapping("/shortest-route/{cityStartId}/{cityEndId}")
    public ResponseEntity<?> getShortestRoute(@PathVariable Long cityStartId, @PathVariable Long cityEndId) {
        try {
            logger.info("Received request for shortest route between cities {} and {}", cityStartId, cityEndId);

            List<String> shortestPath = tripService.findShortestPath(cityStartId, cityEndId);

            if (shortestPath.isEmpty()) {
                logger.warn("No path found between cities {} and {}", cityStartId, cityEndId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No route found between the specified cities.");
            }

            logger.info("Shortest path found with {} roads", shortestPath.size());
            return ResponseEntity.ok(shortestPath);
        } catch (RuntimeException e) {
            logger.error("Error finding shortest path: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while finding the shortest route: " + e.getMessage());
        }
    }


    @GetMapping("/shortest-route/test")
    public ResponseEntity<?> getShortestRou1() {



        return ResponseEntity.status(HttpStatus.OK).body("i am wokring");
    }

    @GetMapping("/shortest-route/findCityFrom/{cityID}")
    public ResponseEntity<List<Road>> findCityFrom( @PathVariable Long cityID) {



      List<Road> roadList =   roadRepository.findByCityFromId(cityID) ;

        return ResponseEntity.status(HttpStatus.OK).body(roadList);
    }













}
