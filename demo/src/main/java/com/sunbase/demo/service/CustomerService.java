package com.sunbase.demo.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final String API_BASE_URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
    private String accessToken;

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String createNewCustomer(String firstName, String lastName, String street, String address,
                                    String city, String state, String email, String phone) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(API_BASE_URL);

            // Set headers
            httpPost.setHeader("Authorization", "Bearer " + accessToken);

            // Set request body
            String jsonRequestBody = "{ \"first_name\": \"" + firstName + "\", " +
                    "\"last_name\": \"" + lastName + "\", " +
                    "\"street\": \"" + street + "\", " +
                    "\"address\": \"" + address + "\", " +
                    "\"city\": \"" + city + "\", " +
                    "\"state\": \"" + state + "\", " +
                    "\"email\": \"" + email + "\", " +
                    "\"phone\": \"" + phone + "\" }";
            httpPost.setEntity(new StringEntity(jsonRequestBody, ContentType.APPLICATION_JSON));

            HttpResponse response = httpClient.execute(httpPost);

            // Check the response status
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
                return "Successfully Created";
            } else {
                return "Failed to create customer. HTTP Status: " + response.getStatusLine().getStatusCode();
            }
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }
}
