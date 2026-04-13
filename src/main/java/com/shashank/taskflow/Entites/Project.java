package com.shashank.taskflow.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotNull
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User user;
    @CreationTimestamp
    private LocalDateTime created_at;
}