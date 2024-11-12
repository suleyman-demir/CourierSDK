package com.sdk.courier.sdk;

import com.sdk.courier.client.CourierServiceClient;
import com.sdk.courier.exception.CourierServiceException;
import com.sdk.courier.model.Courier;

import java.util.concurrent.CompletableFuture;

/**
 * SDK class for interacting with the Courier Service API asynchronously and synchronously.
 * Provides methods to add and retrieve couriers through HTTP requests to the specified base URL.
 */

public class CourierServiceSDK {
    private final CourierServiceClient courierServiceClient;

    /**
     * Constructs a CourierServiceSDK instance with a specified CourierServiceClient.
     *
     * @param courierServiceClient the client responsible for handling courier-related requests.
     */
    public CourierServiceSDK(CourierServiceClient courierServiceClient) {
        this.courierServiceClient = courierServiceClient;
    }


    /**
     * Asynchronously retrieves a courier by its ID.
     *
     * @param courierId the ID of the courier to retrieve.
     * @return a CompletableFuture containing the requested Courier object.
     * @throws CourierServiceException if the request fails or the courier cannot be found.
     */
    public CompletableFuture<Courier> asyncExecuteGetCourier(Long courierId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return courierServiceClient.getCourier(courierId);
            } catch (CourierServiceException e) {
                throw new RuntimeException("Failed to asynchronously get courier", e);
            }
        });
    }

    /**
     * Synchronously retrieves a courier by its ID.
     *
     * @param courierId the ID of the courier to retrieve.
     * @return the Courier object associated with the specified ID.
     * @throws CourierServiceException if the request fails or the courier cannot be found.
     */
    public Courier executeGetCourier(Long courierId) throws CourierServiceException {
        return courierServiceClient.getCourier(courierId);
    }

    /**
     * Synchronously adds a new courier.
     *
     * @param courier the Courier object to be added.
     * @return the added Courier object with updated information.
     * @throws CourierServiceException if the request fails and the courier cannot be added.
     */
    public Courier executeAddCourier(Courier courier) throws CourierServiceException {
        return courierServiceClient.addCourier(courier);
    }

    /**
     * Asynchronously adds a new courier.
     *
     * @param courier the Courier object to be added.
     * @return a CompletableFuture containing the added Courier object.
     * @throws CourierServiceException if the request fails and the courier cannot be added.
     */
    public CompletableFuture<Courier> asyncExecuteAddCourier(Courier courier) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return courierServiceClient.addCourier(courier);
            } catch (CourierServiceException e) {
                throw new RuntimeException("Failed to asynchronously add courier", e);
            }
        });
    }
}
