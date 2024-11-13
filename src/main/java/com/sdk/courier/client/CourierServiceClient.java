package com.sdk.courier.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdk.courier.exception.CourierServiceException;
import com.sdk.courier.model.Courier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;

@Component
public class CourierServiceClient {

    private final String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final Set<Integer> successStatusCodes;

    public CourierServiceClient(
            @Value("${courier.service.base-url}") String baseUrl,
             Set<Integer> successStatusCodes
    ) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.successStatusCodes = successStatusCodes;
    }

    public Courier addCourier(Courier courier) throws CourierServiceException {
        try {
            String requestBody = objectMapper.writeValueAsString(courier);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/addCourier"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);

            return objectMapper.readValue(response.body(), Courier.class);

        } catch (JsonProcessingException e) {
            throw new CourierServiceException("Failed to process courier JSON", 500, null, "", e);
        } catch (Exception e) {
            throw new CourierServiceException("Failed to send add courier request", 500, null, "", e);
        }
    }

    public Courier getCourier(Long courierId) throws CourierServiceException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/getCourier/" + courierId))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            validateResponse(response);

            return objectMapper.readValue(response.body(), Courier.class);

        } catch (JsonProcessingException e) {
            throw new CourierServiceException("Failed to process courier JSON", 500, null, "", e);
        } catch (Exception e) {
            throw new CourierServiceException("Failed to send get courier request", 500, null, "", e);
        }
    }

    private void validateResponse(HttpResponse<String> response) throws CourierServiceException {
        if (!successStatusCodes.contains(response.statusCode())) {
            throw new CourierServiceException(
                    "Unexpected response status: " + response.statusCode(),
                    response.statusCode(),
                    response.uri(),
                    response.body()
            );
        }
    }
}
