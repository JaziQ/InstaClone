package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @MockBean
    UserRepository userRepository;

    @Test
    void createUser_Successfully() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("A@mail.ru");
        signupRequest.setFirstname("A");
        signupRequest.setLastname("A");
        signupRequest.setUsername("A");
        signupRequest.setPassword("AAAAAA");
        signupRequest.setConfirmPassword("AAAAAA");

        UserService userService = new UserService(userRepository, passwordEncoder);
        try {
            userService.createUser(signupRequest);
        } catch (Exception e) {
            Assertions.assertNull(e);
        }
        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    void createUser_Failed_NullPassword() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail("B@mail.ru");
        signupRequest.setUsername("B");
        signupRequest.setFirstname("B");
        signupRequest.setLastname("B");

        UserService userService = new UserService(userRepository, passwordEncoder);

        try {
            userService.createUser(signupRequest);
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
        Mockito.verify(userRepository, Mockito.times(0)).save(any(User.class));
    }
}