package com.shashank.taskflow.Services;

import com.shashank.taskflow.Dtos.ProjectRequestDto;
import com.shashank.taskflow.Dtos.ProjectResponseDto;
import com.shashank.taskflow.Entites.Project;
import com.shashank.taskflow.Entites.User;
import com.shashank.taskflow.Repositories.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;

        public ResponseEntity<ProjectResponseDto> createProject(ProjectRequestDto projectRequestDto) {
            Project project = modelMapper.map(projectRequestDto, Project.class);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                User user = (User) authentication.getPrincipal(); // your entity
                project.setUser(user);
            }
            projectRepository.save(project);
            return ResponseEntity.ok(new ProjectResponseDto(project.getId()));
    }
}
