package com.springboot.jobportal.service;

import com.springboot.jobportal.dto.ApplicantJobDto;
import com.springboot.jobportal.entity.Applicant;
import com.springboot.jobportal.entity.Job;
import com.springboot.jobportal.repository.ApplicantRepository;
import com.springboot.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicantService {

    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private JobRepository jobRepository;

    private ApplicantJobDto convertEntityToDto(Applicant applicant){
        ApplicantJobDto applicantJobDto = new ApplicantJobDto();
        applicantJobDto.setApplicantId(applicant.getApplicantId());
        applicantJobDto.setFirstName(applicant.getFirstName());
        applicantJobDto.setLastName(applicant.getLastName());
        applicantJobDto.setExperience(applicant.getExperience());
        applicantJobDto.setJobId(applicant.getJob().getJobId());
        applicantJobDto.setApplicantStatus(applicant.getApplicantStatus());

        return applicantJobDto;
    }

    public List<ApplicantJobDto> getApplicant(){
        return applicantRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Applicant saveApplicant(ApplicantJobDto applicant) {
        Job job= jobRepository.findById(applicant.getJobId()).get();

        Applicant applicant1=Applicant.builder()
                .firstName(applicant.getFirstName())
                .lastName(applicant.getLastName())
                .experience(applicant.getExperience())
                .job(job)
                .applicantStatus("pending")
                .build();

        return applicantRepository.save(applicant1);
    }

    public Applicant updateApplicant(ApplicantJobDto applicantJobDto){
        Applicant applicant = applicantRepository.findById(applicantJobDto.getApplicantId()).get();
        Job job = jobRepository.findById(applicant.getJob().getJobId()).get();

        Applicant applicant1 = Applicant.builder()
                .applicantId(applicantJobDto.getApplicantId())
                .firstName(applicant.getFirstName())
                .lastName(applicant.getLastName())
                .experience(applicant.getExperience())
                .job(job)
                .applicantStatus(applicantJobDto.getApplicantStatus())
                .build();

        return applicantRepository.save(applicant1);
    }
}
