package com.sdk.courier.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class Courier {

    private Long courierId;
    private String courierName;
    private String courierPhone;
     private List<Long> courierCargosIds;



}
