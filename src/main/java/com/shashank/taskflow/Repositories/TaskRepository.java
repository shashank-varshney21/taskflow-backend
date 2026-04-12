package com.shashank.taskflow.Repositories;

import com.shashank.taskflow.Entites.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, String> {
}
