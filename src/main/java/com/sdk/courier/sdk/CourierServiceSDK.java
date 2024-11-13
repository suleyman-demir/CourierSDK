package com.sdk.courier.sdk;

import com.sdk.courier.client.CourierServiceClient;
import com.sdk.courier.exception.CourierServiceException;
import com.sdk.courier.model.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CourierServiceSDK {

    private final CourierServiceClient courierServiceClient;

    @Autowired
    public CourierServiceSDK(CourierServiceClient courierServiceClient) {
        this.courierServiceClient = courierServiceClient;
    }

    public CompletableFuture<Courier> asyncExecuteGetCourier(Long courierId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return courierServiceClient.getCourier(courierId);
            } catch (CourierServiceException e) {
                throw new RuntimeException("Async getCourier failed", e);
            }
        });
    }

    public Courier executeGetCourier(Long courierId) throws CourierServiceException {
        return courierServiceClient.getCourier(courierId);
    }

    public Courier executeAddCourier(Courier courier) throws CourierServiceException {
        return courierServiceClient.addCourier(courier);
    }

    public CompletableFuture<Courier> asyncExecuteAddCourier(Courier courier) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return courierServiceClient.addCourier(courier);
            } catch (CourierServiceException e) {
                throw new RuntimeException("Async addCourier failed", e);
            }
        });
    }
}
