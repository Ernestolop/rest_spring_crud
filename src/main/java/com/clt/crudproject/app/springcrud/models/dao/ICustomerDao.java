package com.clt.crudproject.app.springcrud.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.clt.crudproject.app.springcrud.models.entity.CustomerEntity;

public interface ICustomerDao extends CrudRepository<CustomerEntity, Long> {

}