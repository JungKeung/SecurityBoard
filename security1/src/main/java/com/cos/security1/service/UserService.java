package com.cos.security1.service;


import com.cos.security1.model.User;
import com.cos.security1.repository.UserDuplicateRepository;
import com.cos.security1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDuplicateRepository userDuplicateRepository;

    public boolean checkDuplicatedEmail(String email){
        Optional<User> userEmail = userDuplicateRepository.findByEmail(email);
        boolean checkEmail = userEmail == null ? false : true;

        if (checkEmail == true){
            throw new IllegalStateException ("이미 존재하는 이메일입니다.");
        }
        return false;
    }
}
