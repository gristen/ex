package com.example.compl.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String username;
    public String password;

    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(targetClass = RoleModel.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<RoleModel> roles;

}
