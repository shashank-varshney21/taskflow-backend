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
    Long id;
    @NotNull
    String name;
    String description;
    @OneToOne(targetEntity = User.class)
    Long owner_id;
    @CreationTimestamp
    LocalDateTime created_at;
}