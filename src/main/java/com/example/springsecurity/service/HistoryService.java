package com.example.springsecurity.service;

import com.example.springsecurity.response.EmployeeHistoryResponse;

import java.util.List;

public interface HistoryService {

    public List<EmployeeHistoryResponse> findAllHistory();

    public List<EmployeeHistoryResponse> findHistoryById(Long id);

}
