package com.shashank.taskflow.Entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app-user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    @NotNull
    private String name;
    @Email(message = "Type a valid email.")
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    @CreationTimestamp
    private LocalDateTime created_at;
}
