package com.sdk.courier.config;
import com.sdk.courier.client.CourierServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class AppConfig {

    @Value("${baseUrl}")
    private String baseUrl;

}

