package com.shashank.taskflow.Services;

import com.shashank.taskflow.Dtos.TaskRequestDto;
import com.shashank.taskflow.Dtos.TaskResponseDto;
import com.shashank.taskflow.Dtos.TaskUpdateRequestDto;
import com.shashank.taskflow.Entites.Project;
import com.shashank.taskflow.Entites.Task;
import com.shashank.taskflow.Entites.User;
import com.shashank.taskflow.Enums.StatusEnum;
import com.shashank.taskflow.Repositories.ProjectRepository;
import com.shashank.taskflow.Repositories.TaskRepository;
import com.shashank.taskflow.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {

    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public ResponseEntity<List<TaskResponseDto>> getTasks(String id, StatusEnum status, String assignee) {
        Project project = projectRepository.findById(id).orElseThrow();
        if (status!=null && assignee!=null) {
            User assignedUser = userRepository.findById(assignee).orElseThrow();
            List<Task> taskList = taskRepository.findAllByProjectAndStatusAndUser(project, status, assignedUser);
            log.info("taskList: {}", taskList);
            return ResponseEntity.ok(taskList.stream().map(task -> {
                return modelMapper.map(task, TaskResponseDto.class);
            }).toList());
        }
        else if (status != null) {
            List<Task> taskList = taskRepository.findAllByProjectAndStatus(project, status);
            log.info("taskList: {}", taskList);
            return ResponseEntity.ok(taskList.stream().map(task -> {
                return modelMapper.map(task, TaskResponseDto.class);
            }).toList());
        }
        else if (assignee!=null) {
            User assignedUser = userRepository.findById(assignee).orElseThrow();
            List<Task> taskList = taskRepository.findAllByProjectAndUser(project, assignedUser);
            log.info("taskList: {}", taskList);
            return ResponseEntity.ok(taskList.stream().map(task -> {
                return modelMapper.map(task, TaskResponseDto.class);
            }).toList());
        }
        else {
            List<Task> taskList = taskRepository.findAllByProject(project);
            log.info("taskList: {}", taskList);
            List<TaskResponseDto> response = taskList.stream().map(task -> {
                return modelMapper.map(task, TaskResponseDto.class);
            }).toList();
            return ResponseEntity.ok(response);
        }
    }

    public ResponseEntity<String> assignTask(String taskId, String userId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        task.setUser(user);

        taskRepository.save(task);

        return ResponseEntity.ok("SUCCESS");
    }

    public ResponseEntity<TaskResponseDto> createTask(String id, TaskRequestDto taskRequestDto) {
        Task task = modelMapper.map(taskRequestDto, Task.class);
        Project project = projectRepository.findById(id).orElseThrow();

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = null;
//        if (authentication != null && authentication.isAuthenticated()) {
//            user = (User) authentication.getPrincipal(); // your entity
//        }

        task.setProject(project);
        task.setUser(null);

        taskRepository.save(task);

        return ResponseEntity.ok(modelMapper.map(task, TaskResponseDto.class));
    }

    public ResponseEntity<String> patchTask(String id, TaskUpdateRequestDto taskUpdateRequestDto) {
        Task task = taskRepository.findById(id).orElseThrow();
        String projectOwnerId = task.getProject().getUser().getId();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal(); // your entity
            if(!user.getId().equals(projectOwnerId)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
        }
        if(taskUpdateRequestDto.getTitle() != null) {
            task.setTitle(taskUpdateRequestDto.getTitle());
        }
        if(taskUpdateRequestDto.getDescription() != null) {
            task.setDescription(taskUpdateRequestDto.getDescription());
        }
        if(taskUpdateRequestDto.getStatus() != null){
            task.setStatus(taskUpdateRequestDto.getStatus());
        }
        if(taskUpdateRequestDto.getPriority() != null) {
            task.setPriority(taskUpdateRequestDto.getPriority());
        }
        if(taskUpdateRequestDto.getDue_date() != null) {
            task.setDue_date(taskUpdateRequestDto.getDue_date());
        }
        if(taskUpdateRequestDto.getAssignee() != null) {
            User assignedUser = userRepository.findById(taskUpdateRequestDto.getAssignee()).orElseThrow();
            task.setUser(assignedUser);
        }

        taskRepository.save(task);
        return ResponseEntity.ok("SUCCESS");
    }

    public ResponseEntity<String> delete(String id) {
        Task task = taskRepository.findById(id).orElseThrow();
        String projectOwnerId = task.getProject().getUser().getId();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal(); // your entity
            if(!user.getId().equals(projectOwnerId)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }
        }
        taskRepository.delete(task);
        return ResponseEntity.ok("SUCCESS");
    }

}
