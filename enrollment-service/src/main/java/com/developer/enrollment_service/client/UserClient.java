package com.developer.enrollment_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserClient {

    @GetMapping("user/{id}")
    String getUserById(@PathVariable Integer id);
}
