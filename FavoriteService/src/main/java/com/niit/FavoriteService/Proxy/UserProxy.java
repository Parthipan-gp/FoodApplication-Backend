package com.niit.FavoriteService.Proxy;

import com.niit.FavoriteService.Domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-authentication-service",url = "localhost:8083")
public interface UserProxy {

    @PostMapping("/api/v1/save")
    public ResponseEntity<?> registerUser(User user);
}
