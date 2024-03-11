package com.clt.crudproject.app.springcrud.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clt.crudproject.app.springcrud.models.services.ICustomerService;
import com.clt.crudproject.app.springcrud.models.entity.CustomerEntity;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {
    
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/list")
    public List<CustomerEntity> findAll() {
        return customerService.findAll();
    }

}
