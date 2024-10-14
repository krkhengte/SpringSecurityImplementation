package com.example.springsecurity.controller;

import com.example.springsecurity.dto.AdminRequest;
import com.example.springsecurity.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminRoleRequestController {

    @Autowired
    private AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/adminRoleRequest")
    public ResponseEntity<String> adminRoleRequest(@RequestBody AdminRequest adminRequest){
        Boolean result = adminService.approveAdminRequest(adminRequest);
        if(result){
            return ResponseEntity.status(HttpStatus.OK).body("Admin Role Granted");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("Admin Role Not Granted");
        }
    }

}
