package com.springboot.jobportal.repository;

import com.springboot.jobportal.entity.Job;
import com.springboot.jobportal.service.JobService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static String GET_URL = "/allJobs";
    private static String POST_URL = "/addJob";

    String str = """
            [
              {
                "jobId": 1,
                "title": "Software Dev",
                "company": "Microsoft",
                "salary": 70000
              },
              {
                "jobId": 2,
                "title": "Front-End Developer",
                "company": "Google",
                "salary": 120000
              },
              {
                "jobId": 5,
                "title": "Back-End Developer",
                "company": "Microsoft",
                "salary": 70000
              },
              {
                "jobId": 6,
                "title": "Software Developer",
                "company": "Infosys",
                "salary": 80000
              },
              {
                "jobId": 4,
                "title": "Back-End Developer",
                "company": "Google",
                "salary": 90000
              },
              {
                "jobId": 7,
                "title": "Tester",
                "company": "Facebook",
                "salary": 70000
              },
              {
                "jobId": 8,
                "title": "Manager",
                "company": "Twitter",
                "salary": 80000
              },
              {
                "jobId": 9,
                "title": "Manager",
                "company": "Facebook",
                "salary": 60000
              }
            ]""";

    @Test
    void getJobs() throws JSONException {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(GET_URL, String.class);
        JSONAssert.assertEquals(str, responseEntity.getBody(), false);
        System.out.println(responseEntity.getHeaders());
    }

    @Test
    public void postJob() {
        String requestBody = """
                {
                  "title": "Tester",
                  "company": "Netflix",
                  "salary": 50000
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);

        ResponseEntity<String> responseEntity = testRestTemplate.exchange(POST_URL, HttpMethod.POST, httpEntity, String.class);

        System.out.println(responseEntity.getHeaders());
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void deleteJob(){
        String DELETE_URL = "http://localhost:8080/deleteJob/{jobId}";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Void> response =
                restTemplate.exchange(DELETE_URL, HttpMethod.DELETE, null, Void.class, 15);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void saveJob() {
        Job job = Job.builder()
                .title("Software Dev")
                .company("Microsoft")
                .salary(Long.valueOf(70000))
                .build();

        jobRepository.save(job);
    }

    @Test
    public List<Job> allJobs() {
        return jobRepository.findAll();
    }

}