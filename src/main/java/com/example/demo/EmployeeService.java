package com.example.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> fetchEmployees() {
       return (List<Employee>) employeeRepository.findAll();
    }

    public Employee createEmployee(EmployeeRequest employeeRequest) {
        Employee createdEmployee = new Employee();
        BeanUtils.copyProperties(employeeRequest, createdEmployee);
        sendMessageToQueue(createdEmployee);
        return employeeRepository.save(createdEmployee);

    }

    private void sendMessageToQueue(Employee employee) {
        QueueMessage qMsg = new QueueMessage();
        qMsg.setEmail(employee.getEmail());
        qMsg.setName(employee.getName());
        rabbitTemplate.convertAndSend("testDataq", "data", qMsg);
    }
}
