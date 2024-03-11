package com.clt.crudproject.app.springcrud.models.services;

import com.clt.crudproject.app.springcrud.models.entity.CustomerEntity;
import java.util.List;

public interface ICustomerService {

    public List<CustomerEntity> findAll();

}
