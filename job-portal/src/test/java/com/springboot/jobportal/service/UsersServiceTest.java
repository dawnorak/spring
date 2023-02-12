package com.springboot.jobportal.service;

import com.springboot.jobportal.repository.UsersRepository;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersServiceTest {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private TestRestTemplate testRestTemplate;

    private static String GET_URL = "/allUsers";
    private static String POST_URL = "/addUser";

    String get = """
            [
              {
                "userId": 1,
                "username": "admin",
                "password": "admin@123",
                "role": "admin"
              },
              {
                "userId": 2,
                "username": "recruiter",
                "password": "recruiter@123",
                "role": "recruiter"
              },
              {
                "userId": 3,
                "username": "applicant",
                "password": "applicant@123",
                "role": "applicant"
              }
            ]""";

    @Test
    void getUsers() throws JSONException {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(GET_URL, String.class);
        JSONAssert.assertEquals(get, responseEntity.getBody(), false);
        System.out.println(responseEntity.getHeaders());
    }

    @Test
    public void postUser() {
        String requestBody = """
                {
                    "userId": 5,
                    "username": "applicant2",
                    "password": "applicant2",
                    "role": "applicant2"
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

}