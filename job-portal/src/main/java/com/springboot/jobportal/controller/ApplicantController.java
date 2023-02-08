package com.springboot.jobportal.controller;

import com.springboot.jobportal.dto.ApplicantJobDto;
import com.springboot.jobportal.entity.Applicant;
import com.springboot.jobportal.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @PostMapping("/addApplicant")
    public Applicant postDetails(@RequestBody ApplicantJobDto applicantJobDto) {
        return applicantService.saveApplicant(applicantJobDto);
    }

    @GetMapping("/allApplicants")
    public List<ApplicantJobDto> allJobs(){
        return applicantService.getApplicant();
    }

    @PutMapping("/updateApplicant")
    public Applicant putApplicant(@RequestBody ApplicantJobDto applicantJobDto){
        return applicantService.updateApplicant(applicantJobDto);
    }

}
