package com.clt.crudproject.app.springcrud.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.clt.crudproject.app.springcrud.models.services.ICustomerService;
import com.clt.crudproject.app.springcrud.models.entity.CustomerEntity;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerEntity save(@RequestBody CustomerEntity customer) {
        return customerService.save(customer);
    }

    @GetMapping("customers/{id}")
    public CustomerEntity findById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @GetMapping("/customers")
    public List<CustomerEntity> findAll() {
        return customerService.findAll();
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerEntity update(@RequestBody CustomerEntity customer, @PathVariable Long id) {
        CustomerEntity customerActual = customerService.findById(id);
        if (customerActual != null) {
            customerActual.setCi(customer.getCi());
            customerActual.setName(customer.getName());
            customerActual.setLastName(customer.getLastName());
            customerActual.setEmail(customer.getEmail());
            return customerService.save(customerActual);
        }
        return null;
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

}
