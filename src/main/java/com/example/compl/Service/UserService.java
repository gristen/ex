package com.example.compl.Service;

import com.example.compl.Models.RoleModel;
import com.example.compl.Models.UserModel;
import com.example.compl.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserService {
    UserRepo userRepo;
    PasswordEncoder passwordEncoder;

    public void store(UserModel data){
        UserModel user = new UserModel();
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setRoles(Collections.singleton(RoleModel.ADMIN));
        userRepo.save(user);
    }
}
