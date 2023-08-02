package com.sunbase.demo.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final String AUTH_API_URL = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

    public String getBearerToken(String loginId, String password) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(AUTH_API_URL);

            // Set request body
            String jsonRequestBody = "{ \"login_id\": \"" + loginId + "\", " +
                                     "\"password\": \"" + password + "\" }";
            httpPost.setEntity(new StringEntity(jsonRequestBody, ContentType.APPLICATION_JSON));

            HttpResponse response = httpClient.execute(httpPost);

            // Check the response status
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String responseBody = EntityUtils.toString(entity);
                JSONObject jsonObject = new JSONObject(responseBody);
                return jsonObject.getString("token");
            } else {
                // Handle authentication failure
                return null;
            }
        } catch (Exception e) {
            // Handle exception
            return null;
        }
    }
}
