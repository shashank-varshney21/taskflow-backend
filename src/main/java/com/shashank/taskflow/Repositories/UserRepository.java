package com.shashank.taskflow.Repositories;

import com.shashank.taskflow.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
