package com.Planner.Planner.Repository;

import com.Planner.Planner.Entity.City;
import com.Planner.Planner.Entity.Road;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoadRepository extends JpaRepository<Road , Long> {


    List<Road> findByCityFromId(Long cityID);
}
