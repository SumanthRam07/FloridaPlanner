package com.Planner.Planner.Repository;

import com.Planner.Planner.Entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
