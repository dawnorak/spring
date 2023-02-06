package com.springboot.jobportal.service;

import com.springboot.jobportal.entity.Applicant;
import com.springboot.jobportal.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.Optional;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;

    public Applicant saveApplicant(Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    public Optional<Applicant> updateApplicant(Applicant applicant){
        applicantRepository.save(applicant);
        return applicantRepository.findById(applicant.getApplicantId());
    }
}
