package com.Planner.Planner.Service.Client;

import com.Planner.Planner.Entity.Road;

import java.util.List;

public interface ITripService {


    public List<String> findShortestPath(Long cityStartId, Long cityEndId)  ;



}
