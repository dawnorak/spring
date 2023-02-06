package com.springboot.jobportal.service;

import com.springboot.jobportal.entity.Job;
import com.springboot.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job saveJob(Job job) {

        return jobRepository.save(job);
    }
}
