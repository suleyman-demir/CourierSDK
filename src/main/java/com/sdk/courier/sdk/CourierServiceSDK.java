package com.sdk.courier.sdk;

import com.sdk.courier.client.CourierServiceClient;
import com.sdk.courier.model.Courier;

import java.util.concurrent.CompletableFuture;

public class CourierServiceSDK {
    public static final String BASE_URL = "http://localhost:8081/v1/api/deliveryy";

    private final CourierServiceClient courierServiceClient;

    public CourierServiceSDK(CourierServiceClient courierServiceClient) {
        this.courierServiceClient = courierServiceClient;
    }

    public CourierServiceSDK(){
        this.courierServiceClient = new CourierServiceClient(BASE_URL);
    }




    public CompletableFuture<Courier> asyncExecuteGetCourier(Long courierId) throws Exception {
        return CompletableFuture.supplyAsync(()->{
            try {
                return courierServiceClient.getCourier(courierId);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });
    }

    public Courier executeGetCourier(Long courierId) throws Exception {
        return courierServiceClient.getCourier(courierId);
    }



    public Courier executeAddCourier(Courier courier) throws Exception {
        return courierServiceClient.addCourier(courier);
    }

    public CompletableFuture<Courier> asyncExecuteAddCourier(Courier courier) {

        return CompletableFuture.supplyAsync(()-> {
            try {
                return courierServiceClient.addCourier(courier);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });
    }


}
