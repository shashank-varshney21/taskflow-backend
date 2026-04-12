package com.shashank.taskflow.Entites;

import com.shashank.taskflow.Enums.PriorityEnum;
import com.shashank.taskflow.Enums.StatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    Long id;
    @NotNull
    String title;
    String description;
    @Enumerated(EnumType.STRING)
    StatusEnum status;
    @Enumerated(EnumType.STRING)
    PriorityEnum priority;
    @OneToOne(targetEntity = Project.class)
    @NotNull
    Long project_id;
    @OneToOne(targetEntity = User.class)
    Long assignee_id;
    LocalDate due_date;
    @CreationTimestamp
    LocalDateTime createdAt;
    @UpdateTimestamp
    LocalDateTime updated_at;
}