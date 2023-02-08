package com.springboot.jobportal.controller;

import com.springboot.jobportal.entity.Applicant;
import com.springboot.jobportal.entity.Job;
import com.springboot.jobportal.repository.JobRepository;
import com.springboot.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobRepository jobRepository;

    @PostMapping("/addJob")
    public Job postJob(@RequestBody Job job){
        return jobService.saveJob(job);
    }

    @GetMapping("/allJobs")
    public List<Job> allJobs(){
        return jobRepository.findAll();
    }

    @DeleteMapping("/deleteJob/{jobId}")
    public void deleteJob(@PathVariable("jobId") Long jobId) {
        jobService.deleteJob(jobId);
    }
}
