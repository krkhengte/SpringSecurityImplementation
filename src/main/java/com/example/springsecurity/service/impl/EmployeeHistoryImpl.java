package com.example.springsecurity.service.impl;
import com.example.springsecurity.model.Employee;
import com.example.springsecurity.model.EmployeeHistory;
import com.example.springsecurity.repo.EmployeeHistoryRepo;
import com.example.springsecurity.response.EmployeeHistoryResponse;
import com.example.springsecurity.service.HistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeHistoryImpl implements HistoryService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeHistoryRepo employeeHistoryRepo;

    @Override
    public List<EmployeeHistoryResponse> findAllHistory() {
        List<EmployeeHistory> historyRepoAll = employeeHistoryRepo.findAll();
        List<EmployeeHistoryResponse> employeeHistoryResponses=new ArrayList<>();
        List<Object> collect = historyRepoAll.stream().map((x) -> {
            Employee employee = x.getEmployee();
            EmployeeHistoryResponse employeeHistoryResponse = modelMapper.map(employee, EmployeeHistoryResponse.class);
            employeeHistoryResponse.setUpdatedDateAndTime(x.getUpdatedDateAndTime());
            employeeHistoryResponse.setUpdatedDetails(x.getUpdatedDetails());
            employeeHistoryResponse.setHysId(x.getHysId());
            employeeHistoryResponses.add(employeeHistoryResponse);
            return null;
        }).collect(Collectors.toList());
        return employeeHistoryResponses;
    }

    @Override
    public List<EmployeeHistoryResponse> findHistoryById(Long id) {
       List<EmployeeHistoryResponse> empHistoryList = new ArrayList<>();
        List<EmployeeHistory> employeeHistory = employeeHistoryRepo.getEmployeeHistory(id);
        List<Object> collect = employeeHistory.stream().map((x) -> {
            Employee employee = x.getEmployee();
            EmployeeHistoryResponse employeeHistoryResponse = modelMapper.map(employee, EmployeeHistoryResponse.class);
            employeeHistoryResponse.setUpdatedDateAndTime(x.getUpdatedDateAndTime());
            employeeHistoryResponse.setUpdatedDetails(x.getUpdatedDetails());
            employeeHistoryResponse.setHysId(x.getHysId());
            empHistoryList.add(employeeHistoryResponse);
            return null;
        }).collect(Collectors.toList());
        return empHistoryList;
    }
}
