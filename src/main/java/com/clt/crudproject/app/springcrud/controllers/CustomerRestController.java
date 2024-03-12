package com.clt.crudproject.app.springcrud.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import com.clt.crudproject.app.springcrud.models.services.ICustomerService;
import jakarta.validation.Valid;

import com.clt.crudproject.app.springcrud.models.entity.CustomerEntity;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("/customers")
    public ResponseEntity<?> save(@Valid @RequestBody CustomerEntity customer, BindingResult result) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("message", "Verifique los datos ingresados");
            response.put("errors", errors);
            status = HttpStatus.BAD_REQUEST;
        } else {
            try {
                CustomerEntity customerNew = customerService.save(customer);
                response.put("message", "El cliente ha sido creado con éxito");
                response.put("result", customerNew);
                status = HttpStatus.CREATED;
            } catch (DataAccessException e) {
                response.put("message", "Error al realizar el insert en la base de datos");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<Map<String, Object>>(response, status);
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = null;

        try {
            CustomerEntity customer = customerService.findById(id);
            if (customer != null) {
                response.put("message", "Cliente encontrado con éxito");
                response.put("result", customer);
                status = HttpStatus.OK;
            } else {
                response.put("message", "Verifique el id ingresado");
                response.put("error", "El cliente con el id " + id + " no existe en la base de datos");
                status = HttpStatus.NOT_FOUND;
            }
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(response, status);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> findAll() {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = null;

        try {
            List<CustomerEntity> customers = customerService.findAll();
            response.put("message", "Consulta realizada con éxito");
            response.put("result", customers);
            status = HttpStatus.OK;
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(response, status);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CustomerEntity customer, BindingResult result,
            @PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = null;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("message", "Verifique los datos ingresados");
            response.put("errors", errors);
            status = HttpStatus.BAD_REQUEST;
        } else {
            try {
                CustomerEntity customerActual = customerService.findById(id);
                if (customerActual != null) {
                    customerActual.setCi(customer.getCi());
                    customerActual.setName(customer.getName());
                    customerActual.setLastName(customer.getLastName());
                    customerActual.setEmail(customer.getEmail());
                    CustomerEntity customerUpdated = customerService.save(customerActual);
                    response.put("message", "El cliente ha sido actualizado con éxito");
                    response.put("result", customerUpdated);
                    status = HttpStatus.CREATED;
                } else {
                    response.put("message", "Verifique el id ingresado");
                    response.put("error", "El cliente con el id " + id + " no existe en la base de datos");
                }
                status = HttpStatus.NOT_FOUND;
            } catch (DataAccessException e) {
                response.put("message", "Error al realizar el update en la base de datos");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return new ResponseEntity<Map<String, Object>>(response, status);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = null;

        try {
            CustomerEntity customer = customerService.findById(id);
            if (customer == null) {
                response.put("message", "Verifique el id ingresado");
                response.put("error", "El cliente con el id " + id + " no existe en la base de datos");
                status = HttpStatus.NOT_FOUND;
            } else {
                customerService.delete(id);
                response.put("message", "Cliente eliminado con éxito");
                response.put("result", "Cliente con id " + id + " eliminado de la base de datos");
                status = HttpStatus.OK;
            }
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar el delete en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(response, status);

    }

}
