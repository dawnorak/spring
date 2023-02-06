package com.springboot.jobportal.repository;

import com.springboot.jobportal.entity.Applicant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicantRepositoryTest {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Test
    public void saveApplicant() {
        // Job job = Job.builder().jobId(Long.valueOf(2)).build();

        Applicant applicant = Applicant.builder()
                .firstName("Guna")
                .lastName("Vardhan")
                .build();

        applicantRepository.save(applicant);
    }

}