package com.example.demo.services;

import com.example.demo.entity.ImageModel;
import com.example.demo.repository.ImageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.security.Principal;

@RunWith(SpringRunner.class)
@SpringBootTest
class ImageUploadServiceTest extends IOException {
    @Autowired
    ImageUploadService imageUploadService;

    @Autowired
    ImageRepository imageRepository;

    @MockBean
    ImageModel imageModel;

    @Test
    void getImageToPost_SuccessfullyGetImage() {
        String postId = "11";
        Assertions.assertNotNull(imageUploadService.getImageToPost(Long.parseLong(postId)));
    }

    @Test
    void getImageToPost_Failed_ImageNotFound(){
        String postId = "101";
        try {
            Assertions.assertNull(imageUploadService.getImageToPost(Long.parseLong(postId)));
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }
    @Test
    void getImageToUser_SuccessfullyGetImage(){
        Principal principal = () -> "Jazi";
        Assertions.assertNotNull(imageUploadService.getImageToUser(principal));
    }

    @Test
    void getImageToUser_Failed_UserNotFound() {
        Principal principal = () -> "e45tgbj";
        try {
            Assertions.assertNull(imageUploadService.getImageToUser(principal));
        } catch (Exception e) {
            Assertions.assertNotNull(e);
        }
    }
}