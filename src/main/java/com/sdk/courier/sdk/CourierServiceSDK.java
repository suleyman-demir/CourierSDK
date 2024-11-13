package com.sdk.courier.sdk;

import com.sdk.courier.client.CourierServiceClient;
import com.sdk.courier.exception.CourierServiceException;
import com.sdk.courier.exception.ErrorHandler;
import com.sdk.courier.model.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * SDK class for interacting with the Courier Service API asynchronously and synchronously.
 * Provides methods to add and retrieve couriers through HTTP requests to the specified base URL.
 */
@Service
public class CourierServiceSDK {

    private final CourierServiceClient courierServiceClient;
    private final ErrorHandler errorHandler;



    @Autowired
    public CourierServiceSDK(CourierServiceClient courierServiceClient, ErrorHandler errorHandler) {
        this.courierServiceClient = courierServiceClient;
        this.errorHandler = errorHandler;
    }

    public CompletableFuture<Courier> asyncExecuteGetCourier(Long courierId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return courierServiceClient.getCourier(courierId);
            } catch (CourierServiceException e) {
                errorHandler.handle(e);
                throw e;
            }
        });
    }

    public Courier executeGetCourier(Long courierId) throws CourierServiceException {
        try {
            return courierServiceClient.getCourier(courierId);
        } catch (CourierServiceException e) {
            errorHandler.handle(e);
            throw e;
        }
    }

    public Courier executeAddCourier(Courier courier) throws CourierServiceException {
        try {
            return courierServiceClient.addCourier(courier);
        } catch (CourierServiceException e) {
            errorHandler.handle(e);
            throw e;
        }
    }

    public CompletableFuture<Courier> asyncExecuteAddCourier(Courier courier) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return courierServiceClient.addCourier(courier);
            } catch (CourierServiceException e) {
                errorHandler.handle(e);
                throw e;
            }
        });
    }
}
