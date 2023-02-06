package com.springboot.jobportal.repository;

import com.springboot.jobportal.entity.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @Test
    public void saveJob() {
        Job job = Job.builder()
                .title("Software Dev")
                .company("Microsoft")
                .salary(Long.valueOf(70000))
                .build();

        jobRepository.save(job);
    }

}