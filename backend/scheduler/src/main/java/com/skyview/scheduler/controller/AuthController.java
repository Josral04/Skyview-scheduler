package com.skyview.scheduler.controller;

import com.skyview.scheduler.dto.RegisterRequest;
import com.skyview.scheduler.dto.RegisterResponse;
import com.skyview.scheduler.model.Staff;
import com.skyview.scheduler.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest req) {
        Staff staff = authService.registerWithCode(req);
        return ResponseEntity.ok(new RegisterResponse(staff.getId(), req.username));
    }
}
