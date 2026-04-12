package com.shashank.taskflow.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    @NotNull
    private String name;
    private String description;
    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;
    @CreationTimestamp
    private LocalDateTime created_at;
}