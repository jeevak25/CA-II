package com.Jivak.joblisting.repository;

import com.Jivak.joblisting.model.Resume;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends MongoRepository<Resume, String> {
}
