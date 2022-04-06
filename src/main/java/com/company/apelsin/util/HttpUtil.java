package com.company.apelsin.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class HttpUtil {

    private static final String username = "user";
    private static final String password = "user";

    public static <T> T get(String url, Class<T> clazz){
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth(username, password);

            HttpEntity httpEntity = new HttpEntity(headers);

            ResponseEntity<T> responseObj =
                    restTemplate.exchange(url, HttpMethod.GET, httpEntity, clazz);

            return responseObj.getBody();
        } catch (RuntimeException e){
            log.info("exception in Sending request to uzcard-project");
            e.printStackTrace();
            return null;
        }
    }

    public static <T, R> T post(String url, R requestBody, Class<T> responseClass) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth(username, password);

            HttpEntity<R> httpEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<T> responseObj =
                    restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseClass);

            return responseObj.getBody();
        } catch (RuntimeException e) {
            log.info("exception in Sending request to uzcard-project");
            e.printStackTrace();
            return null;
        }
    }

    public static <T, R> T put(String url, R requestBody, Class<T> responseClass){
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth(username, password);

            HttpEntity<R> httpEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<T> responseObj =
                    restTemplate.exchange(url, HttpMethod.PUT, httpEntity, responseClass);

            return responseObj.getBody();
        } catch (RuntimeException e){
            log.info("exception in Sending request to uzcard-project");
            e.printStackTrace();
            return null;
        }
    }

    public static <T, R> T request(HttpMethod method, String url, R requestBody, Class<T> responseClass){
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth(username, password);

            HttpEntity<R> httpEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<T> responseObj =
                    restTemplate.exchange(url, method, httpEntity, responseClass);

            if (responseObj.getStatusCodeValue() == 401){
                throw new RuntimeException("Unauthorized request to Uzcard Project");
            } else
            if (responseObj.getStatusCodeValue() == 400){
                throw new RuntimeException("Bad Request to Uzcard Project");
            }

            return responseObj.getBody();
        } catch (RuntimeException e){
            log.info("exception in Sending request to uzcard-project");
            e.printStackTrace();
            return null;
        }
    }

}
