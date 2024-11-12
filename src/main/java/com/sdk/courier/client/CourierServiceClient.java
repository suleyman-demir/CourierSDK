package com.sdk.courier.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdk.courier.exception.CourierServiceException;
import com.sdk.courier.model.Courier;
import lombok.Setter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * The CourierServiceClient is responsible for handling HTTP requests to interact with
 * the Courier Service API. It provides methods to add and retrieve courier information.
 */
public class CourierServiceClient {
    private final String baseUrl;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    /**
     * -- SETTER --
     *  Sets the HTTP status codes that should be considered successful.
     *
     * @param successStatusCodes a Set of HTTP status codes that represent successful responses.
     */


    // Varsayılan olarak 200'ü Kabul kodu olarak atadım
    @Setter
    private Set<Integer> successStatusCodes = new HashSet<>(Set.of(200));

    /**
     * Constructs a new CourierServiceClient with the specified base URL.
     *
     * @param baseUrl the base URL of the Courier Service API.
     */
    public CourierServiceClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Adds a new courier to the Courier Service API.
     *
     * @param courier the Courier object to be added.
     * @return the added Courier object as returned by the API.
     * @throws CourierServiceException if the request fails or the courier cannot be processed.
     */
    public Courier addCourier(Courier courier) throws CourierServiceException {
        try {
            String requestBody = objectMapper.writeValueAsString(courier);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/addCourier"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // response.statusCode() başarısızsa özel bir exception fırlatıyoruz.
            if (!successStatusCodes.contains(response.statusCode())) {
                throw new CourierServiceException(
                        "Failed to add courier",
                        response.statusCode(),
                        request.uri(),
                        response.body()
                );
            }

            return objectMapper.readValue(response.body(), Courier.class);

        } catch (JsonProcessingException e) {
            throw new CourierServiceException("Failed to process courier JSON", 500, null, "", e);
        } catch (Exception e) {
            throw new CourierServiceException("Failed to send add courier request", 500, null, "", e);
        }
    }

    /**
     * Retrieves a courier from the Courier Service API by its ID.
     *
     * @param courierId the ID of the courier to retrieve.
     * @return the Courier object associated with the specified ID.
     * @throws CourierServiceException if the request fails or the courier cannot be retrieved.
     */
    public Courier getCourier(Long courierId) throws CourierServiceException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/getCourier/" + courierId))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (!successStatusCodes.contains(response.statusCode())) {
                throw new CourierServiceException(
                        "Failed to get courier",
                        response.statusCode(),
                        request.uri(),
                        response.body()
                );
            }

            return objectMapper.readValue(response.body(), Courier.class);

        } catch (JsonProcessingException e) {
            throw new CourierServiceException("Failed to process courier JSON", 500, null, "", e);
        } catch (Exception e) {
            throw new CourierServiceException("Failed to send get courier request", 500, null, "", e);
        }
    }
}
