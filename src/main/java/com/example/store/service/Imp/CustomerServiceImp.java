package com.example.store.service.Imp;

import com.example.store.dao.CustomerDao;
import com.example.store.dao.Imp.CustomerDaoImpJdbc;
import com.example.store.domain.Customer;
import com.example.store.service.CustomerService;
import com.example.store.service.ServiceException;

public class CustomerServiceImp implements CustomerService {
    CustomerDao dao = new CustomerDaoImpJdbc();
    @Override
    public boolean login(Customer customer) {
        Customer dbCustomer = dao.findByPk(customer.getId());
        if (dbCustomer != null && dbCustomer.getPassword().equals(customer.getPassword())){
            customer.setBirthday(dbCustomer.getBirthday());
            customer.setPhone(dbCustomer.getPhone());
            customer.setAddress(dbCustomer.getAddress());
            customer.setName(dbCustomer.getName());
            return true;
        }
        return false;
    }

    @Override
    public void register(Customer customer) throws ServiceException {
        Customer dbCustomer = dao.findByPk(customer.getId());
        if (dbCustomer != null){
            throw new ServiceException("id exists" + customer.getId());
        } else{
            dao.create(customer);
        }
    }
}
