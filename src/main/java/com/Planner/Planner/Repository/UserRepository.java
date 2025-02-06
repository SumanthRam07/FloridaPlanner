package com.Planner.Planner.Repository;

import com.Planner.Planner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Long> {

}
