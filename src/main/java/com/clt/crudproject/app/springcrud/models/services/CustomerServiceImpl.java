package com.clt.crudproject.app.springcrud.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clt.crudproject.app.springcrud.models.dao.ICustomerDao;
import com.clt.crudproject.app.springcrud.models.entity.CustomerEntity;


@Service
public class CustomerServiceImpl implements ICustomerService{
    
    @Autowired
    private ICustomerDao customerDao;

    @Override
    public List<CustomerEntity> findAll() {
        return (List<CustomerEntity>) customerDao.findAll();
    }

}
