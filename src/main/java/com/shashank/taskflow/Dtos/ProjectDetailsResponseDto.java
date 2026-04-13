package com.shashank.taskflow.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailsResponseDto {
    private String id;
    private String name;
    private String description;
    private LocalDateTime created_at;
}
