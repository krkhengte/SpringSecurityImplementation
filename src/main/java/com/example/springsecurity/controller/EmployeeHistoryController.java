package com.example.springsecurity.controller;

import com.example.springsecurity.response.EmployeeHistoryResponse;
import com.example.springsecurity.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeHistoryController {

    @Autowired
    private HistoryService service;

    @GetMapping("/admin/getAllHistory")
    public ResponseEntity<List<EmployeeHistoryResponse>> getAllHistory(){
        List<EmployeeHistoryResponse> allHistory = service.findAllHistory();
        return ResponseEntity.status(HttpStatus.OK).body(allHistory);
    }

    @GetMapping("/admin/getAllHistory/{id}")
    public ResponseEntity<List<EmployeeHistoryResponse>> getAllHistoryById(@PathVariable Long id){
        List<EmployeeHistoryResponse> allHistory = service.findHistoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(allHistory);
    }

}
