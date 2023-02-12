package com.springboot.jobportal.repository;

import com.springboot.jobportal.entity.Applicant;
import com.springboot.jobportal.entity.Job;
import com.springboot.jobportal.entity.Notification;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    private static String GET_URL = "/allNotifications";
    private static String POST_URL = "/addNotification";

    String str = """
            [
              {
                "notificationId": 1,
                "jobTitle": "Front-End Developer",
                "jobCompany": "Google",
                "jobId": 2
              },
              {
                "notificationId": 2,
                "jobTitle": "Back-End Developer",
                "jobCompany": "Google",
                "jobId": 4
              },
              {
                "notificationId": 3,
                "jobTitle": "Back-End Developer",
                "jobCompany": "Microsoft",
                "jobId": 5
              },
              {
                "notificationId": 4,
                "jobTitle": "Software Developer",
                "jobCompany": "Infosys",
                "jobId": 6
              },
              {
                "notificationId": 5,
                "jobTitle": "Software Dev",
                "jobCompany": "Microsoft",
                "jobId": 1
              },
              {
                "notificationId": 6,
                "jobTitle": "Manager",
                "jobCompany": "Facebook",
                "jobId": 9
              }
            ]
            """;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getNotification_basic() throws JSONException {
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(GET_URL, String.class);
        JSONAssert.assertEquals(str, responseEntity.getBody(), false);
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getHeaders());
    }

    @Test
    public void postNotification() {
        String requestBody = """
                {
                    "notificationId": 8,
                    "jobId": 10
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
    public void saveNotification() {
        Job job = Job.builder()
                .title("Manager")
                .company("Infosys")
                .salary(Long.valueOf(60000))
                .build();

        Notification notification = Notification.builder()
                .jobCompany("Google")
                .jobTitle("Back-End Developer")
                .job(job)
                .build();

        notificationRepository.save(notification);
    }

    @Test
    public List<Notification> allNotifications(){
        return notificationRepository.findAll();
    }

    @Test
    public void saveNotificationApplicant() {
        Job job = Job.builder()
                .title("Manager")
                .company("Facebook")
                .salary(60000L)
                .build();

        Applicant applicant = Applicant.builder()
                .firstName("Jaswanth")
                .lastName("Nid")
                .experience(10)
                .applicantStatus("pending")
                .build();

        Notification notification = Notification.builder()
                .notificationId(6L)
                .jobCompany(job.getCompany())
                .jobTitle(job.getTitle())
                .job(job)
                .build();

        notification.addApplicant(applicant);

        notificationRepository.save(notification);
    }
}