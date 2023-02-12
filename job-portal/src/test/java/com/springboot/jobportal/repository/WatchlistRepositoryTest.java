package com.springboot.jobportal.repository;

import com.springboot.jobportal.entity.Applicant;
import com.springboot.jobportal.entity.Watchlist;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WatchlistRepositoryTest {

    @Autowired
    private WatchlistRepository watchlistRepository;

    private static String GET_URL = "/allWatchlist/1210";
    private static String GET = "/allWatchlist";
    private static String POST_URL = "/addWatchlist";
    private static String DELETE_URL = "/deleteWatchlist/{watchlistId}";

    String getAll = """
            [
              {
                "watchlistId": 1,
                "applicantId": 1210,
                "jobId": 1,
                "jobCompany": "Microsoft",
                "jobTitle": "Software Dev"
              },
              {
                "watchlistId": 2,
                "applicantId": 1225,
                "jobId": 2,
                "jobCompany": "Google",
                "jobTitle": "Front-End Developer"
              },
              {
                "watchlistId": 3,
                "applicantId": 1200,
                "jobId": 1,
                "jobCompany": "Microsoft",
                "jobTitle": "Software Dev"
              },
              {
                "watchlistId": 5,
                "applicantId": 1200,
                "jobId": 1,
                "jobCompany": "Microsoft",
                "jobTitle": "Software Dev"
              },
              {
                "watchlistId": 6,
                "applicantId": 1226,
                "jobId": 7,
                "jobCompany": "Facebook",
                "jobTitle": "Tester"
              },
              {
                "watchlistId": 7,
                "applicantId": 1210,
                "jobId": 1,
                "jobCompany": "Microsoft",
                "jobTitle": "Software Dev"
              }
            ]""";

    String str = """
            [
              {
                "watchlistId": 1,
                "job_category": "Software Developer",
                "applicant": {
                  "applicantId": 1210,
                  "firstName": "Varun",
                  "lastName": "Teja",
                  "experience": 0,
                  "applicantStatus": "negative",
                  "job": {
                    "jobId": 1,
                    "title": "Software Dev",
                    "company": "Microsoft",
                    "salary": 70000
                  }
                }
              }
            ]""";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void get_Watchlist() throws JSONException {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(GET, String.class);
        JSONAssert.assertEquals(getAll, responseEntity.getBody(), false);
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getHeaders());
    }

    @Test
    void get_WatchlistById() throws JSONException {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(GET_URL, String.class);
        JSONAssert.assertEquals(str, responseEntity.getBody(), false);
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getHeaders());
    }

    @Test
    public void postWatchlist() {
        String requestBody = """
                {
                    "applicantId": 1210,
                    "jobId": 9,
                    "jobCompany": "Facebook",
                    "jobTitle": "Manager"
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
    public void deleteWatchlist(){
        ResponseEntity<Void> response =
                testRestTemplate.exchange(DELETE_URL, HttpMethod.DELETE, null, Void.class, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void saveWatchlist() {
        Applicant applicant = Applicant.builder()
                .applicantId(Long.valueOf(1214))
                .build();

        Watchlist watchlist = Watchlist.builder()
                .job_category("Front-End Developer")
                .applicant(applicant)
                .build();

        watchlistRepository.save(watchlist);
    }

    @Test
    public List<Watchlist> allWatchlist(){
        return watchlistRepository.findAll();
    }

    @Test
    public void deleteWatchlistById(){
        watchlistRepository.deleteById(2L);
    }

    @Test
    public List<Watchlist> getWatchlistById(){
        return watchlistRepository.findAllByApplicantApplicantId(1225L);
    }
}