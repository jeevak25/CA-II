package com.Jivak.joblisting.controller;

import com.Jivak.joblisting.model.Resume;
import com.Jivak.joblisting.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    // POST endpoint to upload a resume
    @PostMapping("/upload")

    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file,
                                               @RequestParam("userId") String userId) {
        try {
            System.out.println("Received file: " + file.getOriginalFilename());
            System.out.println("Received userId: " + userId);

            // Confirm the file is not empty
            if (file.isEmpty()) {
                System.out.println("Failed: Uploaded file is empty.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
            }

            // Ensure userId is present
            if (userId == null || userId.isEmpty()) {
                System.out.println("Failed: userId is missing.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User ID is required.");
            }

            // Save resume using the service
            resumeService.saveResume(file, userId);
            System.out.println("Resume uploaded successfully.");
            return ResponseEntity.status(HttpStatus.CREATED).body("Resume uploaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload resume due to server error.");
        }
    }

}

