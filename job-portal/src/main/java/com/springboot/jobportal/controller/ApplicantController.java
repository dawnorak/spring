package com.springboot.jobportal.controller;

import com.springboot.jobportal.entity.Applicant;
import com.springboot.jobportal.repository.ApplicantRepository;
import com.springboot.jobportal.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private ApplicantRepository applicantRepository;

    @PostMapping("/addApplicant")
    public Applicant postDetails(@RequestBody Applicant applicant) {
        return applicantService.saveApplicant(applicant);
    }

    @PutMapping("/updateApplicant")
    public Optional<Applicant> putApplicant(@RequestBody Applicant applicant){
        //applicantRepository.save(applicant);
        //return applicantRepository.findById(applicant.getApplicantId());
        return applicantService.updateApplicant(applicant);
    }
}
