package com.Jivak.joblisting.service;


import com.Jivak.joblisting.model.Resume;
import com.Jivak.joblisting.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public void saveResume(MultipartFile file, String userId) throws IOException {
        Resume resume = new Resume();
        resume.setUserId(userId);
        resume.setFileName(file.getOriginalFilename());
        resume.setFileType(file.getContentType());
        resume.setFileData(file.getBytes());
        resumeRepository.save(resume);
    }
}
