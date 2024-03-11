package com.clt.crudproject.app.springcrud.models.services;

import com.clt.crudproject.app.springcrud.models.entity.CustomerEntity;
import java.util.List;

public interface ICustomerService {

    public CustomerEntity save (CustomerEntity customer);
    
    public CustomerEntity findById(Long id);
    
    public List<CustomerEntity> findAll();

    public void delete(Long id);

}
