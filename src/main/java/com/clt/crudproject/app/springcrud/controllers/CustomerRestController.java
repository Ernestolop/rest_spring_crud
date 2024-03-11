package com.clt.crudproject.app.springcrud.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import org.springframework.http.ResponseEntity;
import com.clt.crudproject.app.springcrud.models.services.ICustomerService;
import com.clt.crudproject.app.springcrud.models.entity.CustomerEntity;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> save(@RequestBody CustomerEntity customer) {
        try {
            CustomerEntity customerNew = customerService.save(customer);
            return new ResponseEntity<CustomerEntity>(customerNew, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            CustomerEntity customer = customerService.findById(id);
            if (customer != null) {
                return new ResponseEntity<CustomerEntity>(customer, HttpStatus.OK);
            }
           
            Map<String, String> response = new HashMap<>();
            response.put("message", "El cliente con el id " + id + " no existe en la base de datos");
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers")
    public  ResponseEntity<?> findAll() {
        try {
            List<CustomerEntity> customers = customerService.findAll();
            return new ResponseEntity<List<CustomerEntity>>(customers, HttpStatus.OK);
        } catch (DataAccessException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public  ResponseEntity<?> update(@RequestBody CustomerEntity customer, @PathVariable Long id) {
        try {
            CustomerEntity customerActual = customerService.findById(id);
            if (customerActual != null) {
                customerActual.setCi(customer.getCi());
                customerActual.setName(customer.getName());
                customerActual.setLastName(customer.getLastName());
                customerActual.setEmail(customer.getEmail());
                CustomerEntity customerUpdated = customerService.save(customerActual);
                return new ResponseEntity<CustomerEntity>(customerUpdated, HttpStatus.CREATED);
            }
            Map<String, String> response = new HashMap<>();
            response.put("message", "El cliente con el id " + id + " no existe en la base de datos");
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al realizar el update en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, String>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
       try {
            customerService.delete(id);
        } catch (DataAccessException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Error al realizar el delete en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        }
    }

}
