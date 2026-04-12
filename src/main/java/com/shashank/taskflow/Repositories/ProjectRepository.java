package com.shashank.taskflow.Repositories;

import com.shashank.taskflow.Entites.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, String> {
}
