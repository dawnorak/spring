package com.springboot.jobportal.repository;

import com.springboot.jobportal.entity.Applicant;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicantRepositoryTest {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static String GET_URL = "/allApplicants";
    private static String POST_URL = "/addApplicant";

    String str = """
            [
              {
                "applicantId": 1200,
                "firstName": "Arun",
                "lastName": "Chandra",
                "experience": 5,
                "jobId": 1,
                "applicantStatus": "positive"
              },
              {
                "applicantId": 1201,
                "firstName": "Sri",
                "lastName": "Kumar",
                "experience": 2,
                "jobId": 2,
                "applicantStatus": "pending"
              },
              {
                "applicantId": 1208,
                "firstName": "Guna",
                "lastName": "Vardhan",
                "experience": 3,
                "jobId": 1,
                "applicantStatus": "pending"
              },
              {
                "applicantId": 1209,
                "firstName": "Rushi",
                "lastName": "Vardhan",
                "experience": 1,
                "jobId": 5,
                "applicantStatus": "pending"
              },
              {
                "applicantId": 1219,
                "firstName": "Varun",
                "lastName": "Dummy",
                "experience": 0,
                "jobId": 6,
                "applicantStatus": "pending"
              },
              {
                "applicantId": 1223,
                "firstName": "Baparna",
                "lastName": "Aparna",
                "experience": 2,
                "jobId": 4,
                "applicantStatus": "pending"
              },
              {
                "applicantId": 1224,
                "firstName": "Baparna",
                "lastName": "Aparna",
                "experience": 2,
                "jobId": 4,
                "applicantStatus": "pending"
              },
              {
                "applicantId": 1210,
                "firstName": "Varun",
                "lastName": "Teja",
                "experience": 0,
                "jobId": 1,
                "applicantStatus": "negative"
              },
              {
                "applicantId": 1225,
                "firstName": "Supriya",
                "lastName": "Sai",
                "experience": 0,
                "jobId": 2,
                "applicantStatus": "negative"
              },
              {
                "applicantId": 1226,
                "firstName": "Sabahat",
                "lastName": "Shaik",
                "experience": 1,
                "jobId": 7,
                "applicantStatus": "negative"
              },
              {
                "applicantId": 1227,
                "firstName": "Jaswanth",
                "lastName": "Nid",
                "experience": 10,
                "jobId": 6,
                "applicantStatus": "pending"
              }
            ]""";


    @Test
    void getApplicant() throws JSONException {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(GET_URL, String.class);
        JSONAssert.assertEquals(str, responseEntity.getBody(), false);
        System.out.println(responseEntity.getHeaders());
    }

    @Test
    public void postApplicant() {
        String requestBody = """
                {
                    "firstName": "Marty",
                    "lastName": "McFly",
                    "experience": 8,
                    "jobId": 8
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);

        ResponseEntity<String> responseEntity =
                testRestTemplate.exchange(POST_URL, HttpMethod.POST, httpEntity, String.class);

        System.out.println(responseEntity.getHeaders());
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void putApplicant() {
        Applicant applicant = new Applicant();
        applicant.setApplicantId(1227L);
        applicant.setApplicantStatus("negative");

        HttpEntity<Applicant> request = new HttpEntity<>(applicant);

        ResponseEntity<Applicant> response =
                testRestTemplate.exchange("/updateApplicant", HttpMethod.PUT, request, Applicant.class, applicant.getApplicantId());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void saveApplicant() {

        Applicant applicant = Applicant.builder()
                .firstName("Guna")
                .lastName("Vardhan")
                .build();

        applicantRepository.save(applicant);
    }

    @Test
    public List<Applicant> allApplicants() {
        return applicantRepository.findAll();
    }

}