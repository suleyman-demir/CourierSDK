package com.sdk.courier.sdk;

import com.sdk.courier.client.CourierServiceClient;
import com.sdk.courier.exception.CourierServiceException;
import com.sdk.courier.exception.CourierErrorHandler;
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
     */
    public CompletableFuture<Courier> asyncExecuteGetCourier(Long courierId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return courierServiceClient.getCourier(courierId);
            } catch (CourierServiceException e) {
                CourierErrorHandler.handleException(e);
                throw e; // Exception'ı tekrar fırlatıyoruz, böylece asenkron işlemde de görünür.
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
        try {
            return courierServiceClient.getCourier(courierId);
        } catch (CourierServiceException e) {
            CourierErrorHandler.handleException(e);
            throw e;
        }
    }

    /**
     * Synchronously adds a new courier.
     *
     * @param courier the Courier object to be added.
     * @return the added Courier object with updated information.
     * @throws CourierServiceException if the request fails and the courier cannot be added.
     */
    public Courier executeAddCourier(Courier courier) throws CourierServiceException {
        try {
            return courierServiceClient.addCourier(courier);
        } catch (CourierServiceException e) {
            CourierErrorHandler.handleException(e);
            throw e;
        }
    }

    /**
     * Asynchronously adds a new courier.
     *
     * @param courier the Courier object to be added.
     * @return a CompletableFuture containing the added Courier object.
     */
    public CompletableFuture<Courier> asyncExecuteAddCourier(Courier courier) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return courierServiceClient.addCourier(courier);
            } catch (CourierServiceException e) {
                CourierErrorHandler.handleException(e);
                throw e;
            }
        });
    }
}
