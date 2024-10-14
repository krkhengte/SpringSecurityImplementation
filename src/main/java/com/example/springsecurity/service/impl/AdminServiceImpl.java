package com.example.springsecurity.service.impl;

import com.example.springsecurity.dto.AdminRequest;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repo.UserRepository;
import com.example.springsecurity.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public Boolean approveAdminRequest(AdminRequest adminRequest) {

        if(adminRequest.isRoleGranted()){
            User byUsername = userRepository.findByUsername(adminRequest.getUsername());
            if(byUsername != null){
                byUsername.setActiveStatus("Active");
                userRepository.save(byUsername);
                return true;
            }else {
                return false;
            }
        }
        return false;
    }
}
