package com.sdk.courier.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdk.courier.model.Courier;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CourierServiceClient {
    private final String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;


    public CourierServiceClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }



    public Courier addCourier(Courier courier) throws Exception {
        String requestBody= objectMapper.writeValueAsString(courier);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl+"/addCourier"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode()!=200) {
            throw new RuntimeException("Failed to add courier: "+response.statusCode());
        }
        return objectMapper.readValue(response.body(),Courier.class);
    }

    public Courier getCourier(Long courierId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create(baseUrl+"/getCourier/"+courierId))
                .GET()
               .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode()!=200) {
            throw new RuntimeException("Failed to get courier: "+response.statusCode());
        }
        return objectMapper.readValue(response.body(),Courier.class);
    }








}
